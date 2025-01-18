package com.mycompany.hundirlaflotaserver.service;

import com.mycompany.hundirlaflotaserver.PartidaEntity;
import com.mycompany.hundirlaflotaserver.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PartidaService {

    public void savePartida(PartidaEntity partida) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(partida);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void getPartidasSinTerminar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<PartidaEntity> partidas = session.createQuery("from PartidaEntity where ganador is null", PartidaEntity.class).list();
            for (PartidaEntity partida : partidas) {
                System.out.println(partida);
            }
        }
    }

    public List<PartidaEntity> getAllPartidas() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from PartidaEntity", PartidaEntity.class).list();
        }
    }

    public PartidaEntity getPartidaById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(PartidaEntity.class, id);
        }
    }

    public void updatePartida(PartidaEntity partida) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(partida);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deletePartida(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            PartidaEntity partida = session.get(PartidaEntity.class, id);
            if (partida != null) {
                session.delete(partida);
                System.out.println("Partida is deleted");
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