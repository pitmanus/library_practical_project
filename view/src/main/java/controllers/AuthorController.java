package controllers;

import javax.persistence.*;

import java.util.*;

import model.*;
import repositories.*;

import lombok.*;
import service.*;

public class AuthorController {

    private AuthorService authorService;

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

    public Author attachAuthor(Scanner scanner){
        Author author = null;
        List<Author> authorList = authorService.getAuthorRepository ().findAll ();
        authorList.forEach (a -> System.out.println (a));
        if (authorList.isEmpty ()) {
            System.out.println ("Author list is empty, add the new one");

            System.out.println ("Enter the first name of author");
            String first_name = scanner.nextLine ();
            System.out.println ("Enter the last name of author");
            String last_name = scanner.nextLine ();
            System.out.println ("Enter a place of birth of author");
            String placeOfBirth = scanner.nextLine ();
            author = new Author (first_name, last_name, placeOfBirth);
            authorService.addAuthor (author);
        } else {
            System.out.println ("Choose authors ID from the list");
            int author_id = scanner.nextInt ();
            for (Author a : authorList) {
                if (author_id == a.getAuthor_id ()) {
                    author = a;
                    System.out.println ("Author with ID " + author.getAuthor_id () + " was selected");
                }
            }
        }
        return author;
    }
}
