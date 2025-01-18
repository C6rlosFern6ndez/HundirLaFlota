package com.mycompany.hundirlaflotaserver.service;

import com.mycompany.hundirlaflotaserver.BarcoEntity;
import com.mycompany.hundirlaflotaserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.HibernateException;

import java.util.List;

public class BarcoService {

    public void saveBarco(BarcoEntity barco) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(barco);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving barco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<BarcoEntity> getAllBarcos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from BarcoEntity", BarcoEntity.class).list();
        } catch (HibernateException e) {
            System.err.println("Error getting all barcos: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public BarcoEntity getBarcoById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(BarcoEntity.class, id);
        } catch (HibernateException e) {
            System.err.println("Error getting barco by ID: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void updateBarco(BarcoEntity barco) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(barco);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error updating barco: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteBarco(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            BarcoEntity barco = session.get(BarcoEntity.class, id);
            if (barco != null) {
                session.delete(barco);
                System.out.println("Barco is deleted");
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error deleting barco: " + e.getMessage());
            e.printStackTrace();
        }
    }
}