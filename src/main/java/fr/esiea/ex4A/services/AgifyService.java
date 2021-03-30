package fr.esiea.ex4A.services;

import fr.esiea.ex4A.dao.AgifyRepository;
import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class AgifyService {
    final AgifyRepository agifyRepository;

    public AgifyService(AgifyRepository agifyRepository) {
        this.agifyRepository = agifyRepository;
    }
    public AgifyUser callAgify (String name, String country) {
        try {
            return agifyRepository.callAgify(name, country);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AgifyUser existAgifyUser(Key key) {
        return agifyRepository.countainAgifyUser(key);
    }
}
