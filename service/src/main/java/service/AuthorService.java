package service;

import javax.persistence.*;

import model.*;
import repositories.*;

public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService (EntityManager entityManager) {
        this.authorRepository = new AuthorRepository (entityManager);
    }

    public AuthorRepository getAuthorRepository () {
        return authorRepository;
    }

    public void addAuthor(Author author){
        authorRepository.create (author);
        System.out.println ("Author " + author.getFirstName () + " " + author.getLastName () + " is added to the list");
    }
}
