package no.hvl.dat108.f24;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends
        JpaRepository<Person, Long> {
}
