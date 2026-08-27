package no.hvl.dat108.f04;

import no.hvl.dat108.f04.Person;

import static no.hvl.dat108.f04.People.people;

public class Eksempel8 {
	
	public static void main(String[] args) {
		
		/* Summen av aldrene til personene i people-listen */
		// Vi ser på 4 varianter:
		// 1) reduce med +
		// 2) reduce med sum
		// 3) reduce med sum og metodereferanse
		// 4) IntStream og sum
		System.out.println("Sum alder er " + sumAlder);
		
		/* En streng med alle initialene, "CD LC TC CB MA" - reduce med + */
		System.out.println(inits);

		/* Alle forbokstavene i fornavnene i en streng "CLTCM" - reduce med concat */
		System.out.println(forboks);

		/* Antall personer over 50 år - count() */
		System.out.println(antallOver50);

		/* Om vi har data som matcher
			anyMatch(pred), allMatch(pred), noneMatch(pred) */
		//Er alle over 30 år?
		System.out.println(alleOver30);
		//Er noen over 60 år?
		System.out.println(noenOver60);
	}
}



