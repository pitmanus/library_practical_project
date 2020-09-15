package init;

import javax.persistence.*;

import java.io.*;
import java.time.*;
import java.util.*;

import controllers.*;
import export.*;
import model.*;
import repositories.*;
import service.*;

public class AppLauncher {
    public static void main (String[] args) {
        EntityManagerProvider entityManagerProvider = new EntityManagerProvider ();
        EntityManager entityManager = entityManagerProvider.getInstance ();

        Scanner scanner = new Scanner (System.in);

        BookController bookController = new BookController (entityManager);
        BookService bookService = new BookService (entityManager);
        AuthorController authorController = new AuthorController (entityManager);
        BorrowController borrowController = new BorrowController (entityManager);
        BorrowerController borrowerController = new BorrowerController (entityManager);
        ImportExportController importExportController = new ImportExportController (entityManager);
        BookExporter bookExporter = new BookExporter ();
        BookParser bookParser = new BookParser ();

        int option;
        boolean start = false;
        String startLine;

        System.out.println ("Do you want to switch on the library? Yes or No ");
        startLine = scanner.nextLine ();
        startLine = startLine.toUpperCase ();
        if ("YES".equals (startLine)) {
            start = true;
        } else {
            start = false;
        }


        while (start) {
            System.out.println ("L I B R A R Y");
            System.out.println (" Following operations are available to perform:");
            System.out.println ("1. Add book");
            System.out.println ("2. Remove");
            System.out.println ("3. Edit book");
            System.out.println ("4. Show all available books");
            System.out.println ("5. Add author");
            System.out.println ("6. Borrow book");
            System.out.println ("7. Return book");
            System.out.println ("8. Add user");
            System.out.println ("9. Export books into .xlsx file");
            System.out.println ("10. Import books from .xlsx file");
            System.out.println ("11. Exit from the library");
            option = scanner.nextInt ();
            scanner.nextLine ();

            switch (option) {
                case (1):
                    bookController.addBook (scanner);
                    break;

                case (2):
                    bookController.deleteBook (scanner);
                    break;

                case (3):
                    bookController.updateBook (scanner);

                    break;

                case (4):
                    bookController.showAllBooks ();
                    break;

                case (5):
                    authorController.addAuthor (scanner);
                    break;

                case (6):
                    borrowController.borrowBook (scanner);

                    break;

                case (7):
                    borrowController.returnBook (scanner);
                    break;

                case (8):
                    borrowerController.addUser (scanner);
                    break;

                case (9):
                    try {
                        bookExporter.export (bookController.getBooksList ());
                    } catch (XlsxParseException e) {
                        e.printStackTrace ();
                    }
                    break;

                case (10):
                    //"/Users/pitmanus/Downloads/books_sample.xlsx"
                   importExportController.parseBook (scanner, authorController);
                    break;

                case (11):
                    start = false;
                    break;

                default:
                    System.out.println ("Invalid option");
                    break;
            }
        }
        System.out.println ("Library was turned off");

    }
}
