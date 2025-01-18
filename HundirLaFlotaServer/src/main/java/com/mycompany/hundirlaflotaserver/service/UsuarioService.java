package com.mycompany.hundirlaflotaserver.service;

import com.mycompany.hundirlaflotaserver.UsuarioEntity;
import com.mycompany.hundirlaflotaserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import java.util.List;

public class UsuarioService {

    public void saveUsuario(UsuarioEntity usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<UsuarioEntity> getAllUsuarios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from UsuarioEntity", UsuarioEntity.class).list();
        } catch (HibernateException e) {
            System.err.println("Error getting all users: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UsuarioEntity getUsuarioById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(UsuarioEntity.class, id);
        } catch (HibernateException e) {
            System.err.println("Error getting user by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public UsuarioEntity getUsuarioByUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from UsuarioEntity where username = :username", UsuarioEntity.class)
                    .setParameter("username", username)
                    .uniqueResult();
        } catch (HibernateException e) {
            System.err.println("Error getting user by username: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void updateUsuario(UsuarioEntity usuario) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error updating user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteUsuario(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            UsuarioEntity usuario = session.get(UsuarioEntity.class, id);
            if (usuario != null) {
                session.delete(usuario);
                System.out.println("Usuario is deleted");
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
    }
}