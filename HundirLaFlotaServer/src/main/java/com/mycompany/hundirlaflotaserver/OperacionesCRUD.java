package com.mycompany.hundirlaflotaserver;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class OperacionesCRUD {
    private EntityManagerFactory emf;
    private EntityManager em;

    public OperacionesCRUD() {
        emf = Persistence.createEntityManagerFactory("HundirLaFlotaPU");
        em = emf.createEntityManager();
    }

    public void create(Object entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public <T> T read(Class<T> entityClass, Object primaryKey) {
        return em.find(entityClass, primaryKey);
    }

    public void update(Object entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void delete(Object entity) {
        em.getTransaction().begin();
        em.remove(em.merge(entity));
        em.getTransaction().commit();
    }

    public <T> List<T> readAll(Class<T> entityClass) {
        CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(entityClass);
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }

    public void close() {
        em.close();
        emf.close();
    }
}