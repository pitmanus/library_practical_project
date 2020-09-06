package repositories;

import javax.persistence.*;

import model.*;

public class BookRepository extends GenericDao<Book, Long>  {
    public BookRepository (EntityManager entityManager) {
        super (entityManager);
    }
}


