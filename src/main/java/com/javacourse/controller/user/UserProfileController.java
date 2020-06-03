package com.javacourse.controller.user;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.view.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserProfileController implements ControllerCommand {

    @Override
    @GetMapping("/user/profile")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagePath.getProperty("page.user-profile");
    }
}
