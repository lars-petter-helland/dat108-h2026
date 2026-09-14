package eks2_telleOppTelleNed;

// ----------------------------------------------------------------------------------------

import java.util.concurrent.atomic.AtomicInteger;

/*
 * Droppe Teller og bruke AtomicInteger direkte? JA
 */
public class Main2MedAtomicInteger {

    static void main() throws InterruptedException {

        int ANTALL_GANGER = 100_000;

        AtomicInteger teller = new AtomicInteger(0);

        Thread telleOppTraad = new Thread(() -> {
            for (int i = 0; i < ANTALL_GANGER; i++) {
                teller.incrementAndGet();
            }
        });

        Thread telleNedTraad = new Thread(() -> {
            for (int i = 0; i < ANTALL_GANGER; i++) {
                teller.decrementAndGet();
            }
        });

        telleOppTraad.start();
        telleNedTraad.start();

        telleOppTraad.join();
        telleNedTraad.join();

        IO.println("Teller-verdi etter at begge tråder er ferdige = " + teller.get());

    }
}

// ----------------------------------------------------------------------------------------
