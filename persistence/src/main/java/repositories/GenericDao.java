package repositories;

import javax.persistence.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class GenericDao<T, K> {

    protected final EntityManager entityManager;
    protected final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    protected GenericDao (EntityManager entityManager) {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        this.entityManager = entityManager;
    }

    public boolean create(T entity) {
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction ();
            transaction.begin ();
            entityManager.persist (entity);
            transaction.commit ();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback ();
                return false;
            }
        }
        return true;
    }

    public T save(T entity) {
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction ();
            transaction.begin ();
            entityManager.persist (entity);
            entityManager.flush ();
            transaction.commit ();
            return entity;
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback ();
                return null;
            }
        }
        return null;
    }

    public T read(K id) {
        return entityManager.find (entityClass, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery ("Select t from " + entityClass.getSimpleName () + " t").getResultList ();
    }

    public T update(T entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction ();
            transaction.begin ();
            entityManager.merge (entity);
            transaction.commit ();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback ();
            }
        }
        return entity;
    }

    public void delete(K id) {
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction ();
            transaction.begin ();
            entityManager.remove (this.read (id));
            transaction.commit ();
        }catch (Exception e){
            if (transaction!=null){
                transaction.rollback ();
            }
        }
    }
}
