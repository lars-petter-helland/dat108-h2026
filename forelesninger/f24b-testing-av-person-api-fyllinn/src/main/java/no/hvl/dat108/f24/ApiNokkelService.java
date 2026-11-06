package no.hvl.dat108.f24;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@Service
class ApiNokkelService {

    // Den korrekte API-nøkkelen er "gandalf" - Merk at denne ikke er lagret noe sted.

    private final String KORREKT_NOKKEL_HASH
            = "739E72F2DB16302B3D58893083364D942BD22727938B316BE2AE6C5A12B342FC";

    public boolean erGyldig(String apinokkel) {
        if (apinokkel == null) {
            return false;
        }
        return sha256(apinokkel).equals(KORREKT_NOKKEL_HASH);
    }

    private String sha256(String nokkelSomKlartekst) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] nokkelSomBytes = nokkelSomKlartekst.getBytes(StandardCharsets.UTF_8);
            byte[] hashSomBytes = messageDigest.digest(nokkelSomBytes);
            return HexFormat.of().withUpperCase().formatHex(hashSomBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algoritmen ble ikke funnet", e);
        }
    }
}
