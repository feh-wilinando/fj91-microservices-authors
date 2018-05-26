package br.com.caelum.fj91.microservices.controllers;

import br.com.caelum.fj91.microservices.exceptions.NotFoundException;
import br.com.caelum.fj91.microservices.forms.AuthorForm;
import br.com.caelum.fj91.microservices.models.Author;
import br.com.caelum.fj91.microservices.repositories.Authors;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("authors")
public class AuthorsController {


    private final Authors authors;

    public AuthorsController(Authors authors) {
        this.authors = authors;
    }

    @GetMapping
    @Cacheable("authors")
    public List<Author> list(){
        return authors.findAll();
    }

    @GetMapping("{id}")
    @Cacheable("author")
    public Author show(@PathVariable Long id){
        return authors.findById(id).orElseThrow(NotFoundException::new);
    }

    @CacheEvict(value = {"authors","author"},allEntries = true)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Author> save(@RequestBody @Valid AuthorForm form){
        Author author = form.toEntity();

        authors.save(author);

        return ResponseEntity
                .created(URI.create("/authors/" + author.getId()))
                    .body(author);
    }
}
