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
    public List<Person> hentAllePersoner() {
        return personService.hentAlle();
    }

    /**
     * Svarer på GET /api/personer/{id}
     * Henter personen med id={id}.
     * Mulige resultat:
     *  - 200 OK. Aktuell person som body.
     *  - 404 Not Found, hvis person med id={id} ikke finnes.
     */
    @GetMapping("/{id}")
    public Person hentEnPerson(@PathVariable long id) {
        return personService.hentEtterId(id).orElse(new Person(-1L, "John", "Doe"));
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
    public ResponseEntity<Person> leggTilPerson(@RequestBody Person person) {
        Person lagretPerson = personService.opprett(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(lagretPerson);
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
    public Person oppdaterPerson(@PathVariable long id, @RequestBody Person person) {
        return personService.oppdater(id, person);
    }

    //DeleteMappping kan dere finne ut av selv :)
}
