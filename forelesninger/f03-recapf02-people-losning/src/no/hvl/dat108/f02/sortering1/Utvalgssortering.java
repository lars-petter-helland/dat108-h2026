package no.hvl.dat108.f02.sortering1;

import java.util.List;

/*
 * Det vi gjorde i f03 var å fikse litt på det vi hadde gjort tidligere.
 *
 * - Vi ønsket å fremdeles kunne kalle sorter() uten den ekstra parameteren, men ønsker ikke å ha
 *   dobbelt opp av tilnærmet samme kode. Løste dette ved å definere en Sammenligner for
 *   standard sortering, og gjorde et kall til den generelle metoden med denne sammenligneren.
 */

public class Utvalgssortering {

	/*
	 * For å fremdeles ha muligheten til å sortere uten å gi inn en ekstra
	 * paramterer, så ... ja hvordan gjør vi det ??? Jo, slik:
	 */
	public static <T extends Comparable<? super T>> void sorter(List<T> liste) {

		Sammenligner<T> standardSammenligning = (a,b) -> a.compareTo(b);
		sorter(liste, standardSammenligning);
	}

	/*
	 * Her har vi utvidet sorter med en ekstra Sammenligner-parameter som brukes
	 * i stedet for a.compareTo(b).
	 * 
	 * Kan da sortere på ulike måter bestemt av logikken i Sammenligner-objektet,
	 * og får en mye mer fleksibel og anvendelig sorteringsmetede.
	 */
	public static <T> void sorter(List<T> liste, Sammenligner<T> sammenligner) {
		
		int n = liste.size(); // antall elementer i listen
		
		for (int i = 0; i < n; i++) {
			T min = liste.get(i);
			int minIndeks = i;

			for (int j = i + 1; j < n; j++) {
				T aktuell = liste.get(j);
				
/* Før:			if (aktuell.compareTo(min) < 0) {			*/
/* Nå: */		if (sammenligner.sammenlign(aktuell, min) < 0) {
					min = aktuell;
					minIndeks = j;
				}
			}

			// swap ↄ: bytt liste[i] med min (funnet over)
			T temp = liste.get(i);
			liste.set(i, min);
			liste.set(minIndeks, temp);
		}
	}
	
}
