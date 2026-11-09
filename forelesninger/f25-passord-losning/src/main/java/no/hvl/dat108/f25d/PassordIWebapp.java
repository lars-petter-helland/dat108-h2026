package no.hvl.dat108.f25d;

import no.hvl.dat108.f25c.Bruker;
import no.hvl.dat108.f25c.PassordService;

public class PassordIWebapp {
	
    private static PassordService ps = new PassordService();

	public static void main(String[] args) {

		/*
		 * Dette er en demo av hvordan PassordService kan brukes i en webapp.
		 * 
		 * En bruker registrerer seg med brukernavn og passord. Et brukerobjekt
		 * med brukernavn m.m., salt og hashet salt+passord lagres i databasen.
		 * Brukeren blir logget på.
		 * 
		 * Tiden går ... og brukeren er logget ut.
		 * 
		 * Bruker ønsker å logge inn igjen, og oppgir brukernavn og loginnPassord.
		 * Et brukerobjekt blir hentet fra databasen basert på oppgitt brukernavn.
		 * loginnPassord saltes, hashes og sammenlignes med det lagrede hashede
		 * passordet for denne brukeren, osv ...
		 * 
		 * NB! Sjekk innholdet i metodene PassordService.hashMedSalt() og
		 * PassordService.validerMedSalt(). De er pensum!!!
		 */

		// Bruker registrerer seg på nettsiden og trykker "Submit"
		String brukernavn    = "Per";
		String passord = "1234567890";
		
		// I en @Post-metode i en @Controller gjøres følgende:
		
		/* 1)
		   Validerer brukernavn og passord. Her bør man kanskje kreve at passordet
		 		- Er over en viss lengde, f.eks. 10 tegn
		 		- Ikke finnes i liste over x mest brukte passord

		   2)
		   Oppretter et Bruker-objekt
		 */
		String salt = ps.genererTilfeldigSalt();
		String passordHash = ps.hashMedSalt(passord, salt);
		Bruker bruker = new Bruker(brukernavn, passordHash, salt);
		
		/* 3)
		 * Deretter lagres den nye brukeren i databasen
		 * og brukeren blir f.eks. logget inn.
		 */

		// Liten testutskrift for å se hva vi har ...
		System.out.println(bruker);

		
		// ... tiden går ...

		
		// Nå prøver bruker å logge inn igjen
//		String loginnBrukernavn = "Per";
//      Bruker med brukernavn "Per" blir hentet fra databasen
		
//		String loginnPassord = "hallo"; //Feil passord
		String loginnPassord = "1234567890"; //Riktig passord
		
		// Man sjekker om det er en match på passordet
		String lagretSalt = bruker.getSalt();
		String lagretPassordHash = bruker.getPassordhash();
		
		if (ps.validerMedSalt(loginnPassord, lagretSalt, lagretPassordHash)) {
			System.out.println("\n" + loginnPassord + " var riktig passord. Innlogging OK.");
		} else {
			System.out.println("\n" + loginnPassord + " var feil passord. :(");
		}
	}
}
