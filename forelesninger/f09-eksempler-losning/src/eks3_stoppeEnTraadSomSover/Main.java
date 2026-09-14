package eks3_stoppeEnTraadSomSover;

public class Main {

    static void main() throws InterruptedException {

        // 1. Vi oppretter og starter en tråd som skal gjøre en jobb i bakgrunnen
        Thread bakgrunnsTraad = new Thread(() -> {

            IO.println("Bakgrunnstråd: Jeg har startet.");

            // Tråden kjører i en løkke så lenge den ikke er avbrutt
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    IO.println("Bakgrunnstråd: Jeg jobber litt, og så tar jeg en lang lur...");
                    // Tråden prøver å sove i 10 sekunder
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                    // Hvis tråden blir avbrutt mens den sover, havner vi her med en gang
                    IO.println("Bakgrunnstråd: Oi, noen vekket meg med et avbrudd!");

                    // God praksis: Gjenopprett avbrutt-statusen
                    Thread.currentThread().interrupt();

                    // Kan evt. gjort break eller return her for å slippe å sjekke på nytt ...
                    break;
                }
            }
            IO.println("Bakgrunnstråd: Nå avslutter jeg pent.");
        });
        bakgrunnsTraad.start();

        // 3. main-tråden venter i 3 sekunder før den avbryter bakgrunnstråden
        Thread.sleep(3000);
        System.out.println("main-tråd: Nå har det gått 3 sekunder. Jeg ber bakgrunnstråden om å stoppe!");

        // Sender avbruddssignal til bakgrunnstråden
        bakgrunnsTraad.interrupt();

        // Venter til bakgrunnstråden er ferdig
        bakgrunnsTraad.join();
        System.out.println("main-tråd: Bakgrunnstråden er nå ferdig! Programmet avsluttes.");
    }
}
