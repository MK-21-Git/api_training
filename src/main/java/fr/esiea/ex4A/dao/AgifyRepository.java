package fr.esiea.ex4A.dao;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;

@Repository
public class AgifyRepository {
    final AgifyClient agifyClient;
    public final HashMap<Key, AgifyUser> agifyBD = new HashMap<>();

    public AgifyRepository(AgifyClient agifyClient) {
        this.agifyClient = agifyClient;
    }

    public void addAgifyUser(AgifyUser agifyUser) {
        agifyBD.put(new Key(agifyUser.getName(), agifyUser.getCountryId()), agifyUser);
    }
    public AgifyUser callAgify (String name, String country) throws IOException {
        Call<AgifyUser> agifyDataCall = agifyClient.getData(name,country);
        Response<AgifyUser> response = agifyDataCall.execute();
        AgifyUser agifyUser = response.body();
        addAgifyUser(agifyUser);
        return agifyUser;
    }
    public AgifyUser countainAgifyUser(Key key) {
        return agifyBD.get(key);
    }
}
