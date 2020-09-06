package controllers;

import javax.persistence.*;

public class EntityManagerProvider {
    private static final String PU = "libraryPersistenceUnit";
    private EntityManager entityManager;

    public EntityManager getInstance(){
        if (entityManager==null){
            entityManager = Persistence.createEntityManagerFactory (PU).createEntityManager ();
        }
        return entityManager;
    }
}
