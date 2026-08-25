package no.hvl.dat108.f02.utplukk;

import java.util.List;

/*
 * Det vi gjorde i f03 var å fullføre dette eksempelet.
 * - Laget en metode storreEnn() som returnerer en funksjon (av typen Betingelse) !
 *
 * Vi har da sett at vi både kan bruke funksjoner som parametre, og som returverdier.
 * Akkurat som andre typer data !
 */

public class Main {

	static Betingelse storreEnn(int grense) {
		return tall -> tall > grense;
	}

	public static void main(String[] args) {
		
		List<Integer> listen = List.of(-4, 9, 2, 7, 6, 0);
		List<Integer> resultat = null;;

		System.out.println("Listen:      " + listen);

		//1. Plukk ut og returner alle partall
		resultat = Tallplukker.plukkUt(listen, x -> x % 2 == 0);
		System.out.println("Partallene:  " + resultat);
		
		//2. Plukk ut og returner alle tall større enn 3
		resultat = Tallplukker.plukkUt(listen, storreEnn(3));
		System.out.println("Tallene > 3: " + resultat);

		//3. Plukk ut og returner alle tall større enn 0
        resultat = Tallplukker.plukkUt(listen, storreEnn(0));
        System.out.println("Tallene > 0: " + resultat);

		//4. Plukk ut og returner alle tall større enn 6
		//???

	}

}

