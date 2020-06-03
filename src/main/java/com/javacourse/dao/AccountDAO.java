package com.javacourse.dao;

import com.javacourse.model.entities.Account;

public interface AccountDAO {

    void create(Account account);

    void update(Account account);

    Account getById(int id);

    Account getByLogin(String login);
}
