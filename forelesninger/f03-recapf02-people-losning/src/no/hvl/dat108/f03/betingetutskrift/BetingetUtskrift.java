package no.hvl.dat108.f03.betingetutskrift;

import java.util.function.Predicate;

/* Lars-Petter Helland, 17. august 2026
 * 
 * Uke34 - Oppgave 4
 * 
 * Her er en liten oppgave dere kan prøve på labben og frem til 
 * mandag for å se om dere har fått med dere hovedideen med 
 * lambda-uttrykk.
 * 
 * Oppgavetekst:
 * 
 * Lag en generell metode betingetUtskrift(...) som tar inn en streng 
 * og et (false | true) lambda-uttrykk og som skriver ut strengen kun 
 * hvis uttrykket er sant.
 * 
 * Lag en main(...)-metode som tester dette ut med et par ulike input, 
 * f.eks. krav om at stringen begynner på "X" eller at den er lengre 
 * enn 3 tegn.
 */

/*
 * Merk at vi her bruker funksjonstypen Predicate<T> i stedet for vår egen Betingelse.
 */
public class BetingetUtskrift {
	
    private static void betingetUtskrift(String s, Predicate<String> bet) {
		if (bet.test(s)) {
            System.out.println(s);
        }
    }
	
	public static void main(String[] args) {

        //Skriv ut hvis starter med X
        Predicate<String> begynnerMedX = s -> s.startsWith("X");
        betingetUtskrift("Per", begynnerMedX);
        betingetUtskrift("Xander", begynnerMedX);
	}
}



