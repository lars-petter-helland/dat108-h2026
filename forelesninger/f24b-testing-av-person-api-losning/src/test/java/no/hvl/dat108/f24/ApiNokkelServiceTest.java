package no.hvl.dat108.f24;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiNokkelServiceTest {

    private ApiNokkelService apiNokkelService = new ApiNokkelService();

    @Test
    void nullOgTomStringErIkkeGyldig() {
        assertFalse(apiNokkelService.erGyldig(null));
        assertFalse(apiNokkelService.erGyldig(""));
    }

    @Test
    void feilNokkelErIkkeGyldig() {
        assertFalse(apiNokkelService.erGyldig("frodo"));
    }

    @Test
    void korrektNokkelErGyldig() {
        assertTrue(apiNokkelService.erGyldig("gandalf"));
    }
}
