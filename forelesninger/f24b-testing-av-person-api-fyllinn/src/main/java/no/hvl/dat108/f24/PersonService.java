package no.hvl.dat108.f24;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /*
     * Siden de andre metodene var så¨enkle, lager vi en ny som vi ønsker å teste.
     * F.eks. en metode som henter alle med enten fornavn eller etternavn som
     * starter med en viss bokstav.
     */
    public List<Person> hentDeMedFornavnSomBegynnerMed(String c) {
        return personRepository.findAll().stream()
                .filter(p -> p.getFornavn().startsWith(c))
                .toList();
    }

    public boolean finnesPerson(Long id) {
        return personRepository.existsById(id);
    }

    public List<Person> hentAlle() {
        return personRepository.findAll();
    }

    public Optional<Person> hentEtterId(Long id) {
        return personRepository.findById(id);
    }

    public Person opprett(Person nyPerson) {
        /*
         * Vi antar her at nyPerson kommer inn med id=null.
         *
         * save() vil da opprette en ny entitet med generert id, og person med id returneres.
         */
        return personRepository.save(nyPerson);
    }

    public Person oppdater(Long id, Person personData) {
        /*
         * Vi antar her at personData kommer inn med id=null.
         * Personen som skal oppdateres er person med id gitt som paramteter.
         *
         * Siden vi ikke har noen set-metode for id, oppretter vi en ny Person i stedet.
         * save() vil oppdatere entitet med gitt id med de andre data i personData.
         * Oppdatert person returneres.
         */

        Person p = new Person(id, personData.getFornavn(), personData.getEtternavn());
        return personRepository.save(p);
    }

    public void slett(Long id) {
        personRepository.deleteById(id);
    }
}
