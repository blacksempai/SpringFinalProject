package com.javacourse.dao;

import com.javacourse.model.entities.Account;
import com.javacourse.model.entities.Admin;

public interface AdminDAO{

    Admin getByLogin(String login);

    boolean isExists(String login);

}
