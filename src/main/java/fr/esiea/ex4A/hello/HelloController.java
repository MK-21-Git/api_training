package fr.esiea.ex4A.hello;

import fr.esiea.ex4A.dao.UserRepository;
import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import fr.esiea.ex4A.model.UserInfo;
import fr.esiea.ex4A.model.UserMatch;
import fr.esiea.ex4A.services.AgifyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
class HelloController {

    private final HelloRepository helloRepository;
    private final UserRepository userRepository;
    private final AgifyService agifyService;

    HelloController(HelloRepository helloRepository, UserRepository userRepository, AgifyService agifyService) {
        this.helloRepository = helloRepository;
        this.userRepository = userRepository;
        this.agifyService = agifyService;
    }

    @GetMapping(path = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    HelloData sayHello(@RequestParam(name = "name", required = false) String name) {
        final HelloData helloData;
        if (name == null || name.isBlank()) {
            helloData = helloRepository.randomHello();
        } else {
            helloData = helloRepository.getHelloFor(name);
        }
        return helloData;
    }

    @PostMapping(path="/api/inscription", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_HTML_VALUE)
    String inscription (@RequestBody UserInfo userInfo) {
        AgifyUser agifyUser = agifyService.existAgifyUser(new Key(userInfo.getUserName().toLowerCase(Locale.ROOT), userInfo.getUserCountry()));
        if ( agifyUser == null ) {
            agifyUser = agifyService.callAgify(userInfo.getUserName(), userInfo.getUserCountry());
        }
        this.userRepository.addUser(new UserInfo(agifyUser, userInfo.getUserSex(), userInfo.getUserSexPref()));
        return "user " + userInfo .getUserName() + " added successfully";
    }

    @GetMapping(path="/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserMatch>  matches(@RequestParam(name = "userName") String name, @RequestParam (name = "userCountry") String country) {
        return this.userRepository.getMatchesUsers(name.substring(0, 1).toUpperCase() + name.substring(1),country);
    }

}

