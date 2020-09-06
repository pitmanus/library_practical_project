package controllers;

import javax.persistence.*;
import java.util.*;

import model.*;
import repositories.*;
import service.*;

public class BorrowerController {
    BorrowerService borrowerService;
    BorrowerDetailsService borrowerDetailsService;

    public BorrowerController (EntityManager entityManager) {
        this.borrowerService = new BorrowerService (entityManager);
        this.borrowerDetailsService = new BorrowerDetailsService (entityManager);
    }

    public void addUser(Scanner scanner){
        Borrower borrower = null;
        BorrowerDetails borrowerDetails = null;
        System.out.println ("Add user");
        System.out.println ("Enter users first name");
        String usersFirstName = scanner.nextLine ();
        System.out.println ("Enter users last name");
        String usersLastName = scanner.nextLine ();
        borrower = new Borrower (usersFirstName, usersLastName);
        System.out.println ("Add users details");
        System.out.println ("Enter users address");
        String address = scanner.nextLine ();
        System.out.println ("Enter users email");
        String email = scanner.nextLine ();
        System.out.println ("Enter users tel.");
        String tel = scanner.nextLine ();
        borrowerDetails = new BorrowerDetails (address, email, tel);
        borrowerDetailsService.getBorrowerDetailsRepository ().create (borrowerDetails);
        borrower.setBorrowerDetails (borrowerDetails);
        borrowerService.addUser (borrower);
    }
}
