package it.unibo.dungeonsql.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();

            String dbUrl = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            if (dbUrl != null && !dbUrl.isEmpty()) {
                configuration.setProperty("hibernate.connection.url", dbUrl);
            }
            if (dbUser != null && !dbUser.isEmpty()) {
                configuration.setProperty("hibernate.connection.username", dbUser);
            }
            if (dbPassword != null && !dbPassword.isEmpty()) {
                configuration.setProperty("hibernate.connection.password", dbPassword);
            }

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Inizializzazione della SessionFactory fallita: " + ex); 
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}