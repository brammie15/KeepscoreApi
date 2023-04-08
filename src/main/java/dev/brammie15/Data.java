package dev.brammie15;

import dev.brammie15.objects.Class;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;

import java.sql.SQLException;

public class Data {
    String databaseURL = "jdbc:mysql://localhost:3306/keepscore";

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
    public void test(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Class c = new Class();
        c.setName("test");
        session.persist(c);
        session.getTransaction().commit();

        Class newclass = session.get(Class.class, 1);
        System.out.println(Constants.GSON.toJsonTree(newclass).toString());
        session.close();
    }
    public SessionFactory getConnection() {
        return sessionFactory;
    }
}
