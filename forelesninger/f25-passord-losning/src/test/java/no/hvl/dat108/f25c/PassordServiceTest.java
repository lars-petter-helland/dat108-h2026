package no.hvl.dat108.f25c;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PassordServiceTest {
	
	private static final String RIKTIG_PASSORD = "Abc de fgh";
	private static final String FEIL_PASSORD = "Java er kjedelig";
	
	private PassordService ps = new PassordService();
	
	private String salt;
	private String hash;
	
	@BeforeEach
	void setup() {
        salt = ps.genererTilfeldigSalt();
        hash = ps.hashMedSalt(RIKTIG_PASSORD, salt);
	}

	@Test
	void riktigPassordVirker() {
        assertTrue(ps.validerMedSalt(RIKTIG_PASSORD, salt, hash));
	}

	@Test
	void feilPassordVirkerIkke() {
        assertFalse(ps.validerMedSalt(FEIL_PASSORD, salt, hash));
	}
	
	@Test
	void tomHashVirkerIkke() {
        assertFalse(ps.validerMedSalt(FEIL_PASSORD, salt, ""));
	}
	
	@Test
	void nullPassordKasterUnntak1() {
        assertThrows(IllegalArgumentException.class, 
        		() -> ps.hashMedSalt(null, salt));
	}
	
	@Test
	void nullPassordKasterUnntak2() {
        assertThrows(IllegalArgumentException.class, 
        		() -> ps.validerMedSalt(null, salt, hash));
	}
	
	@Test
	void nullHashKasterUnntak() {
        assertThrows(IllegalArgumentException.class, 
        		() -> ps.validerMedSalt(RIKTIG_PASSORD, salt, null));
	}
}
