package no.hvl.dat108.f25e;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

record Bruker(String navn, String lagretNokkelHash) {}

public class ApiNokkelMain {

    private static final String nokkelPrefiks = "minapp_";

    private static final SecureRandom secureRandom = new SecureRandom();

    static void main() {

        // 1. Det første vi trenger er en måte å generere tilfeldige nøkler av en viss lengde.
        //    Hvis vi velger 24 byte, tilsvarer det 32 tegn i base64.
        //    Det er også vanlig å kombinere dette med et prefiks som gir brukeren en viss
        //    idé om hvor/hva nøkkelen brukes til.
        String tilfeldigNokkel = genererTilfeldigApiNokkel();
        IO.println("Tilfeldig generert nøkkel: " + tilfeldigNokkel);

        // 2. Nøkkelen bør ikke lagres som den er, siden dette muliggjør uautorisert bruk av
        //    API-et hvis man får tak i lagret nøkkel. Med samme argumentasjon som for
        //    passord bør nøkkelen lagres som en hash. Merk: Det er ikke nødvendig med salt,
        //    da nøkkelen er lang nok (har god nok entropi) i utgangspunktet.
        //    En SHA-256 hash er 32 byte, tilsv 44 tegn i base64.
        //    Denne lagres sammen med andre brukerdata for aktuell bruker.
        String nokkelHash = hashApiNokkel(tilfeldigNokkel);
        Bruker per = new Bruker("Per", nokkelHash);
        IO.println("Hash av generert nøkkel:   " + nokkelHash);

        // 3. Den siste tingen vi må ha på plass er hvordan vi sjekker om en benyttet
        //    API-nøkkel er gyldig/korrekt for aktuell bruker.

        // Denne skal være korrekt/gyldig:
        String benyttetNokkel = tilfeldigNokkel;
        boolean erNokkelGyldig = erNokkelGyldig(per, benyttetNokkel);
        IO.println("Per sin brukte nøkkel:     " + benyttetNokkel + " --- er "
                + (erNokkelGyldig ? "GYLDIG!" : "IKKE gyldig!"));

        // Denne skal IKKE være korrekt/gyldig:
        benyttetNokkel = genererTilfeldigApiNokkel();
        erNokkelGyldig = erNokkelGyldig(per, benyttetNokkel);
        IO.println("Per sin brukte nøkkel:     " + benyttetNokkel + " --- er "
                + (erNokkelGyldig ? "GYLDIG!" : "IKKE gyldig!"));
    }

    /* ************************************************************************************ */

    private static boolean erNokkelGyldig(Bruker bruker, String benyttetNokkel) {
        if (bruker.lagretNokkelHash() == null) {
            return false; //Brukeren har ingen nøkkel
        }
        return hashApiNokkel(benyttetNokkel).equals(bruker.lagretNokkelHash());
    }

    private static String hashApiNokkel(String nokkel) {
        try {

            // Her er det ikke så viktig med en treg algoritme. SHA-256 er OK.
            // En nøkkel på 24 byte tar uendelig lag tid å brute-force, og ordlister
            // over "mye brukte nøkler" finnes jo ikke.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hashBytes = digest.digest(nokkel.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Kritisk feil: SHA-256 algoritme ikke funnet", e);
        }
    }

    private static String genererTilfeldigApiNokkel() {

        byte[] randomBytes = new byte[24];  // Satser på en 24 byte ~ 32 base64-tegn
        secureRandom.nextBytes(randomBytes);

        // Bruker URL-safe Base64 uten padding for å unngå tegnene '+', '/' og '=' i nøkkelen
        String randomString = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

        // Prefiks gjør det lettere for brukere å identifisere hva slags nøkkel dette er
        return nokkelPrefiks + randomString;

    }


}
