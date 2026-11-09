package no.hvl.dat108.f25c;

public class BruteForceAngrep {

	private static PassordService ps = new PassordService();

    public static void main(String[] args) {
        
        String passord = "Hei";
        
        String salt = ps.genererTilfeldigSalt();
        String hash = ps.hashMedSalt(passord, salt);
        
        long antallPassordSjekket = 0;

        long start = System.currentTimeMillis();
        
        /*
         * Sjekker alle mulige passord med 3 tegn, der tegnene
         * er i området '0'..'z' (Altså ascii 48-122)
         * Dvs. maks 73^3 = 389017 passord å sjekke.
         * 
         */
        
        boolean ferdig = false;
        System.out.println("En prikk per ca. 5000 sjekkede passord.");
        for (char c1='0'; c1<='z' && !ferdig; c1++) {
            System.out.print(".");
            for (char c2='0'; c2<='z' && !ferdig; c2++) {
                for (char c3='0'; c3<='z' && !ferdig; c3++) {
                	
                	antallPassordSjekket++;
                    String p = "" + c1 + c2 + c3;
                    
                    if (ps.validerMedSalt(p, salt, hash)) {
                        System.out.println("\nPassordet er " + p);
                        ferdig = true;
                    }
                }
            }
        }
        
        long stopp = System.currentTimeMillis();
        
        System.out.println("Antall passord sjekket: " + antallPassordSjekket);
        System.out.println("Tid i sekunder: " + (stopp-start)/1_000);
    }
}
