package com.javacourse.dao.implementation;

import com.javacourse.dao.UserDAO;
import com.javacourse.model.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
@Component
public class UserDAOHibernate implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public User getByLogin(String login) {
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from User where login = :login");
            query.setParameter("login", login);
            return (User) query.getSingleResult();
        }
    }

    @Override
    public User getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public void update(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean isExists(String login) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from User where login = :login");
            query.setParameter("login", login);
            User user;
            try {
                 user = (User) query.getSingleResult();
            }catch (NoResultException e){
                return false;
            }
            return user != null;
        }
    }

}
