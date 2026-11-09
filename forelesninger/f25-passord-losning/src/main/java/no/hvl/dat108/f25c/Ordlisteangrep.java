package no.hvl.dat108.f25c;

import java.io.IOException;
import java.util.List;

public class Ordlisteangrep {

	private static PassordService ps = new PassordService();

	public static void main(String... blablabla) throws IOException {

		/*
		 * Fra https://github.com/danielmiessler/SecLists/tree/master/Passwords/Common-
		 * Credentials Har kopiert en liste med de 10000 mest populære passordene.
		 */
        String vanligePassord10k = "10k-most-common.txt";
        String vanligePassord100k = "100k-most-used-passwords-NCSC.txt";
		List<String> passordliste = Filhjelper.lesInnPassordFraFil(vanligePassord100k);

		/* Hjelpemetode, se nederst */
		List<Bruker> brukerliste = opprettNoenBrukere();

		for (Bruker bruker : brukerliste) {
			System.out.print("\n" + bruker.getBrukernavn() + " -> ");
			for (String passord : passordliste) {
				if (ps.validerMedSalt(passord, bruker.getSalt(), bruker.getPassordhash())) {
					System.out.print(bruker.getBrukernavn() + " har passord '" + passord + "'");
                    break;
				}
			}
		}
		System.out.println("\nFerdig");
	}

	private static List<Bruker> opprettNoenBrukere() {

		/* Forhåndsgenererer noen tilfeldige salt */
		String[] saltTab = new String[10];
		for (int i = 0; i < saltTab.length; i++) {
			saltTab[i] = ps.genererTilfeldigSalt();
		}

		/*
		 * Noen oppdiktede brukere. Alle blir lagret med saltet, stretched og hashed
		 * passord.
		 */
		return List.of(
				new Bruker("atle", ps.hashMedSalt("passord", saltTab[0]), saltTab[0]),
				new Bruker("brit", ps.hashMedSalt("qwerty", saltTab[1]), saltTab[1]),
				new Bruker("carl", ps.hashMedSalt("sdfkjsd lfkj sdf", saltTab[2]), saltTab[2]),
				new Bruker("dina", ps.hashMedSalt("123456", saltTab[3]), saltTab[3]),
				new Bruker("erik", ps.hashMedSalt("HaLlO", saltTab[4]), saltTab[4]),
				new Bruker("fred", ps.hashMedSalt("Hei på deg", saltTab[5]), saltTab[5]),
				new Bruker("geir", ps.hashMedSalt("madmax", saltTab[6]), saltTab[6]),
				new Bruker("hans", ps.hashMedSalt("vladimir", saltTab[7]), saltTab[7]),
				new Bruker("inge", ps.hashMedSalt("Liverpool", saltTab[8]), saltTab[8]),
				new Bruker("jens", ps.hashMedSalt("liverpool", saltTab[9]), saltTab[9]));
	}
}
