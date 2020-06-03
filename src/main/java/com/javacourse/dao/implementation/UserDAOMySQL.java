package com.javacourse.dao.implementation;

import com.javacourse.dao.factory.MySqlDBConnection;
import com.javacourse.dao.UserDAO;
import com.javacourse.model.TaxGroup;
import com.javacourse.model.entities.Account;
import com.javacourse.model.entities.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDAOMySQL implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOMySQL.class);

    @Override
    public void create(User user) {
        String sql ="INSERT INTO user(account_id,full_name,company_name,passport,address,tax_group) VALUES(?,?,?,?,?,?);";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            int id = createAccountAndGetId(connection, user);
            statement.setInt(1, id);
            statement.setString(2,user.getFullName());
            statement.setString(3,user.getCompany());
            statement.setString(4,user.getPassport());
            statement.setString(5,user.getAddress());
            statement.setString(6,user.getGroup().name());
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
    public User getById(int id) {
        String sql ="SELECT * FROM user U JOIN account A ON (U.account_id = A.account_id) WHERE U.account_id=?;";
        User user = new User();
        user.setId(id);
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                user.setLogin(rs.getString("login"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setSalt(rs.getString("salt"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));
                user.setCompany(rs.getString("company_name"));
                user.setPassport(rs.getString("passport"));
                user.setAddress(rs.getString("address"));
                user.setGroup(TaxGroup.valueOf(rs.getString("tax_group")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByLogin(String login) {
        String sql ="SELECT * FROM user U JOIN account A ON (U.account_id = A.account_id) WHERE A.login=?;";
        User user = new User();
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("account_id"));
                user.setLogin(rs.getString("login"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setSalt(rs.getString("salt"));
                user.setEmail(rs.getString("email"));
                user.setFullName(rs.getString("full_name"));
                user.setCompany(rs.getString("company_name"));
                user.setPassport(rs.getString("passport"));
                user.setAddress(rs.getString("address"));
                user.setGroup(TaxGroup.valueOf(rs.getString("tax_group")));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE user U JOIN account A ON (U.account_id = A.account_id) SET " +
                "full_name=?, company_name=?, passport=?, address=?, tax_group=? WHERE login=?;";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,user.getFullName());
            statement.setString(2,user.getCompany());
            statement.setString(3,user.getPassport());
            statement.setString(4,user.getAddress());
            statement.setString(5,user.getGroup().name());
            statement.setString(6,user.getLogin());
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExists(String login) {
        String sql ="SELECT * FROM user U JOIN account Ac ON (U.account_id = Ac.account_id) WHERE login=?;";
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
}
