package com.javacourse.taxApp.controller.inspector;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class InspectorProfileController {

    @GetMapping("/inspector/profile")
    public String profile(HttpServletRequest request, HttpServletResponse response) {
        return "inspector/profile";
    }
}
