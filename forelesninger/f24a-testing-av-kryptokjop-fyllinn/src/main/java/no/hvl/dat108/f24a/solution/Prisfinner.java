package no.hvl.dat108.f24a.solution;

import java.io.IOException;

public interface Prisfinner {
	double finnPris(String kryptoCode, String fiatCode) throws IOException;
}
