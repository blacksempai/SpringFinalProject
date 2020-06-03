package com.javacourse.dao.factory;


import com.javacourse.dao.AdminDAO;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.UserDAO;
import com.javacourse.dao.implementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateDAOFactory implements DAOFactory {
    @Autowired
    private UserDAOHibernate userDAO;
    @Autowired
    private AdminDAOHibernate adminDAO;
    @Autowired
    private ReportDAOHibernate reportDAO;
    @Autowired
    private InspectorDAOHibernate inspectorDAO;

    @Override
    public UserDAO createUserDAO() {
        return userDAO;
    }

    @Override
    public ReportDAO createReportDAO() {
        return reportDAO;
    }

    @Override
    public InspectorDAO createInspectorDAO() {
        return inspectorDAO;
    }

    @Override
    public AdminDAO createAdminDAO() {
        return adminDAO;
    }
}
