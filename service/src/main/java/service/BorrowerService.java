package service;

import javax.persistence.*;

import model.*;
import repositories.*;

public class BorrowerService {
    BorrowerRepository borrowerRepository;

    public BorrowerService (EntityManager entityManager) {
        borrowerRepository = new BorrowerRepository (entityManager);
    }

    public void addUser(Borrower borrower){
        borrowerRepository.create (borrower);
        System.out.println ("New user " + borrower.getFirstName () + " " + borrower.getLastName () + " is added");
    }

    public BorrowerRepository getBorrowerRepository () {
        return borrowerRepository;
    }
}
