package no.hvl.dat108.funksjoner;

import java.util.ArrayList;
import java.util.List;

interface HeltallsFunksjon {
    int av(int x);
}

class Dobbel implements HeltallsFunksjon {
    @Override
    public int av(int x) {
        return 2*x;
    }
}

class Kvadrat implements HeltallsFunksjon {
    @Override
    public int av(int x) {
        return x*x;
    }
}

public class FunksjonerMain {

    static List<Integer> omform(List<Integer> liste, HeltallsFunksjon fu) {
        List<Integer> nyListe = new ArrayList<>();
        for (int tall : liste) {
            nyListe.add(fu.av(tall));
        }
        return nyListe;
    }

    static void main() {
        List<Integer> listen = List.of(1,2,3,4);

        List<Integer> dobbelListe = omform(listen, new Dobbel());
        IO.println(dobbelListe);

        List<Integer> kvadratListe = omform(listen, new Kvadrat());
        IO.println(kvadratListe);
    }
}
