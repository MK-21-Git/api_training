package fr.esiea.ex4A.hello;

import fr.esiea.ex4A.dao.UserRepository;
import fr.esiea.ex4A.model.AgifyData;
import fr.esiea.ex4A.model.UserData;
import fr.esiea.ex4A.services.AgifyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping(path="/api/inscription", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserData> inscription (@RequestBody UserData userData)
    {
        return this.userRepository.addUser(userData);
    }
    @GetMapping(path="/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<UserData>  matches(@RequestParam(name = "userName") String name, @RequestParam (name = "userCountry") String country)
    {
        AgifyData agifyData = agifyService.existAgifyData(name, country);
        if ( agifyData == null ) {
            agifyData = agifyService.callAgify(name, country);
        }

        List <UserData> result = this.userRepository.getMatchesUsers(name,country,agifyData.getAge());
        return result;
    }
    @GetMapping (path = "/api/allusers")
    List <UserData> getAllUsers (){
        return this.userRepository.getUsers();
    }
}

