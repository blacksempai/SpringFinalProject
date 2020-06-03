package com.javacourse.controller;

import com.javacourse.view.PagePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String execute(){
        return PagePath.getProperty("page.home");
    }
}
