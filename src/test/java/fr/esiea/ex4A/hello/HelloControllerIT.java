package fr.esiea.ex4A.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.esiea.ex4A.dao.UserRepository;
import fr.esiea.ex4A.model.AgifyUser;
import fr.esiea.ex4A.model.Key;
import fr.esiea.ex4A.model.UserInfo;
import fr.esiea.ex4A.model.UserMatch;
import fr.esiea.ex4A.services.AgifyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class HelloControllerIT {

    private final MockMvc mockMvc;

    @MockBean
    private HelloRepository repository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AgifyService agifyService;

    HelloControllerIT(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void inscription_delegates_to_repository_when_name_param_is_present() throws Exception {
        assertThat(userRepository).isNotNull();
        //user n'existe pas en cache
        when(agifyService.existAgifyUser(new Key("Marie", "FR"))).thenReturn(null);
        when(agifyService.callAgify("Marie", "FR")).thenReturn(new AgifyUser("Marie", 64, 787, "FR"));

        mockMvc
            .perform(MockMvcRequestBuilders
                .post("/api/inscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "userEmail": "Marie@truc.com",
                        "userName": "Marie",
                        "userTweeter": "Marie",
                        "userCountry": "FR",
                        "userSex": "F",
                        "userSexPref": "H"
                    }
                    """))
                .andExpect(status().isOk())
                .andExpect(content().string("user Marie added successfully"));

        //verify(userRepository).getMatchesUsers("toto", "FR");

    }

    @Test
    void matches_delegates_to_repository_when_params_is_present() throws Exception {
        assertThat(userRepository).isNotNull();
        when(userRepository.getMatchesUsers("Marie", "FR")).thenReturn(
            List.of(
                new UserMatch("Philippe", "Philippe", 66),
                new UserMatch("Patrick", "Patrick", 61)));

        mockMvc
            .perform(MockMvcRequestBuilders.get("/api/matches?userName=Marie&userCountry=FR"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].userName").value("Philippe"))
            .andExpect(jsonPath("$[0].userAge").value("66"));

    }

    @Test
    void hello_delegates_to_repository_when_name_param_is_present() throws Exception {
        when(repository.getHelloFor(any())).thenReturn(new HelloData("test"));

        mockMvc
            .perform(MockMvcRequestBuilders.get("/hello?name=test"))
            .andExpect(status().isOk())
            .andExpect(content().json("""
                        {
                            "type":"hello",
                            "name":"test",
                            "completeSentence":"hello test!"}
                        }
                        """));

        verify(repository).getHelloFor("test");
    }

    @Test
    void hello_delegates_to_random_when_name_param_is_absent() throws Exception {
        when(repository.randomHello()).thenReturn(new HelloData("randomtest"));

        mockMvc
            .perform(MockMvcRequestBuilders.get("/hello"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.type").value("hello"))
            .andExpect(jsonPath("$.name").value("randomtest"))
            .andExpect(jsonPath("$.completeSentence").value(allOf(startsWith("hello"), endsWith("!"))));

        verify(repository).randomHello();
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
