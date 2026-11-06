package no.hvl.dat108.f24a.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;

import no.hvl.dat108.f24a.Fiat;
import no.hvl.dat108.f24a.Krypto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class) 
public class KryptoMockitoTest {

    //SUT
	private Kryptokalkulator kk;
	
	@Mock
	private Prisfinner prisfinnerStub;
	
	@BeforeEach
	public void injectFreshMock() {
		kk = new Kryptokalkulator(prisfinnerStub);
	}
	
	@Test
	public void sjekkAtUtregningVirker() throws IOException {
		
		when(prisfinnerStub.finnPris("ETH", "USD"))
				.thenReturn(3000.03);
		
		double belop = kk.kjope(0.1, Krypto.ETH, Fiat.USD);
		
//		assertEquals(300.003, belop);
		
		/* Fordi vi jobber med desimaltall kan/vil det alltid bli
		 * avrundingsfeil. Kanskje det holder å sjekke at det er 
		 * korrekt med en feilmargin på 0.0000001.
		 */
		assertEquals(300.003, belop, 0.00000001d);
	}
	
	@Test
	public void sjekkAtDetBlirMinus1HvisIOException() 
			throws IOException {
		
		when(prisfinnerStub.finnPris("ETH", "USD"))
				.thenThrow(IOException.class);
		
		double belop = kk.kjope(0.1, Krypto.ETH, Fiat.USD);
		
		assertEquals(-1.0, belop);
	}
	
}