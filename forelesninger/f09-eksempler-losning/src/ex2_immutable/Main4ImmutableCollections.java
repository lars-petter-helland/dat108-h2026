package ex2_immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main4ImmutableCollections {
	
	public static void main(String[] args) {
		
		//Dynamic-size. Backed by array
		List<Integer> dynamiskListe = new ArrayList<>();
		dynamiskListe.addAll(List.of(8,3,5,4,2));
		
		Collections.sort(dynamiskListe);
		dynamiskListe.add(9);
		System.out.println("dynamiskListe: " + dynamiskListe);
		
		//Fixed-size. Backed by array
		List<Integer> fixedSizeMuterbar = Arrays.asList(8,3,5,4,2);

		//OK
		Collections.sort(fixedSizeMuterbar);
		//Gir runtime java.lang.UnsupportedOperationException
		//muterbarListe.add(9);
		System.out.println("fixedSizeMuterbar: " + fixedSizeMuterbar);
		
		//Immutable
		List<Integer> ikkeMuterbarListe = List.of(8,3,5,4,2);
		
		//Gir runtime java.lang.UnsupportedOperationException
		//Collections.sort(ikkeMuterbarListe);
		
		//Må istedet gjøre slik hvis man ønsker å sortere !
		List<Integer> sortertListe = ikkeMuterbarListe.stream().sorted().toList();
		System.out.println("sortertListe: " + sortertListe);
		
		//Gir runtime java.lang.UnsupportedOperationException
		//sortertListe.add(9);
		
		//Må istedet gjøre slik hvis man ønsker å legge til 9 på slutten !
		List<Integer> tempListe = new ArrayList<>(sortertListe);
		tempListe.add(9);
		List<Integer> resultatListe = List.copyOf(tempListe);
		System.out.println("resultatListe: " + resultatListe);
	}
}
