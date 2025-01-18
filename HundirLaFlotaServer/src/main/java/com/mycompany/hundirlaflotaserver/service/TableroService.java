package com.mycompany.hundirlaflotaserver.service;

import com.mycompany.hundirlaflotaserver.TableroEntity;
import com.mycompany.hundirlaflotaserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TableroService {

    public void saveTablero(TableroEntity tablero) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tablero);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<TableroEntity> getAllTableros() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from TableroEntity", TableroEntity.class).list();
        }
    }

    public TableroEntity getTableroById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TableroEntity.class, id);
        }
    }

    public void updateTablero(TableroEntity tablero) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tablero);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteTablero(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            TableroEntity tablero = session.get(TableroEntity.class, id);
            if (tablero != null) {
                session.delete(tablero);
                System.out.println("Tablero is deleted");
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