package com.javacourse.dao.implementation;

import com.javacourse.dao.factory.MySqlDBConnection;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.model.entities.Account;
import com.javacourse.model.entities.Inspector;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class InspectorDAOMySQL implements InspectorDAO {
    private static Logger logger = Logger.getLogger(InspectorDAOMySQL.class);

    @Override
    public void create(Inspector inspector) {
        String sql ="INSERT INTO inspector(account_id,full_name,complaint_number,reports_in_service) VALUES(?,?,?,?);";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            int id = createAccountAndGetId(connection, inspector);
            statement.setInt(1,id);
            statement.setString(2,inspector.getFullName());
            statement.setInt(3,inspector.getComplaintNumber());
            statement.setInt(4,inspector.getReportsInService());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private int createAccountAndGetId(Connection connection, Account account) throws SQLException {
        String sql ="INSERT INTO account(login,password_hash,salt,email) VALUES(?,?,?,?);";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,account.getLogin());
        statement.setString(2,account.getPasswordHash());
        statement.setString(3,account.getSalt());
        statement.setString(4,account.getEmail());
        statement.execute();
        ResultSet rs = statement.getGeneratedKeys();
        if(rs.next())
            return rs.getInt(1);
        else
            throw new SQLException("Error getting generated key");
    }

    @Override
    public List<Inspector> getAllInspectors() {
        String sql ="SELECT * FROM inspector I JOIN account A ON (I.account_id = A.account_id);";
        List<Inspector> inspectors = new ArrayList<>();
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Inspector inspector = new Inspector();
                inspector.setId(rs.getInt("account_id"));
                inspector.setLogin(rs.getString("login"));
                inspector.setPasswordHash(rs.getString("password_hash"));
                inspector.setSalt(rs.getString("salt"));
                inspector.setEmail(rs.getString("email"));
                inspector.setFullName(rs.getString("full_name"));
                inspector.setComplaintNumber(rs.getInt("complaint_number"));
                inspector.setReportsInService(rs.getInt("reports_in_service"));
                inspectors.add(inspector);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return inspectors;
    }

    @Override
    public Inspector getByLogin(String login) {
        String sql ="SELECT * FROM inspector I JOIN account A ON (I.account_id = A.account_id) WHERE login=?;";
        Inspector inspector = new Inspector();
        inspector.setLogin(login);
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                inspector.setId(rs.getInt("account_id"));
                inspector.setPasswordHash(rs.getString("password_hash"));
                inspector.setSalt(rs.getString("salt"));
                inspector.setEmail(rs.getString("email"));
                inspector.setFullName(rs.getString("full_name"));
                inspector.setComplaintNumber(rs.getInt("complaint_number"));
                inspector.setReportsInService(rs.getInt("reports_in_service"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return inspector;
    }

    @Override
    public Inspector getById(int id) {
        String sql ="SELECT * FROM inspector I JOIN account A ON (I.account_id = A.account_id) WHERE I.account_id=?;";
        Inspector inspector = new Inspector();
        inspector.setId(id);
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                inspector.setLogin(rs.getString("login"));
                inspector.setPasswordHash(rs.getString("password_hash"));
                inspector.setSalt(rs.getString("salt"));
                inspector.setEmail(rs.getString("email"));
                inspector.setFullName(rs.getString("full_name"));
                inspector.setComplaintNumber(rs.getInt("complaint_number"));
                inspector.setReportsInService(rs.getInt("reports_in_service"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return inspector;
    }

    @Override
    public void update(Inspector inspector) {
        String sql = "UPDATE inspector I JOIN account A ON (I.account_id = A.account_id)" +
                " SET password_hash=?, email=?, full_name=?, complaint_number=?, reports_in_service=?, salt=? WHERE login=?;";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, inspector.getLogin());
            statement.setString(1,inspector.getPasswordHash());
            statement.setString(2,inspector.getEmail());
            statement.setString(3,inspector.getFullName());
            statement.setInt(4,inspector.getComplaintNumber());
            statement.setInt(5,inspector.getReportsInService());
            statement.setString(6,inspector.getSalt());
            statement.setString(7,inspector.getLogin());
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExists(String login) {
        String sql ="SELECT * FROM inspector I JOIN account A ON (I.account_id = A.account_id) WHERE login=?;";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
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

    @Override
    public Inspector getInspectorWithLessReportsInService() {
        String sql ="SELECT * FROM inspector I JOIN account A ON (I.account_id = A.account_id)" +
                " WHERE reports_in_service = (SELECT MIN(reports_in_service) FROM inspector);";
        Inspector inspector = new Inspector();
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                inspector.setId(rs.getInt("account_id"));
                inspector.setLogin(rs.getString("login"));
                inspector.setPasswordHash(rs.getString("password_hash"));
                inspector.setSalt(rs.getString("salt"));
                inspector.setEmail(rs.getString("email"));
                inspector.setFullName(rs.getString("full_name"));
                inspector.setComplaintNumber(rs.getInt("complaint_number"));
                inspector.setReportsInService(rs.getInt("reports_in_service"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return inspector;
    }
}
