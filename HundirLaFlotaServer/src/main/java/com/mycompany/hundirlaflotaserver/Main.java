package com.mycompany.hundirlaflotaserver;

import java.io.IOException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        // Inicializar Hibernate
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Añadir clases anotadas
            configuration.addAnnotatedClass(UsuarioEntity.class);
            configuration.addAnnotatedClass(PartidaEntity.class);
            configuration.addAnnotatedClass(TableroEntity.class);
            configuration.addAnnotatedClass(BarcoEntity.class);
            configuration.addAnnotatedClass(MovimientoEntity.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Hibernate inicializado correctamente.");

            // Iniciar el servidor
            ServidorSocket servidor = new ServidorSocket(PORT);
            System.out.println("Servidor iniciado en el puerto " + PORT);
            servidor.start();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar SessionFactory al finalizar
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}