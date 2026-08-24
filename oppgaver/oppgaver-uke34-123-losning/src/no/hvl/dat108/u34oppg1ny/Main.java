package no.hvl.dat108.u34oppg1ny;

import java.util.StringJoiner;

public class Main {
	
	public static void main(String[] args) {
		
		int[] tabell = {2,4,1};

        IO.println(new Stjerneformat().somStreng(3));
        IO.println(new Romertallformat().somStreng(3));

		skrivUtTallene(tabell, new Stjerneformat());
		skrivUtTallene(tabell, new Romertallformat());
	}
	
//	private static void skrivUtTallene(int[] tabell, Tallformat format) {
//        System.out.print("[");
//        for (int i=0; i<tabell.length; i++) {
//			System.out.print(format.somStreng(tabell[i]));
//            if (i < tabell.length - 1) {
//                System.out.print(", ");
//            }
//		  }
//        System.out.println("]");
//	}
    private static void skrivUtTallene(int[] tabell, Tallformat format) {
        StringJoiner joiner = new StringJoiner(", ", "[", "]"); //delimiter, prefix, suffix
        for (int tall : tabell) {
            joiner.add(format.somStreng(tall));
        }
        System.out.println(joiner.toString());
}


}
