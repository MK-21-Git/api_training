package fr.esiea.ex4A.services;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.dao.AgifyRepository;
import fr.esiea.ex4A.dao.CacheAgify;
import fr.esiea.ex4A.model.AgifyData;
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
    public AgifyData callAgify (String name, String country)
    {
        AgifyData resultat = null;
        Call<AgifyData> agifyDataCall = agifyClient.getData(name,country);
        try {
            resultat = agifyDataCall.execute().body();
            CacheAgify.agifyBD.put(resultat,resultat.getAge());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultat;
    }
    public AgifyData existAgifyData (String name, String country) {
        return agifyRepository.countainAgifyData(name,country);
    }
}
