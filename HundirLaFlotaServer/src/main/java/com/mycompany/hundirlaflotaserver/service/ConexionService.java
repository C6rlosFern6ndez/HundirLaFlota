package com.mycompany.hundirlaflotaserver.service;

import com.mycompany.hundirlaflotaserver.ConexionEntity;
import com.mycompany.hundirlaflotaserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ConexionService {

    public void saveConexion(ConexionEntity conexion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(conexion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<ConexionEntity> getAllConexiones() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ConexionEntity", ConexionEntity.class).list();
        }
    }

    public ConexionEntity getConexionById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ConexionEntity.class, id);
        }
    }

    public void updateConexion(ConexionEntity conexion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(conexion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteConexion(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            ConexionEntity conexion = session.get(ConexionEntity.class, id);
            if (conexion != null) {
                session.delete(conexion);
                System.out.println("Conexion is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}