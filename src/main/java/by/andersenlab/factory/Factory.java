package by.andersenlab.factory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Factory {

    private static volatile Factory factory;
    private SessionFactory sessionFactory;

    private Factory() {
        sessionFactory =
                new MetadataSources(
                        new StandardServiceRegistryBuilder()
                                .configure()
                                .build()
                ).buildMetadata().buildSessionFactory();
    }

    public static synchronized Factory getInstance() {
        if (factory == null) {
            factory = new Factory();
        }
        return factory;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
