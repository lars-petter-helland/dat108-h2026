package no.hvl.dat108.f23;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonRequestDTO(
        @NotBlank // implisitt også @NotNull
        String fornavn,
        @NotBlank // implisitt også @NotNull
        String etternavn) {

    public Person tilPerson() {
        return new Person(null, fornavn, etternavn);
    }
}
