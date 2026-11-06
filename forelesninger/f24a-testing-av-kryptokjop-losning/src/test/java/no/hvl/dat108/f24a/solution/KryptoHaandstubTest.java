package no.hvl.dat108.f24a.solution;

import no.hvl.dat108.f24a.Fiat;
import no.hvl.dat108.f24a.Krypto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KryptoHaandstubTest {

    //System Under Test (SUT)
    private Kryptokalkulator kk;

	@Test
	public void sjekkAtUtregningVirker() {

        kk = new Kryptokalkulator(new PrisfinnerMedHardkodetPris());
		
		double belop = kk.kjope(0.1, Krypto.ETH, Fiat.USD);
		
		/*
		 * Fordi Kryptokalkulatoren bruker vår PrisfinnerTestStub
		 * til å finne pris (som alltid har kurs = 3000.03) må vi
		 * forvente at beløpet blir 300.003. Da sjekker vi om
		 * utregningen har virket:
		 */
//		assertEquals(300.003, belop);
		
		/* Fordi vi jobber med desimaltall kan/vil det alltid bli
		 * avrundingsfeil. Kanskje det holder å sjekke at det er 
		 * korrekt med en feilmargin på 0.0000001.
		 */
		assertEquals(300.003, belop, 0.0000001d);
	}
	
	@Test
	public void sjekkAtDetBlirMinus1HvisIOException() {

        kk = new Kryptokalkulator(new PrisfinnerSomKasterUnntak());

		double belop = kk.kjope(0.1, Krypto.ETH, Fiat.USD);
		assertEquals(-1.0, belop);
	}

	@Test
	public void sjekkAtPrisfinnerKallesMedETHogUSD() {

        kk = new Kryptokalkulator(new PrisfinnerMedHardkodetPris());

		/* PrisfinnerMedHardkodetPris forventer et kall med ETH og USD.
		   Hvis Kryptokalkulator ikke videreformidler parametrene
		   riktig, vil PrisfinnerMedHardkodetPris si fra om dette. */
		
		// NOK
		assertThrows(IllegalArgumentException.class, 
				() -> kk.kjope(0.1, Krypto.ETH, Fiat.NOK));
		// BTC	
		assertThrows(IllegalArgumentException.class, 
				() -> kk.kjope(0.1, Krypto.BTC, Fiat.USD));
	}
}