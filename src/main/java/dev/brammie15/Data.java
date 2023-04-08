package dev.brammie15;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;


public class Data {
    private SessionFactory sessionFactory;
    public Data(){
        init();
    }
    public void init() {
        Logger logger = org.slf4j.LoggerFactory.getLogger(Data.class);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            logger.error("Error creating session factory", e);
        }
    }
    public SessionFactory getConnection() {
        return sessionFactory;
    }
}
