package init;

import javax.persistence.*;

import java.time.*;
import java.util.*;

import controllers.*;
import model.*;
import repositories.*;
import service.*;

public class AppLauncher {
    public static void main (String[] args) {
        EntityManagerProvider entityManagerProvider = new EntityManagerProvider ();
        EntityManager entityManager = entityManagerProvider.getInstance ();

        Scanner scanner = new Scanner (System.in);

        BookController bookController = new BookController (entityManager);
        AuthorController authorController = new AuthorController (entityManager);
        BorrowController borrowController = new BorrowController (entityManager);
        BorrowerController borrowerController = new BorrowerController (entityManager);

        int option;
        boolean start;
        System.out.println ("Do you want to switch on the library? ");
        start = scanner.nextBoolean ();
        do {
            System.out.println ("Library");
            System.out.println (" Following operations are available to perform:");
            System.out.println ("1. Add book");
            System.out.println ("2. Remove");
            System.out.println ("3. Edit book");
            System.out.println ("4. Show all available books");
            System.out.println ("5. Add author");
            System.out.println ("6. Borrow book");
            System.out.println ("7. Return book");
            System.out.println ("8. Add user");
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

                default:
                    System.out.println ("Invalid option");
                    break;
            }
        } while (start = true);











       /* EntityManager prodPersistenceUnit = Persistence.createEntityManagerFactory ("libraryPersistenceUnit").createEntityManager ();
        AuthorRepository authorRepository = new AuthorRepository (prodPersistenceUnit);
        BookRepository bookRepository = new BookRepository (prodPersistenceUnit);
        BorrowRepository borrowRepository = new BorrowRepository (prodPersistenceUnit);
        BorrowerRepository borrowerRepository = new BorrowerRepository (prodPersistenceUnit);
        BorrowerDetailsRepository borrowerDetailsRepository = new BorrowerDetailsRepository (prodPersistenceUnit);

        Author author1 = new Author ("Gregory", "Roberts", "New Zeland");

        Book book1 = new Book ("Shantaram", LocalDate.parse ("2010-01-12"), "798823765636865", GenresOfBooks.NOVEL, 566, "Short description");
        authorRepository.create (author1);
        bookRepository.create (book1);

        book1.setAuthor (author1);

        BorrowerDetails borrowerDetails1 = new BorrowerDetails ("Krakow, ul. Sienna 26/1, 31-000", "987-765-345", "bor777@gmail.com");
        borrowerDetailsRepository.create (borrowerDetails1);

        Borrower borrower1 = new Borrower ("John", "Rico");
        borrower1.setBorrowerDetails (borrowerDetails1);
        borrowerRepository.create (borrower1);

        Borrow borrow1 = new Borrow (LocalDate.parse ("2020-08-29"));
        borrow1.setBook (book1);
        borrow1.setBorrower (borrower1);
        borrowRepository.create (borrow1);*/


    }
}
