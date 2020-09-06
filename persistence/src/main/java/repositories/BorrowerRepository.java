package repositories;

import javax.persistence.*;

import model.*;

public class BorrowerRepository extends GenericDao<Borrower, Long>  {
    public BorrowerRepository (EntityManager entityManager) {
        super (entityManager);
    }
}
