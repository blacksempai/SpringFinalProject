package com.javacourse.dao;

import com.javacourse.model.entities.Account;

public interface AccountDAO <T extends Account> {

    void create(T account);

    void update(T account);

    T getById(int id);

    T getByLogin(String login);
}
