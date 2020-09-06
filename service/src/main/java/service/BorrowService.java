package service;

import javax.persistence.*;

import model.*;
import repositories.*;

public class BorrowService {
    BorrowRepository borrowRepository;
    BookRepository bookRepository;
    BorrowerRepository borrowerRepository;


    public BorrowService (EntityManager entityManager) {
        this.bookRepository = new BookRepository (entityManager);
        this.borrowerRepository = new BorrowerRepository (entityManager);
        this.borrowRepository = new BorrowRepository (entityManager);
    }

    public void borrowBook(Borrow borrow){
        borrowRepository.create (borrow);
    }

    public BorrowRepository getBorrowRepository () {
        return borrowRepository;
    }
}
