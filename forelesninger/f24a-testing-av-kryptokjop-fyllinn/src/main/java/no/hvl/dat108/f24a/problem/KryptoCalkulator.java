package no.hvl.dat108.f24a.problem;

import java.io.IOException;

import org.springframework.stereotype.Service;

import no.hvl.dat108.f24a.Fiat;
import no.hvl.dat108.f24a.Krypto;

@Service
public class KryptoCalkulator {
	
	public double kjope(double antall, Krypto krypto, Fiat fiat) {
		try {
			return antall * PrisFinderKucoin.finnPris(krypto.name(), fiat.name());
			
		} catch (IOException e) {
			return -1;
		}
	}
}
