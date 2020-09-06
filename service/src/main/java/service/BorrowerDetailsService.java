package service;

import javax.persistence.*;

import repositories.*;

public class BorrowerDetailsService {
    BorrowerDetailsRepository borrowerDetailsRepository;

    public BorrowerDetailsService (EntityManager entityManager) {
        this.borrowerDetailsRepository = new BorrowerDetailsRepository (entityManager);
    }

    public BorrowerDetailsRepository getBorrowerDetailsRepository () {
        return borrowerDetailsRepository;
    }
}
