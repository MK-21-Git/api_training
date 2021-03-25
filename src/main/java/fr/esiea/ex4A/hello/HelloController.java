package fr.esiea.ex4A.hello;

import fr.esiea.ex4A.dao.UserRepository;
import fr.esiea.ex4A.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class HelloController {

    private final HelloRepository helloRepository;
    private final UserRepository userRepository;

    HelloController(HelloRepository helloRepository, UserRepository userRepository) {
        this.helloRepository = helloRepository;
        this.userRepository = userRepository;
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
    List<User> inscription (@RequestBody User user)
    {
        return this.userRepository.addUser(user);
    }
    @GetMapping(path="/api/matches", produces = MediaType.APPLICATION_JSON_VALUE)
    List<User>  matches(@RequestParam(name = "userName") String name, @RequestParam (name = "userCountry") String country)
    {
        User user = new User ();
        user.setUserName(name);
        user.setUserCountry(country);
        List <User> result = this.userRepository.getMatchesUsers(user);
        return result;
    }
    @GetMapping (path = "/api/allusers")
    List <User> getAllUsers (){

        return this.userRepository.getUsers();
    }
}

