package com.javacourse.dao.factory;

import com.javacourse.dao.AdminDAO;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.UserDAO;
import com.javacourse.dao.implementation.AdminDAOMySQL;
import com.javacourse.dao.implementation.InspectorDAOMySQL;

import com.javacourse.dao.implementation.ReportDAOMySQL;
import com.javacourse.dao.implementation.UserDAOMySQL;
import org.springframework.stereotype.Component;

public class MySqlDAOFactory implements DAOFactory {
    @Override
    public UserDAO createUserDAO() {
        return new UserDAOMySQL();
    }

    @Override
    public ReportDAO createReportDAO() {
        return new ReportDAOMySQL();
    }

    @Override
    public InspectorDAO createInspectorDAO() {
        return new InspectorDAOMySQL();
    }

    @Override
    public AdminDAO createAdminDAO() {
        return new AdminDAOMySQL();
    }
}
