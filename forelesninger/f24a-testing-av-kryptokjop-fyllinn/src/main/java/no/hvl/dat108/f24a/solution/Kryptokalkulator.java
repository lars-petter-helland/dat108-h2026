package no.hvl.dat108.f24a.solution;

import java.io.IOException;

import org.springframework.stereotype.Service;

import no.hvl.dat108.f24a.Fiat;
import no.hvl.dat108.f24a.Krypto;

@Service
public class Kryptokalkulator {
	
	private final Prisfinner prisfinner;

    public Kryptokalkulator(Prisfinner prisfinner) {
        this.prisfinner = prisfinner;
    }

    public double kjope(double antall, Krypto krypto, Fiat fiat) {
		try {
			return antall * prisfinner.finnPris(krypto.name(), fiat.name());
			
		} catch (IOException e) {
			return -1;
		}
	}
}
