package no.hvl.dat108.f05;

import java.util.List;
import java.util.Optional;

/*
 * Hvis vi har Optional-verdier i en Stream vil operasjoner pakke ut,
 * gjennomføres og pakke inn igjen.
 *
 * flatMap() på Optional vil utføre en map() på verdien hvis den finnes,
 * men gjøre ingenting (altså returnere en Optional.empty) om den ikke finnes.
 *
 * flatMap på en Optional kan da brukes til å slå sammen to operasjoner som begge
 * returnerer en Optional, slik at du unngår nøstede Optional<Optional<T>>-verdier.
 *
 * Dette eksempelet demonstrerer dette.
 */
public class Eks5_Optional_flatMap {

    /*
     * En metode som prøver å parse en string, altså trekke ut tallverdien.
     * Hva gjør vi hvis stringen s ikke er et tall, altså hvis parsingen
     * kaster et NumberFormatException?
     *
     * Alternativer:
     * - 1) Kaste unntaket - I så fall må vi ha try-catch på et kall til denne.
     * - 2) Returnere null - I så fall må vi sjekke om null etter et kall til denne.
     * - 3) Returnere en Optional(Integer) - Valgt løsning.
     */
    static Optional<Integer> parseTall(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    static void main() {

        List<String> strenger = List.of("x", "abc", "2fa", "0123", "007");

        // Vi antar at alle strenger som begynner med 0 er gyldige heltall
        // Vi ønsker å finne den lengste strengen som begynner med "0", og
        // hente ut tallet fra denne, altså at det endelige svaret blir 123.

        // Vi begynner med å finne den aktuelle strengen, altså den lengste
        // strengen som begynner med "0". Siden vi kan risikere å ikke finne
        // noen som matcher vårt kriterium, returneres en Optional<String>.
        Optional<String> lengsteSomBegynnerPaa0 = strenger.stream()
                .filter(s -> s.startsWith("0"))
                .max((a,b) -> a.length() - b.length());

        //Kan da ikke kalle parseTall(..) direkte siden den forventer en String.
        //Optional<Integer> svaret = parseTall(lengsteSomBegynnerPaa0);

        //Hvis vi bruker ..map(s -> parseTall(s)) vil den riktignok pakke ut
        //lengsteSomBegynnerPaa0 og parse denne, men den pakker den inn igjen
        //før svaret returnenes, slik at det blir en Optional<Optional<Integer>>.
        Optional<Optional<Integer>> x = lengsteSomBegynnerPaa0.map(s -> parseTall(s));
        //Svar: Optional[Optional[123]]

        //For å ikke måtte styre med manglende data allerede nå kan vi gjøre
        //flatmap() samtidig med kallet på parseTall(), slik at begge "feil"
        //(manglende data eller ugyldige tallverdi) kan behandles under ett.
        //Velger å si at svaret er -1 om noen er feil.
        int svaret = lengsteSomBegynnerPaa0
                .flatMap(s -> parseTall(s))
                .orElse(-1);

        IO.println(svaret);

        //Evt. alt i ett:
        strenger.stream()
                .filter(s -> s.startsWith("0"))
                .max((a,b) -> a.length() - b.length())
                .flatMap(s -> parseTall(s))
                .ifPresentOrElse(IO::println, () -> IO.println("Intet svar"));
   }
}
