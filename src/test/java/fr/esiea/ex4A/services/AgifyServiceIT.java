package fr.esiea.ex4A.services;

import fr.esiea.ex4A.dao.AgifyRepository;

import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AgifyServiceIT {
    @Mock
    private AgifyRepository agifyRepository;

    @InjectMocks
    private AgifyService agifyService;

    @Test
    public void whenValidNameAndCountry_thenAgifyUserFound() throws IOException {
        AgifyUser agifyUser = new AgifyUser("Alex", 44, 55, "FR");
        Mockito.when(agifyRepository.callAgify("alex", "FR")).thenReturn(agifyUser);
        AgifyUser found = agifyService.callAgify("alex", "FR");
        assertThat(found.getName()).isEqualTo("Alex");
        assertThat(found.getAge()).isEqualTo(44);
    }

    @Test
    public void whenValidKey_thenAgifyUserFoundInCache()  {
        AgifyUser agifyUser = new AgifyUser("Gabriel", 22, 55, "FR");
        Mockito.when(agifyRepository.countainAgifyUser(new Key("Gabriel", "FR"))).thenReturn(agifyUser);
        AgifyUser found = agifyService.existAgifyUser(new Key("Gabriel", "FR"));
        assertThat(found.getName()).isEqualTo("Gabriel");
        assertThat(found.getAge()).isEqualTo(22);
    }
}
