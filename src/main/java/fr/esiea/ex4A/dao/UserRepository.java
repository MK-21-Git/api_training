package fr.esiea.ex4A.dao;

import fr.esiea.ex4A.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }
    public List <User> addUser(User user)
    {
        this.users.add(user);
        return users;
    }
    public List<User> getMatchesUsers(User user)
    {
        List <User> result = new ArrayList<>();
        for (User u: this.users) {
            if (u.getUserCountry().equals(user.getUserCountry())){
                result.add(u);
            }
        }
        return result;
    }
}
