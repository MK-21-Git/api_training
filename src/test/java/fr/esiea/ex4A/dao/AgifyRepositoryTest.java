package fr.esiea.ex4A.dao;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import fr.esiea.ex4A.model.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AgifyRepositoryTest {

    @Mock
    private AgifyClient agifyClient;

    private final AgifyRepository agifyRepository = new AgifyRepository(agifyClient);

    @BeforeEach
    public void setUp()  {
        agifyRepository.addAgifyUser(new AgifyUser("Jade", 63, 234, "FR"));
        agifyRepository.addAgifyUser(new AgifyUser("Sébastien", 44, 345, "FR"));
        agifyRepository.addAgifyUser(new AgifyUser("Alice", 41, 456, "FR"));
    }

    @Test
    public void whenAddUserInfo_thenUserInCache() {
        AgifyUser cedric = new AgifyUser("Cédric", 40, 309, "FR");
        //Before Add
        assertThat(agifyRepository.agifyBD.size()).isEqualTo(3);

        // when
        agifyRepository.addAgifyUser(cedric);

        // when
        assertThat(agifyRepository.agifyBD.size()).isEqualTo(4);
        assertThat(agifyRepository.countainAgifyUser(new Key("Cédric", "FR"))).isEqualTo(cedric);
    }

   /* @Test
    public void whenCallAgifyWithNameAndCountry_thenAgifyUserFound() throws IOException {

        AgifyUser found = agifyRepository.callAgify("Alex", "FR");
        assertThat(agifyRepository.agifyBD.size()).isEqualTo(4);
    }
*/

}
