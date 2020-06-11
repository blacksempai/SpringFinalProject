package com.javacourse.dao.implementation;

import com.javacourse.dao.ReportDAO;
import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;
import com.javacourse.model.entities.report.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public class ReportDAOHibernate implements ReportDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(Report report) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(report);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(Report report) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.update(report);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Report report) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.delete(report);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Report> getReportsByUser(User user) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Query query = session.createQuery("from Report where user_id = :user_id");
            query.setParameter("user_id", user);
            List reports = query.getResultList();
            session.getTransaction().commit();
            return reports;
        }
    }

    @Override
    public List<Report> getReportsByInspector(Inspector inspector) {
        try (Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from Report where inspector_id = :inspector_id");
            query.setParameter("inspector_id", inspector);
            List reports = query.getResultList();
            return reports;
        }
    }
}