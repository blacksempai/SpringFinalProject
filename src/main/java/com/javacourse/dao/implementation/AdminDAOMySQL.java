package com.javacourse.dao.implementation;

import com.javacourse.dao.AdminDAO;
import com.javacourse.dao.factory.MySqlDBConnection;
import com.javacourse.model.entities.Admin;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AdminDAOMySQL implements AdminDAO {
    private static Logger logger = Logger.getLogger(AdminDAOMySQL.class);

    @Override
    public Admin getByLogin(String login) {
        String sql ="SELECT * FROM admin Ad JOIN account Ac ON (Ad.account_id = Ac.account_id) WHERE login=?;";
        Admin admin = new Admin();
        admin.setLogin(login);
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                admin.setId(rs.getInt("account_id"));
                admin.setPasswordHash(rs.getString("password_hash"));
                admin.setEmail(rs.getString("email"));
                admin.setSalt(rs.getString("salt"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public boolean isExists(String login) {
        String sql ="SELECT * FROM admin Ad JOIN account Ac ON (Ad.account_id = Ac.account_id) WHERE login=?;";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                connection.close();
                return true;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


}
