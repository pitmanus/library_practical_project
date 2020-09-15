package controllers;

import javax.persistence.*;

import java.io.*;
import java.util.*;

import export.*;
import model.*;
import service.*;

public class ImportExportController {

    private BookExporter bookExporter;
    private BookParser bookParser;
    private BookService bookService;

    public ImportExportController (EntityManager entityManager) {
        this.bookExporter = new BookExporter ();
        this.bookParser = new BookParser ();
        this.bookService = new BookService (entityManager);
    }

    public void parseBook(Scanner scanner, AuthorController authorController){
        System.out.println ("Enter location of the .xlsx file for import");
        String path = scanner.nextLine ();
        List<Book> bookList = new ArrayList<> ();
        try {
            bookList = bookParser.parse (path);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        bookList.forEach (book -> System.out.println (book));

        for (Book book : bookList) {
            System.out.println ("Select author from the list for the book " + book);
            Author author = authorController.attachAuthor (scanner);
            book.setAuthor (author);
            bookService.getBookRepository ().create (book);
            System.out.println ("Success.");
        }
    }
}
