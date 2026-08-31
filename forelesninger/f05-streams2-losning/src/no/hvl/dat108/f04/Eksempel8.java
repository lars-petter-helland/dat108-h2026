package no.hvl.dat108.f04;

import no.hvl.dat108.f04.Person;

import static no.hvl.dat108.f04.People.people;

public class Eksempel8 {
	
	public static void main(String[] args) {
		
		/* Summen av aldrene til personene i people-listen */
		
//		int sumAlder = people.stream()
//				.map(p -> p.age())
//				.reduce(0, (akku, x) -> akku + x);
		
//		int sumAlder = people.stream()
//				.map(p -> p.age())
//				.reduce(0, (akku, x) -> Integer.sum(akku, x));
		
//		int sumAlder = people.stream()
//				.map(Person::age)
//				.reduce(0, Integer::sum);
		
		int sumAlder = people.stream().mapToInt(Person::age).sum();
		
		System.out.println("Sum alder er " + sumAlder);
		
		/* En streng med alle initialene, "CD LC TC CB MA" */
		String inits = people.stream()
				.map(p -> "" + p.firstName().charAt(0) + p.lastName().charAt(0))
				.reduce("", (akku, s) -> akku  + s + " "); //collect like greit her
		System.out.println(inits);

		//Alle forbokstavene i fornavnene i en streng "CLTCM" - reduce()
		String forboks = people.stream()
				.map(p -> "" + p.firstName().charAt(0))
				.reduce("", String::concat); //concat i stedet for +
		System.out.println(forboks);

		//Antall personer over 50 år - count()
		long antallOver50 = people.stream()
				.filter(p -> p.age() > 50)
				.count();
		System.out.println(antallOver50);

		//Om vi har data som matcher
		//	anyMatch(pred), allMatch(pred), noneMatch(pred)

		//Er alle over 30 år?
		boolean alleOver30 = people.stream()
				.allMatch(p -> p.age() > 30);
		System.out.println(alleOver30);

		//Er noen over 60 år?
		boolean noenOver60 = people.stream()
				.anyMatch(p -> p.age() > 60);
		System.out.println(noenOver60);

	}	
}



