package controllers;

import javax.persistence.*;
import java.time.*;
import java.util.*;

import model.*;
import repositories.*;
import service.*;

public class BookController {

    AuthorService authorService;
    BookService bookService;


    public BookController (EntityManager entityManager) {
        this.authorService = new AuthorService (entityManager);
        this.bookService = new BookService (entityManager);
    }


    public void addBook (Scanner scanner) {
        scanner = new Scanner (System.in);
        Author author = null;
        Book book = null;
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
            System.out.println ("Choose the author from the list");
            int author_id = scanner.nextInt ();
            for (Author a : authorList) {
                if (author_id == a.getAuthor_id ()) {
                    author = a;
                }
            }
            authorService.addAuthor (author);
        }

        System.out.println ("Add the book");
        System.out.println ("Enter title of the book");
        String title = scanner.nextLine ();
        System.out.println ("Enter publishing date of book (in format yyyy-mm-dd)");
        String date = scanner.nextLine ();
        System.out.println ("Enter isbn of the book");
        String isbn = scanner.nextLine ();
        System.out.println ("Enter genre of the book");
        String genresOfBooks = scanner.nextLine ();
        genresOfBooks = genresOfBooks.toUpperCase ();
        System.out.println ("Enter a number of pages");
        int numberOfPages = scanner.nextInt ();
        System.out.println ("Enter short description of the book");
        scanner.nextLine ();
        String shortDescription = scanner.nextLine ();

        book = new Book (title, LocalDate.parse (date), isbn, GenresOfBooks.valueOf (genresOfBooks), numberOfPages, shortDescription, false, false);
        book.setAuthor (author);
        bookService.addBook (book);
    }

    public void deleteBook (Scanner scanner) {
        System.out.println ("In order to delete a book enter ID of the book");
        long id = scanner.nextLong ();
        bookService.removeBook (id);
        System.out.println ("Book with id " + id + " is successfully deleted");

    }

    public void updateBook (Scanner scanner) {
        System.out.println ("List of all available books: ");
        List<Book> bookList = bookService.getBookRepository ().findAll ();
        bookList.forEach (book2 -> System.out.println (book2));
        System.out.println ("In order to edit a book enter ID of the book from the list:");
        long bookId1 = scanner.nextLong ();
        System.out.println ("Editing of the book:");
        System.out.println ("Enter title of the book");
        String title1 = scanner.nextLine ();
        System.out.println ("Enter publishing date of book (in format yyyy-mm-dd)");
        String date1 = scanner.nextLine ();
        System.out.println ("Enter isbn of the book");
        String isbn1 = scanner.nextLine ();
        System.out.println ("Enter genre of the book");
        String genresOfBooks1 = scanner.nextLine ();
        genresOfBooks1 = genresOfBooks1.toUpperCase ();
        System.out.println ("Enter a number of pages");
        int numberOfPages1 = scanner.nextInt ();
        System.out.println ("Enter short description of the book");
        String shortDescription1 = scanner.nextLine ();

        Book editedBook = new Book (bookId1, title1, LocalDate.parse (date1), isbn1, GenresOfBooks.valueOf (genresOfBooks1), numberOfPages1, shortDescription1, false, false);

        bookService.getBookRepository ().update (editedBook);

        System.out.println ("Book with ID " + bookId1 + " was successfully edited");
    }

    public void showAllBooks () {
        System.out.println ("List of all available books: ");
        bookService.getBookRepository ().findAll ().forEach (book3 -> System.out.println (book3));
    }


}
