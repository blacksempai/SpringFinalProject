package com.javacourse.dao.factory;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import javax.naming.Context;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDBConnection {
    private static final Logger logger = Logger.getLogger(MySqlDBConnection.class);
    private static DataSource ds;
    static {
        Context envCtx;
        try {
            envCtx = (Context) (new InitialContext().lookup("java:comp/env"));
            ds = (DataSource) envCtx.lookup("jdbc/taxes");

        } catch (NamingException e) {
            logger.error(e.getMessage());
        }

    }

    public static Connection getConnection(){
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
