package service;

import javax.persistence.*;
import java.time.*;
import java.util.*;

import model.*;
import repositories.*;

public class BookService {


    private BookRepository bookRepository;

    public BookService (EntityManager entityManager) {
        bookRepository = new BookRepository (entityManager);
    }


    public BookRepository getBookRepository () {
        return bookRepository;
    }

    public void addBook(Book book){
        bookRepository.create (book);
        System.out.println ("Book " + book.getTitle () + " added to the library");
    }


    public boolean removeBook(long id){

            Book book = bookRepository.read (id);
            if (book==null){
                System.out.println ("Book is null for " + id);
                return false;
            }
            if (book.isBorrowed ()){
                System.out.println ("Book is borrowed for " + id);
                return false;
            }
            book.setRemoved (true);
            bookRepository.delete (id);
        return true;
    }


}
