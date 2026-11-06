package no.hvl.dat108.f24;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * Noe lesestoff:
 *      https://codingnomads.com/java-spring-mockmvc
 *      https://codingnomads.com/api-testing-mockmvc-jsonpath-example
 *      https://www.trinitylogic.co.uk/blog/spring-boot-webmvctest-mockmvc-rest-controllers/
 *      https://howtodoinjava.com/spring-boot/testing/spring-boot-mockmvc-example/
 *      https://docs.spring.io/spring-framework/reference/testing/mockmvc.html
 */

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonService personService;

    //Ikke i bruk i GET-requesten som testes, men må likevel lages.
    @MockitoBean
    private ApiNokkelService apiNokkelService;

    /*
     * Velger meg én av API.metode å teste i dette eksempelet.
     * Skulle det vært gjort skikkelig burde kanskje alle utfall for alle
     * metodene vært testet.
     *
     * Velger @GetMapping("/{id}"), altså f.eks. GET /api/personer/3
     * To mulige utfall:
     *  - 200 OK med funnet person i JSON-body
     *  - 404 Not Found og tom body
     */

    @Test
    public void getPersonMedSuksess() throws Exception {

        //Arrange
        //TODO Denne kan vi gjøre sammen

        //Act og Assert
        mockMvc.perform(get("/api/personer/{id}", 3))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("fornavn").value("Donald"));
    }

    @Test
    public void getPersonSomIkkeFinnes() throws Exception {

        //Arrange
        //TODO Denne kan vi gjøre sammen

        //Act og Assert
        //TODO Denne kan vi gjøre sammen
    }

}
