package fr.esiea.ex4A.dao;

import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import fr.esiea.ex4A.model.UserInfo;
import fr.esiea.ex4A.model.UserMatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepository();

    @BeforeEach
    public void setUp() {
        userRepository.addUser(new UserInfo("jade@google.com", "Jade", "Jade", "FR", "F", "H", 63));
        userRepository.addUser(new UserInfo("sebastien@google.com", "Sébastien", "sebTwit", "FR", "H", "F", 44));
        userRepository.addUser(new UserInfo("alice@google.com", "Alice", "Alice", "FR", "H", "F", 41));
    }


    @Test
    public void whenFindByNameAndCountry_thenReturnUserInfo() {
        // when
        UserInfo found = userRepository.getUserInfo("Sébastien", "FR");

        // then
        assertThat(found.getUserAge()).isEqualTo(44);
        assertThat(found.getUserEmail()).isEqualTo("sebastien@google.com");
    }

    @Test
    public void whenAddUserInfo_thenReturnListWithUserInfo() {
        UserInfo newUser = new UserInfo("cedric@google.com", "Cédric", "Cédric", "FR", "H", "F", 40);

        assertThat(newUser.asMatch()).isEqualTo(new UserMatch("Cédric", "Cédric", 40));
        // Before Add
        assertThat(userRepository.getUsers().size()).isEqualTo(3);
        // when
        List<UserInfo> listUsers = userRepository.addUser(newUser);

        // then
        assertThat(listUsers.size()).isEqualTo(4);
        assertThat(listUsers.contains(newUser)).isTrue();

    }

    @Test
    public void whenSearchMatcheUsers_thenReturnListUserMatch() {
        UserInfo userToSearch = new UserInfo("emma@google.com", "Emma", "Emma", "FR", "F", "H", 42);
        // Before
        userRepository.addUser(userToSearch);

        // when
        List<UserMatch> listUserMatch = userRepository.getMatchesUsers("Emma", "FR");

        // then
        assertThat(listUserMatch.size()).isEqualTo(2);
        //assertThat(listUserMatch.get(0).userAge, gre);

    }
}
