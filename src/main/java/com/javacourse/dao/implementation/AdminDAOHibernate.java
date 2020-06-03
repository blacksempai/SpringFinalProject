package com.javacourse.dao.implementation;

import com.javacourse.dao.AdminDAO;
import com.javacourse.model.entities.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Component
public class AdminDAOHibernate implements AdminDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Admin getByLogin(String login) {
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Admin where login = :login");
            query.setParameter("login", login);
            return (Admin) query.getSingleResult();
        }
    }

    @Override
    public boolean isExists(String login) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from Admin where login = :login");
            query.setParameter("login", login);
            Admin admin = (Admin) query.getSingleResult();
            return admin != null;
        }
    }
}