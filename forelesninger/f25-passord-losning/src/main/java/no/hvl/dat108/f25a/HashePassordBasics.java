package no.hvl.dat108.f25a;

import org.bitcoinj.base.Base58;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HexFormat;

public class HashePassordBasics {

	public static void main(String[] args)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {

		String[] noenPassord = {"Hallaien", "pwd123", "Peace&Love"};

		for (String passord : noenPassord) {

			byte[] passordSomBytes = passord.getBytes("UTF-8");
			IO.println("\nPassord:       " + passord);
            IO.println(".. som bytes:  " + Arrays.toString(passordSomBytes));

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(passordSomBytes);
            IO.println("SHA-256-hash:  " + Arrays.toString(hash));

			//Hex: 0 (0000) 1 2 ... 9 A B ... F (1111). Ikke case-sensitiv.
            String hex = HexFormat.of().withUpperCase().formatHex(hash);
            IO.println(".. som hex:    " + hex);

			//Base64: A (000000) B C ... Z a b c ... z 0 1 .. 9 + / (111111)
			String base64 = Base64.getEncoder().encodeToString(hash);
            IO.println(".. som base64: " + base64);

            /* Base58: Modifisert Base64 laget spesielt for mennesker.
             * Den fjerner tegn som ser like ut: 0 (null), O (stor o),
             * I (stor i) og l (liten L), i tillegg til + og /.
             * Bruksområde: Bitcoin og andre kryptovalutaer for å unngå tastefeil i adresser.
             */
            String base58 = Base58.encode(hash);
            IO.println(".. som base58: " + base58);



		}
	}

}
