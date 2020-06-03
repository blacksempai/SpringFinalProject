package com.javacourse.controller.auth;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.view.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignOutController implements ControllerCommand {
    @Override
    @GetMapping("/auth/sign-out")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return PagePath.getProperty("page.home");
    }
}
