package com.javacourse.dao.implementation;

import com.javacourse.dao.AccountDAO;
import com.javacourse.dao.factory.MySqlDBConnection;
import com.javacourse.model.entities.Account;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AccountDAOMySQL implements AccountDAO {
    private static Logger logger = Logger.getLogger(AccountDAOMySQL.class);

    @Override
    public void create(Account account) {
        String sql ="INSERT INTO account(login,password_hash,salt,email) VALUE(NULL,?,?,?,?);";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,account.getLogin());
            statement.setString(2,account.getPasswordHash());
            statement.setString(3,account.getSalt());
            statement.setString(4,account.getEmail());
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Account account) {
        String sql = "UPDATE account SET password_hash=?, email=?, salt=?" +
                " WHERE login='"+account.getLogin()+"';";
        try (Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,account.getPasswordHash());
            statement.setString(2,account.getEmail());
            statement.setString(3,account.getSalt());
            statement.execute();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Account getById(int id) {
        String sql ="SELECT * FROM account WHERE account_id='"+id+"';";
        Account account = new Account();
        try(Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                account.setId(rs.getInt("account_id"));
                account.setLogin(rs.getString("login"));
                account.setPasswordHash(rs.getString("password_hash"));
                account.setSalt(rs.getString("salt"));
                account.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Account getByLogin(String login) {
        String sql ="SELECT * FROM account WHERE login='"+login+"';";
        Account account = new Account();
        account.setLogin(login);
        try(Connection connection = MySqlDBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                account.setId(rs.getInt("account_id"));
                account.setPasswordHash(rs.getString("password_hash"));
                account.setSalt(rs.getString("salt"));
                account.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return account;
    }
}
