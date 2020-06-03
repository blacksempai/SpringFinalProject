package com.javacourse.dao.factory;

import com.javacourse.dao.AdminDAO;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.UserDAO;

public interface DAOFactory {
    UserDAO createUserDAO();
    ReportDAO createReportDAO();
    InspectorDAO createInspectorDAO();
    AdminDAO createAdminDAO();
}
