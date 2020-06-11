package com.javacourse.taxApp.service.impl;

import com.javacourse.taxApp.dao.AbstractAccountDAO;
import com.javacourse.taxApp.model.entities.Account;
import com.javacourse.taxApp.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@Qualifier("accountService")
public class AccountServiceImpl<T extends Account> implements AccountService<T> {
    @Autowired
    private AbstractAccountDAO<T> accountDAO;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public T update(T account) {
        return accountDAO.save(account);
    }

    @Override
    @Transactional
    public boolean register(T account) {
        try {
            account.setPassword(encoder.encode(account.getPassword()));
            verifyUserHasNotYetRegistered(account.getUsername());
            accountDAO.save(account);
            return true;
        } catch (IllegalArgumentException e){
            log.info(e.getMessage(), e);
        }
        return false;
    }

    private void verifyUserHasNotYetRegistered(String username) {
        if (accountDAO.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("User with the provided username already exists");
        }
    }

    @Override
    @Transactional
    public T find(String username) {
        return accountDAO.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<T> getAll() {
        return accountDAO.findAll();
    }

    @Override
    @Transactional
    public boolean isExist(String username) {
        return accountDAO.findByUsername(username).isPresent();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return accountDAO.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
