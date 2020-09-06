package repositories;

import javax.persistence.*;

import model.*;

public class BorrowerDetailsRepository extends GenericDao<BorrowerDetails, Long>  {
    public BorrowerDetailsRepository (EntityManager entityManager) {
        super (entityManager);
    }
}
