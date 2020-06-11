package com.javacourse.taxApp.dao;

import com.javacourse.taxApp.model.entities.Account;

import javax.transaction.Transactional;

@Transactional
public interface AccountDAO extends AbstractAccountDAO<Account> {
}
