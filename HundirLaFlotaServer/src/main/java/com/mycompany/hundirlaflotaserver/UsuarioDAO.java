package com.mycompany.hundirlaflotaserver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

public class UsuarioDAO {
    private EntityManagerFactory emf;

    public UsuarioDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public UsuarioEntity findUsuarioByUsernameAndPassword(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM UsuarioEntity u WHERE u.username = :username AND u.password = :password", UsuarioEntity.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
}