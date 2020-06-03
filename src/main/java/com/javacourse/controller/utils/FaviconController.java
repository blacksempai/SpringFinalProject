package com.javacourse.controller.utils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FaviconController {
    @RequestMapping("favicon.ico")
    public String favicon() {
        return "forward:/graphics/favicon.ICO";
    }
}