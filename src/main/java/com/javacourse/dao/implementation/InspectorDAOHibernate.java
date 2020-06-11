package com.javacourse.dao.implementation;

import com.javacourse.dao.InspectorDAO;
import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Component
public class InspectorDAOHibernate implements InspectorDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void create(Inspector inspector) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(inspector);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Inspector> getAllInspectors() {
        try (Session session = sessionFactory.openSession()){
            return session.createCriteria(Inspector.class).list();
        }
    }

    @Override
    public Inspector getById(int id) {
        try (Session session = sessionFactory.openSession()){
            return session.get(Inspector.class, id);
        }
    }

    @Override
    public Inspector getByLogin(String login) {
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Inspector where login = :login");
            query.setParameter("login", login);
            return (Inspector) query.getSingleResult();
        }
    }

    @Override
    public void update(Inspector inspector) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(inspector);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean isExists(String login) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Inspector where login = :login");
            query.setParameter("login", login);
            Inspector inspector;
            try {
                inspector = (Inspector) query.getSingleResult();
            }catch (NoResultException e){
                return false;
            }
            return inspector != null;
        }
    }

    @Override
    public Inspector getInspectorWithLessReportsInService() {//TODO
        try (Session session = sessionFactory.openSession()) {
            Query query1 = session.createQuery("select min(I.reportsInService) from Inspector I");
            Integer reportsIS = (Integer) query1.getSingleResult();
            Query query2 = session.createQuery("from Inspector where reportsInService = :reportsInService");
            query2.setParameter("reportsInService", reportsIS);
            return (Inspector) query2.getSingleResult();
        }
    }
}
