package controllers;

import javax.persistence.*;
import java.time.*;
import java.util.*;

import model.*;
import repositories.*;
import service.*;

public class BorrowController {

    private AuthorService authorService;
    private BookService bookService;
    private BorrowerService borrowerService;
    private BorrowService borrowService;
    private BookController bookController;

    public BorrowController (EntityManager entityManager) {
        this.authorService = new AuthorService (entityManager);
        this.bookService = new BookService (entityManager);
        this.borrowerService = new BorrowerService (entityManager);
        this.borrowService = new BorrowService (entityManager);
        this.bookController = new BookController (entityManager);
    }

    public void borrowBook(Scanner scanner){
        Book book = null;
        Book bookWithoutId = null;
        Author author1 = null;
        Borrower borrower1 = null;
        LocalDate localDate = LocalDate.now ();
        List<Borrower> borrowers = borrowerService.getBorrowerRepository ().findAll ();
        borrowers.forEach (borrower -> System.out.println (borrower));
        System.out.println ("Enter ID of user");
        long userId = scanner.nextLong ();
        scanner.nextLine ();

        Borrow borrow = new Borrow (localDate);
        for (Borrower bor : borrowers) {
            if (userId == bor.getId_borrower ()) {
                borrower1 = bor;
            }
        }
        borrow.setBorrower (borrower1);

        List<Book> bookList2 = bookService.getBookRepository ().findAll ();
        if (bookList2.isEmpty ()) {
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
                author1 = new Author (first_name, last_name, placeOfBirth);
                authorService.addAuthor (author1);
            } else {
                System.out.println ("Choose the author from the list");
                int author_id = scanner.nextInt ();
                for (Author a : authorList) {
                    if (author_id == a.getAuthor_id ()) {
                        author1 = a;
                    }
                }
                authorService.addAuthor (author1);
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
            book.setAuthor (author1);
            book = bookService.getBookRepository ().save (book);

        } else {
            bookList2.forEach (b -> System.out.println (b));
            System.out.println ("Enter book ID");
            long bookId2 = scanner.nextLong ();
            for (Book b : bookList2) {
                if (bookId2 == b.getBook_id ()) {
                    book = b;
                }
            }
        }


        if (book.isBorrowed ()){
            System.out.println ("Impossible to borrow a book which is already borrowed");
        }else {
            borrow.setBook (book);
            borrowService.borrowBook (borrow);
            book.setBorrowed (true);
            System.out.println ("Book " + book.getTitle () + " was successfully borrowed");
        }
        bookService.getBookRepository ().update (book);
    }

    public void returnBook(Scanner scanner){
        System.out.println ("List of borrows:");
        List<Borrow> borrows = borrowService.getBorrowRepository ().findAll ();
        borrows.forEach (borrow1 -> System.out.println (borrow1.getId_borrow () + " " + borrow1.getBorrower ().getFirstName () + " " + borrow1.getBorrower ().getLastName () + " - " + borrow1.getBook ().getTitle ()));

        if (borrows.isEmpty ()) {
            System.out.println ("List of borrows is empty");
        } else {
            System.out.println ("In order to give a book back enter borrow ID ");
            long borrowId = scanner.nextLong ();
            for (Borrow borrow1 : borrows) {
                if (borrowId == borrow1.getId_borrow ()) {
                    borrow1.getBook ().setBorrowed (false);
                    bookService.getBookRepository ().update (borrow1.getBook ());
                    borrowService.getBorrowRepository ().delete (borrow1.getId_borrow ());
                }
            }
        }
    }
}
