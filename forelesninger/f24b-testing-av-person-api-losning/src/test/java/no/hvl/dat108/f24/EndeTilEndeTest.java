package no.hvl.dat108.f24;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * @SpringBootTest laster hele konteksten, dvs. det er en full
 * ende-til-ende-test.
 * 
 * @WebMvcTest kan brukes for å enhetsteste controller, men da
 * må evt. servicer og repos mockes med @MockitoBean. Kanskje det
 * kunne vært noe å testet ut.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class EndeTilEndeTest {

    @Autowired
    private MockMvc mockMvc;

    // Vi kan injisere repositoriet direkte i testen hvis vi trenger å
    // gjøre noe i databasen før/etter vi tester. f.eks:
    //  personRepository.deleteAll();
    //  Person lagretPerson = personRepository.save(...);
    //
    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testHentEnPerson() throws Exception {
        mockMvc.perform(get("/api/personer/{id}", 3))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.fornavn").value("EndaEnNy"));
    }
}

//    @Test
//    public void testPersonerHtml() throws Exception {
//        mockMvc.perform(get("/personerHtml"))
//            .andExpect(status().isOk())
//            .andExpect(view().name("navneliste"))
//            .andExpect(model().attributeExists("navneliste"));
//    }
