package dat108.eksv21.oppg5;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {

		//Her opprettes og startes en tråd som går i en løkke og skriver ut
		//en melding på skjermen ca. hvert sekund helt til tråden avsluttes 
		//på kontrollert måte.

		//Her opprettes og startes en tråd som viser frem en JOptionPane meldings-
		//boks. Når brukeren trykker på OK-knappen skal utskrifts-tråden få beskjed 
		//om å avslutte, og meldingsboks-tråden vil også være ferdig.
		
		System.out.println("Tråder er startet. Venter på at de er ferdige ...");
		
		//Her ventes det på at de andre trådene er ferdige før main avsluttes.
		
		System.out.println("Begge tråder er ferdige!");	
		System.out.println("Main-tråd ferdig!");
	}
}