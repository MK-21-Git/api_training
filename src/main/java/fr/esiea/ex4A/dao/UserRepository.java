package fr.esiea.ex4A.dao;

import fr.esiea.ex4A.model.AgifyData;
import fr.esiea.ex4A.model.UserData;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    final List<UserData> allUsers = new ArrayList<>();

    public List<UserData> getUsers() {
        return allUsers;
    }
    public List <UserData> addUser(UserData userData)
    {
        this.allUsers.add(userData);
        return this.allUsers;
    }
    public List<UserData> getMatchesUsers(String name, String country, Integer age) {
        List<UserData> result = new ArrayList<>();
        UserData tempUserData = new UserData(null, name, null, country, null, null);
        if (this.getUsers().size() > 0 && this.allUsers.contains(tempUserData) ) {
            UserData userData = this.allUsers.get(this.allUsers.indexOf(tempUserData));
            for (UserData u : this.allUsers) {
                Integer ageU = CacheAgify.agifyBD.get(new AgifyData(u.getUserName(), null, null, u.getUserCountry()));
                if (!userData.equals(u)
                    && userData.getUserSexPref().equals(u.getUserSex())
                    && userData.getUserSex().equals(u.getUserSexPref())
                    && ageU >= age - 4 && ageU <= age + 4) {
                    result.add(u);
                }
            }
        }
        return result;
    }

}
