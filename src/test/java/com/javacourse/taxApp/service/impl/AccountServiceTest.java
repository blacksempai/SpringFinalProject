package com.javacourse.taxApp.service.impl;

import com.javacourse.taxApp.dao.AbstractAccountDAO;
import com.javacourse.taxApp.dao.AccountDAO;
import com.javacourse.taxApp.model.entities.Account;
import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AccountServiceTest {

    @Autowired
    @Qualifier("accountService")
    private AccountService<Account> accountService;

    @MockBean
    private AccountDAO accountDAO;

    private Account account;

    @BeforeEach
    void setUp() {
        this.account = new Account();
        account.setUsername("test");
        account.setPassword("test");
        account.setEmail("mytestmail@gmail.com");
    }

    @Test
    void register() throws Exception {
        assertTrue(accountService.register(account));
        Mockito.verify(accountDAO, Mockito.times(1)).save(account);
    }

}