package edu.itplus.bibliospring.backend.repository.jpa;

import edu.itplus.bibliospring.backend.model.AbstractModel;
import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.BaseDAO;
import edu.itplus.bibliospring.backend.repository.RepositoryException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BaseDAOBean<T extends AbstractModel,I> implements BaseDAO<T,I> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> entityType;

    @Autowired
    private Logger LOG;

    public BaseDAOBean(Class<T> entityType){
        this.entityType = entityType;
    }

    @Override
    public T findByID(I id) {
        try {
            return entityManager.find(entityType, id);
        }catch (PersistenceException e){
            LOG.error("FindByID failed",e);
            throw new RepositoryException("FindByID failed",e);
        }
    }

    @Override
    public T create(T entity) {
        try {
            entityManager.persist(entity); // entity peldany be injektalasa
            entityManager.flush();
            return entity;
        }catch (PersistenceException e){
            LOG.error("Create entity error",e);
            throw new RepositoryException("Create entity error",e);
        }
    }

    @Override
    public void update(T entity) {
        try{
            entityManager.merge(entity);
        }catch (PersistenceException e){
            LOG.error("Update entity error",e);
            throw new RepositoryException("Update entity error",e);
        }
    }

    @Override
    public void delete(T entity) {
        try{
            entityManager.remove(entity);
        }catch (PersistenceException e){
            LOG.error("Delete entity error",e);
            throw new RepositoryException("Delete entity error",e);
        }
    }

    @Override
    public List<T> findAll() {
        try{

            // ez fugg a schema nevtol
            // TypedQuery<T> query = entityManager.createQuery("FROM " + entityType.getName(),entityType);

            CriteriaBuilder cb = entityManager.getCriteriaBuilder ();
            CriteriaQuery<T> criteriaQuery = cb.createQuery (entityType);
            Root<T> rootEntry = criteriaQuery.from (entityType);
            CriteriaQuery<T> all = criteriaQuery.select (rootEntry);
            TypedQuery<T> allQuery = entityManager.createQuery (all);

            return allQuery.getResultList ();

        }catch (PersistenceException e){
            LOG.error("FindAll error",e);
            throw new RepositoryException("FindAll error",e);
        }
    }
}
