package com.javacourse.taxApp.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @GetMapping("/auth/sign-in")
    public String getSignInPage() {
        return "auth/sign-in";
    }

}
