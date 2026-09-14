package eks1_volatileOgSynlighet;

public class Main {

    // Volatile sørger for at endringer i flagget blir synlige for alle tråder umiddelbart
    private static volatile boolean fortsette = true;

    static void main() throws InterruptedException {

        // Oppretter og starter en evig tråd
        Thread minTraad = new Thread(() -> {

            IO.println("Arbeidstråd startet...");

            long teller = 0;
            while (fortsette) {
                teller++; // Gjør noe arbeid i den evige løkken
            }

            IO.println("Arbeidstråd stoppet kontrollert. Talte til: " + teller);
        });
        minTraad.start();

        // La main-tråden vente i 1 sekund før den stopper arbeidstråden
        Thread.sleep(1000);

        IO.println("main-tråden ber arbeidstråden om å stoppe...");
        fortsette = false; // Endrer flagget

        // Venter på at arbeidstråden faktisk har avsluttet helt
        minTraad.join();
        System.out.println("main() avsluttet.");
    }
}
