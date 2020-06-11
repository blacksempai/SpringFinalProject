package com.javacourse.dao;


import com.javacourse.model.entities.User;

public interface UserDAO {

    void create(User user);

    User getByLogin(String login);

    User getById(int id);

    void update(User user);

    boolean isExists(String login);

}
