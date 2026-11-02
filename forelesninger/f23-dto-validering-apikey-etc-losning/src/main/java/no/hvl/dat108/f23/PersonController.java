package no.hvl.dat108.f23;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RestController
@RequestMapping("/api/personer")
class PersonController {

    private final String THE_MAGIC_API_KEY = "gandalf";
    private final String UGYLDIG_API_KEY_MELDING = "Requesten krever en gyldig API-nøkkel";

    private final PersonService personService;
    public PersonController(PersonService ps) {
        this.personService = ps;
    }

    /**
     * Svarer på GET /api/personer
     * Henter alle personer.
     * Mulige resultat:
     *  - 200 OK. Listen av personer som body.
     */
    @GetMapping
    public ResponseEntity<List<Person>> hentAllePersoner() {
        return ResponseEntity.ok(personService.hentAlle());
    }

    /**
     * Svarer på GET /api/personer/{id}
     * Henter personen med id={id}.
     * Mulige resultat:
     *  - 200 OK. Aktuell person som body.
     *  - 404 Not Found, hvis person med id={id} ikke finnes.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> hentEnPerson(@PathVariable long id) {
        return personService.hentEtterId(id)
                .map(person -> ResponseEntity.ok(person)) //=> Optional<ResponseEntity<Person>>
                .orElseGet(() -> ResponseEntity.notFound().build()); //og i tilfelle det ikke var noe.

// Alternativt hvis dette er enklere å lese:
//        Optional<Person> funnetPerson = personService.hentEtterId(id);
//        if (funnetPerson.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(funnetPerson.get());
//        }
    }

    /**
     * Svarer på POST /api/personer
     * Legger til en ny person gitt i request-body.
     * Unik id for ny person genereres automatisk.
     * Mulige resultat:
     *  - 201 Created, med personen, nå med generert id, som body.
     *  - 400 Bad Request, hvis ikke request-body er gyldig
     *  - 401 Unauthorized, hvis manglende eller ugyldig api-nøkkel
     */
    @PostMapping
    public ResponseEntity<?> leggTilPerson( // <?> Siden body kan være både Person og String
            @RequestHeader(value = "Api-Key", required = false) String apinokkel,
            @Valid @RequestBody PersonRequestDTO personDTO) {

        if (apinokkel == null || !apinokkel.equals(THE_MAGIC_API_KEY)) {
            return ResponseEntity.status(UNAUTHORIZED).body(UGYLDIG_API_KEY_MELDING);
        }

        Person lagretPerson = personService.opprett(personDTO.tilPerson());

        // return ResponseEntity.status(HttpStatus.CREATED).body(lagretPerson);

        //Hvis vi ønsker URL i Location header, må vi gjøres slik i stedet.
        //Dette er mer opp til standarden.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(lagretPerson.getId()).toUri();
        return ResponseEntity.created(location).body(lagretPerson);
    }

    /**
     * Svarer på PUT /api/personer/{id}
     * Oppdaterer personen med id={id} med data gitt i request-body.
     * Mulige resultat:
     *  - 200 OK. Oppdatert person som body.
     *  - 400 Bad Request, hvis ikke request-body er gyldig
     *  - 401 Unauthorized, hvis manglende eller ugyldig api-nøkkel
     *  - 404 Not Found, hvis person med id={id} ikke finnes.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> oppdaterPerson(
            @PathVariable long id,
            @RequestHeader(value = "Api-Key", required = false) String apinokkel,
            @Valid @RequestBody PersonRequestDTO personDTO) {

        if (apinokkel == null || !apinokkel.equals(THE_MAGIC_API_KEY)) {
            return ResponseEntity.status(UNAUTHORIZED).body(UGYLDIG_API_KEY_MELDING);
        }

        if (!personService.finnesPerson(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(personService.oppdater(id, personDTO.tilPerson()));
    }

    //DeleteMappping kan dere finne ut av selv :)
}
