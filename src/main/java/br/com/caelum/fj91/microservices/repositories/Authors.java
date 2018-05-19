package br.com.caelum.fj91.microservices.repositories;

import br.com.caelum.fj91.microservices.models.Author;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface Authors extends Repository<Author, Long> {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    void save(Author author);
}
