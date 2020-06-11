package com.javacourse.taxApp.dao;

import com.javacourse.taxApp.model.entities.User;

import javax.transaction.Transactional;

@Transactional
public interface UserDAO extends AbstractAccountDAO<User> {
}
