package no.hvl.dat108.f02.funksjoner;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/*
 * Det vi gjorde i f03 var å fikse litt på det vi hadde gjort tidligere ut fra nye ting vi har lært.
 *
 * - I stedet for å opprette objekter med new Dobbel() og new Kvadrat() brukte vi lambda-uttrykk.
 * - Da trengte vi ikke klassene Dobbel og Kvadrat, og kunne da slette disse.
 * - Vi erstattet også interfacet Heltallsfunksjon med Function<Integer>, og kunne slette Heltallsfunksjon.
 *
 * Resultat: Mye mindre kode. Vi skal senere også se at metoden omform() kan erstattes og fjernes.
 */

public class FunksjonerMain {

    static List<Integer> omform(List<Integer> liste, Function<Integer, Integer> fu) {
        List<Integer> nyListe = new ArrayList<>();
        for (int tall : liste) {
            nyListe.add(fu.apply(tall));
        }
        return nyListe;
    }

    static void main() {

        List<Integer> listen = List.of(1,2,3,4);

        Function<Integer, Integer> d = x -> 2 * x;
        List<Integer> dobbelListe = omform(listen, d);
        IO.println(dobbelListe);

        Function<Integer, Integer> k = x -> x * x;
        List<Integer> kvadratListe = omform(listen, k);
        IO.println(kvadratListe);
    }
}
