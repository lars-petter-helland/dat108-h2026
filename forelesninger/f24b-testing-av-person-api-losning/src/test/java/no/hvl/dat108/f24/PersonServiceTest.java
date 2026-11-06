package no.hvl.dat108.f24;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    //SUT
    private PersonService personService;

    @BeforeEach
    void oppsett() {
        //Inject mock-objekt
        personService = new PersonService(personRepository);
    }

    @Test
    void hentEtterIdReturnererOptPersonOmFinnes() {

        //1 - Arrange
        when(personRepository.findById(anyLong()))
                .thenReturn(Optional.of(new Person(5L, "Atle", "Patle")));

        //2 - Act
        Optional<Person> personIEske = personService.hentEtterId(5L);

        //3 - Assert
        assertTrue(personIEske.isPresent());

        Person p = personIEske.get();
        assertEquals(5L, p.getId());
        assertEquals("Atle", p.getFornavn());
        assertEquals("Patle", p.getEtternavn());
    }

    @Test
    void hentEtterIdReturnererOptEmptyOmIkkeFinnes() {

        //1 - Arrange
        when(personRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        //2 - Act
        Optional<Person> personIEske = personService.hentEtterId(5L);

        //3 - Assert
        assertTrue(personIEske.isEmpty());
    }

    @Test
    void hentDeMedFornavnSomBegynnerMed() {

        when(personRepository.findAll()).thenReturn(List.of(
                new Person(1L, "Atle", "Atlesen"),
                new Person(2L, "Elin", "Elinsen"),
                new Person(3L, "Knut", "Knutsen"),
                new Person(4L, "Arne", "Arnesen"),
                new Person(5L, "Anne", "Annesen")));

        List<Person> resultat = personService.hentDeMedFornavnSomBegynnerMed("A");

        assertEquals(3, resultat.size());

        List<String> fornavnene = resultat.stream().map(Person::getFornavn).toList();
        assertTrue(fornavnene.contains("Atle"));
        assertTrue(fornavnene.contains("Anne"));
        assertFalse(fornavnene.contains("Elin"));
    }

}
