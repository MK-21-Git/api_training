package fr.esiea.ex4A.dao;

import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class AgifyRepository {

    public final HashMap<Key, AgifyUser> agifyBD = new HashMap<>();

    public void addAgifyData(AgifyUser agifyUser) {
        agifyBD.put(new Key(agifyUser.getName(), agifyUser.getCountryId()), agifyUser);
    }

    public AgifyUser countainAgifyData(Key key) {
        return agifyBD.get(key);
    }
}
