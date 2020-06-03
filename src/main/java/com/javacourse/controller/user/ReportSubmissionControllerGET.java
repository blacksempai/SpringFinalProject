package com.javacourse.controller.user;


import com.javacourse.controller.ControllerCommand;
import com.javacourse.view.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReportSubmissionControllerGET implements ControllerCommand {
    @Override
    @GetMapping("/user/new-report")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PagePath.getProperty("page.report-form");
    }

}
