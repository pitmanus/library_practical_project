package repositories;

import javax.persistence.*;

import model.*;

public class BorrowRepository extends GenericDao<Borrow, Long>  {
    public BorrowRepository (EntityManager entityManager) {
        super (entityManager);
    }


}
