package no.hvl.dat108.f24;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonDtoTest {

    private Validator validator = Validation
            .buildDefaultValidatorFactory().getValidator();

    @Test
    public void gyldigFornavn() {
        PersonRequestDTO person = new PersonRequestDTO("Per", "Olsen");
        assertTrue(erGyldigEgenskap(person, "fornavn"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "Ab", "   "})
    public void ugyldigeForNavn(String fornavnFraListe) {
        // Denne testen blir kjørt (i en løkke) for alle navn listet opp i ValueSource.
        PersonRequestDTO person = new PersonRequestDTO(fornavnFraListe, "Olsen");
        assertFalse(erGyldigEgenskap(person, "fornavn"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "HansHansen", "LarsP", "X  "})
    public void gyldigeNavn(String fornavnFraListe) {
        // Denne testen blir kjørt (i en løkke) for alle navn listet opp i ValueSource.
        PersonRequestDTO person = new PersonRequestDTO(fornavnFraListe, "Olsen");
        assertTrue(erGyldigEgenskap(person, "navn"));
    }





    /***********************************************************************/

    /*
     * Hjelpemetode til bruk i testingen.
     * Sjekker feltet med navn feltnavn er gyldig for person.
     */
    private boolean erGyldigEgenskap(PersonRequestDTO person, String egenskap) {

        Function<ConstraintViolation<PersonRequestDTO>,String>
                propsWithViolations = v -> v.getPropertyPath().toString();

        return !validator.validate(person).stream()		//Strøm av alle feil (ConstraintViolations)
                .map(propsWithViolations)				//Strøm av alle "egenskaper" med feil
                .toList()								//Samlet opp i en liste av "egenskaper" med feil
                .contains(egenskap);					//Om den "egenskapen" vi lurer på har feil
    }
}
