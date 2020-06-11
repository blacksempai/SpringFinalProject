package com.javacourse.taxApp.controller.user;

import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Controller
public class UserProfileController {
    @Qualifier("userService")
    AccountService<User> userService;

    @GetMapping("/user/profile")
    public String execute() {
        return "user/profile";
    }
}
