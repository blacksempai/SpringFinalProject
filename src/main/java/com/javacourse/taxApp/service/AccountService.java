package com.javacourse.taxApp.service;

import com.javacourse.taxApp.model.entities.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService<T extends Account> extends UserDetailsService {

    T update(T account);

    boolean register(T account);

    T find(String username);

    List<T> getAll();

    boolean isExist(String username);

}
