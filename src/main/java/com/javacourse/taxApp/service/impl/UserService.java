package com.javacourse.taxApp.service.impl;

import com.javacourse.taxApp.model.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("userService")
public class UserService extends AccountServiceImpl<User> {
}
