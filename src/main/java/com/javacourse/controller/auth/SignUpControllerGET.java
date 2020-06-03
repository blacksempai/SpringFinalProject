package com.javacourse.controller.auth;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.view.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignUpControllerGET implements ControllerCommand {
    @Override
    @GetMapping("/auth/sign-up")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagePath.getProperty("page.sign-up");
    }
}
