package no.hvl.dat108.f25c;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/*
 * DatatypeConverter er ikke lenger med i Java etter Java 9.
 * 
 * Siden eksemplet er et helt "plain" Java-prosjekt, må vi 
 * derfor ha med biblioteket lib\jakarta.xml.bind-api-2.3.2.jar
 * slik at vi kan bruke DatatypeConverter.
 * 
 * I Spring web-applikasjoner trenger vi ikke denne jar-filen,
 * siden den allerede er en del av Spring MVC.
 */

//@Service
public class PassordService {

	/**
	 * @return et tilfeldig generert 16 bytes salt ved å bruke SHA1PRNG
	 */
	public String genererTilfeldigSalt() {
	    SecureRandom sr;
	    byte[] salt = new byte[16];
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		    sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	    return HexFormat.of().withUpperCase().formatHex(salt);
	}

	/**
	 * Genererer en kryptografisk hash for gitt passord og salt.
	 * 
	 * Algoritmen som brukes er PBKDF2WithHmacSHA1
	 * 	- PBKDF2: Password-Based-Key-Derivation-Function
	 *  - WithHmac: HMAC står for Keyed-Hash Message Authentication Code
	 *  - SHA1: SHA1 hashing-algoritme
	 * 
	 * Det itereres 1000 ganger.
	 * Output fra denne algoritmen er 256 bits, dvs. 32 bytes.
	 * 
	 * Se https://en.wikipedia.org/wiki/PBKDF2 for mer info om virkemåte.
	 * 
	 * Til slutt omgjøres byte-tabellen til en HEX-streng på 64 tegn/siffer.
	 *  
	 * @param passord
	 * @param salt
	 * @return en 64 tegn lang HEX-streng som representerer en 32 byte/256 bit hash.
	 */
	public String hashMedSalt(String passord, String salt) { 
		
		/* Burde vært en del strengere med hva som er gyldig input her, 
		 * f.eks. kreve en viss lengde på passord som skal sjekkes, og 
		 * sjekke at salt har gyldig format (lengde + tegnsett) ++.
		 * 
		 * Men tok altså en snarvei her ... :(
		 */
		if (passord == null || salt == null) {
			throw new IllegalArgumentException();
		}
		
		char[] passchar = passord.toCharArray();
		byte[] saltbytes = HexFormat.of().parseHex(salt);
		
		byte[] keyhash = null;
		try {
			PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, 1000, 256);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			keyhash = skf.generateSecret(pks).getEncoded();
			
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return HexFormat.of().withUpperCase().formatHex(keyhash);
	}
	
	/**
	 * Sjekker om et passord matcher en hash generert med korresponderende 
	 * hashMedSalt(). 
	 * 
	 * @param passord - Passord som skal sjekkes
	 * @param salt - Saltet som ble brukt ved generering av passordhash
	 * @param passordhash - Det "lagrete" passordet 
	 * @return om passordet matcher
	 */
	public boolean validerMedSalt(
			String passord, String salt, String passordhash) {
		
		/* Burde vært en del strengere med hva som er gyldig input her, 
		 * f.eks. kreve en viss lengde på passord som skal sjekkes, og 
		 * sjekke at salt og hash har gyldig format (lengde + tegnsett) ++.
		 * 
		 * Men tok altså en snarvei her ... :(
		 */
		if (passord == null || salt == null || passordhash == null) { 
			throw new IllegalArgumentException();
		}
		
		return passordhash.equals(hashMedSalt(passord, salt));
	}
}
