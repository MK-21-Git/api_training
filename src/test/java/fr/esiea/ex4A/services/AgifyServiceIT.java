package fr.esiea.ex4A.services;

import fr.esiea.ex4A.agify.AgifyClient;
import fr.esiea.ex4A.dao.UserRepository;
import fr.esiea.ex4A.model.AgifyUser;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AgifyServiceIT {
    private final MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private AgifyClient agifyClient;

    @BeforeAll
    public void setUp() {
        AgifyUser alex = new AgifyUser("alex", 44, 55, "FR");

        /*Mockito.when(userRepository.getData("alex", "FR"))
            .thenReturn(alex);*/
    }


    AgifyServiceIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }


}
