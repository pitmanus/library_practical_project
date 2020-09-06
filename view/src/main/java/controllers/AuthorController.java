package controllers;

import javax.persistence.*;

import java.util.*;

import model.*;
import repositories.*;

import lombok.*;
import service.*;

public class AuthorController {

AuthorService authorService;

    public AuthorController (EntityManager entityManager) {
        this.authorService = new AuthorService (entityManager);
    }

    public void addAuthor(Scanner scanner){
        System.out.println ("Add author");
        System.out.println ("Enter the first name of author");
        String first_name2 = scanner.nextLine ();
        System.out.println ("Enter the last name of author");
        String last_name2 = scanner.nextLine ();
        System.out.println ("Enter a place of birth of author");
        String placeOfBirth2 = scanner.nextLine ();
        Author author2 = new Author (first_name2, last_name2, placeOfBirth2);
        authorService.addAuthor (author2);
    }
}
