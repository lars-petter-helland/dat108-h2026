package no.hvl.dat108.f24a.solution;

import no.hvl.dat108.f24a.Fiat;
import no.hvl.dat108.f24a.Krypto;

import java.io.IOException;

public class PrisfinnerSomKasterUnntak implements Prisfinner {

    /*
     * Forventer et kall for å finne ETH-kurs i USD.
     * I så fall: Kaster et IOException som simulerer et nettverksproblem.
     */
    @Override
	public double finnPris(String kryptoCode, String fiatCode) throws IOException {
		
		if (!Krypto.ETH.name().equals(kryptoCode) 
				|| !Fiat.USD.name().equals(fiatCode)) {
			throw new IllegalArgumentException();
		}
		throw new IOException();
	}

}
