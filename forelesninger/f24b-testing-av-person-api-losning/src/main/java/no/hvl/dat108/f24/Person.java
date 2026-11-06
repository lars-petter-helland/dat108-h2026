package no.hvl.dat108.f24;

import jakarta.persistence.*;

@Entity
@Table(schema = "f22_personer")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fornavn;
    private String etternavn;

    public Person() {}

    public Person(Long id, String fornavn, String etternavn) {
        this.id=id;
        this.fornavn=fornavn;
        this.etternavn=etternavn;
    }

    public Long getId() {
        return id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }
}
