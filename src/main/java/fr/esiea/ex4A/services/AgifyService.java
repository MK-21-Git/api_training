package fr.esiea.ex4A.services;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.dao.AgifyRepository;
import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import org.springframework.stereotype.Service;
import retrofit2.Call;

import java.io.IOException;

@Service
public class AgifyService {
    final AgifyClient agifyClient;
    final AgifyRepository agifyRepository;

    public AgifyService(AgifyClient agifyClient, AgifyRepository agifyRepository) {
        this.agifyClient = agifyClient;
        this.agifyRepository = agifyRepository;
    }
    public AgifyUser callAgify (String name, String country) {
        AgifyUser agifyUser = null;
        Call<AgifyUser> agifyDataCall = agifyClient.getData(name,country);
        try {
            agifyUser = agifyDataCall.execute().body();
            agifyRepository.addAgifyData(agifyUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agifyUser;
    }
    public AgifyUser existAgifyData (Key key) {
        return agifyRepository.countainAgifyData(key);
    }
}
