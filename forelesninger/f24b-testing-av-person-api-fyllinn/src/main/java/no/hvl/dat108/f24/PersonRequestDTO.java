package no.hvl.dat108.f24;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonRequestDTO(

        @NotBlank // implisitt også @NotNull
        @Size(min=3, message="Fornavn må være minst tre tegn") //Hva med whitespace før/etter?
        String fornavn,

        @NotBlank // implisitt også @NotNull
        @Size(min=3, message="Etternavn må være minst tre tegn") //Hva med whitespace før/etter?
        String etternavn) {

    public Person tilPerson() {
        return new Person(null, fornavn, etternavn);
    }
}
