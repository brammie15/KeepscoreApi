package dev.brammie15.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

import java.util.List;

public class ServiceUtils {
    public static <T> T getById(String id, Class<T> c, Session session) throws Exception {
        session.beginTransaction();
        T o = session.get(c, id);
        if(o == null) throw new Exception("Object not found");
        session.getTransaction().commit();
        session.close();
        return o;
    }

    public static <T> void add(Object o, Class<T> c, Session session) throws Exception {
        session.beginTransaction();
        session.persist(o);
        session.getTransaction().commit();
        session.close();
    }

    public static <T> List<T> getAll(Class<T> c, Session session) throws Exception {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(c);
        criteria.from(c);
        return session.createQuery(criteria).getResultList();
    }
}
