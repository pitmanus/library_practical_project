package repositories;

import javax.persistence.*;

import model.*;

public class AuthorRepository extends GenericDao<Author, Long> {
    public AuthorRepository (EntityManager entityManager) {
        super (entityManager);
    }
}
