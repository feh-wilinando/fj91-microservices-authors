package br.com.caelum.fj91.microservices.forms;

import br.com.caelum.fj91.microservices.models.Author;
import org.hibernate.validator.constraints.NotEmpty;

public class AuthorForm {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author toEntity(){
        return new Author(name);
    }
}
