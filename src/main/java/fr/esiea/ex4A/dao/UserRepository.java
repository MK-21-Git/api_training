package fr.esiea.ex4A.dao;

import fr.esiea.ex4A.model.UserInfo;
import fr.esiea.ex4A.model.UserMatch;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    final List<UserInfo> allUsers = new ArrayList<>();

    public List<UserInfo> getUsers() {
        return allUsers;
    }

    public List<UserInfo> addUser(UserInfo userInfo) {
        if(!this.allUsers.contains(userInfo))
            this.allUsers.add(userInfo);
        return this.allUsers;
    }

    UserInfo getUserInfo(String name, String country) {
        int index = this.allUsers.indexOf(new UserInfo(null, name, null, country, null, null, 0));
        return index >= 0 ? this.allUsers.get(index) : null;
    }

    public List<UserMatch> getMatchesUsers(String name, String country) {
        List<UserMatch> result = new ArrayList<>();
        UserInfo userInfo = getUserInfo(name, country);
        if(userInfo != null) {
            for (UserInfo u : this.allUsers) {
                if (userInfo.isMatch(u)) {
                    result.add(u.asMatch());
                }
            }
        }
        return result;
    }
}
