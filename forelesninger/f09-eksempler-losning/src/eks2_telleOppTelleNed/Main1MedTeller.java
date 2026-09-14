package eks2_telleOppTelleNed;

// ----------------------------------------------------------------------------------------

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Naiv ikke trådsikker teller. Ender vi på 0? NEI
 */
class Teller1 {
    private int verdi = 0;
    public void tellOpp() { verdi++; }
    public void tellNed() { verdi--; }
    public int getVerdi() { return verdi; }
}

/*
 * Kan det hjelpe med volatile for synlighet mellom trådene? NEI
 */
class Teller2 {
    private volatile int verdi = 0;
    public void tellOpp() { verdi++; }
    public void tellNed() { verdi--; }
    public int getVerdi() { return verdi; }
}

/*
 * Kan det hjelpe med synchronized for atomisitet i operasjonene? JA
 */
class Teller3 {
    private int verdi = 0;
    public synchronized void tellOpp() { verdi++; }
    public synchronized void tellNed() { verdi--; }
    public synchronized int getVerdi() { return verdi; }
}

/*
 * Bruke AtomicInteger i stedet for int. Virker det? JA
 */
class Teller4 {
    private AtomicInteger verdi = new AtomicInteger(0);
    public void tellOpp() { verdi.incrementAndGet(); }
    public void tellNed() { verdi.decrementAndGet(); }
    public int getVerdi() { return verdi.get(); }
}



// ----------------------------------------------------------------------------------------

public class Main1MedTeller {

    static void main() throws InterruptedException {

        int ANTALL_GANGER = 100_000;

        Teller1 teller = new Teller1();
//        Teller2 teller = new Teller2();
//        Teller3 teller = new Teller3();
//        Teller4 teller = new Teller4();

        Thread telleOppTraad = new Thread(() -> {
            for (int i = 0; i < ANTALL_GANGER; i++) {
                teller.tellOpp();
            }
        });

        Thread telleNedTraad = new Thread(() -> {
            for (int i = 0; i < ANTALL_GANGER; i++) {
                teller.tellNed();
            }
        });

        telleOppTraad.start();
        telleNedTraad.start();

        telleOppTraad.join();
        telleNedTraad.join();

        IO.println("Teller-verdi etter at begge tråder er ferdige = " + teller.getVerdi());

    }
}

// ----------------------------------------------------------------------------------------
