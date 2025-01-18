package com.mycompany.hundirlaflotaserver.service;

import com.mycompany.hundirlaflotaserver.MovimientoEntity;
import com.mycompany.hundirlaflotaserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MovimientoService {

    public void saveMovimiento(MovimientoEntity movimiento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(movimiento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<MovimientoEntity> getAllMovimientos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from MovimientoEntity", MovimientoEntity.class).list();
        }
    }

    public MovimientoEntity getMovimientoById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(MovimientoEntity.class, id);
        }
    }

    public void updateMovimiento(MovimientoEntity movimiento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(movimiento);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteMovimiento(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            MovimientoEntity movimiento = session.get(MovimientoEntity.class, id);
            if (movimiento != null) {
                session.delete(movimiento);
                System.out.println("Movimiento is deleted");
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