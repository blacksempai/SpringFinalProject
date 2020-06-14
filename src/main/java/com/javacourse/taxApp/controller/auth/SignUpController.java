package com.javacourse.taxApp.controller.auth;

import com.javacourse.taxApp.controller.utils.ValidationManager;
import com.javacourse.taxApp.model.TaxGroup;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignUpController {
    @Qualifier("userService")
    private AccountService<User> userService;
    private static final String SUCCESS_PAGE = "redirect:/";
    private static final String SIGN_UP_PAGE = "auth/sign-up";

    @Autowired
    public SignUpController(AccountService<User> userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/sign-up")
    public String getSignUpPage() {
        return SIGN_UP_PAGE;
    }

    @PostMapping("/auth/sign-up")
    public String signUp(HttpServletRequest request) {
        User user = buildUser(request);
        if (ValidationManager.isValid(user)){
            if (userService.register(user))
                return SUCCESS_PAGE;
            request.setAttribute("message","msg.login-exists");
            return SIGN_UP_PAGE;
        }
        request.setAttribute("message",ValidationManager.getFirstErrorMessage(user));
        return SIGN_UP_PAGE;
    }

    private User buildUser(HttpServletRequest request) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFullName(request.getParameter("full_name"));
        user.setCompany(request.getParameter("company"));
        user.setEmail(request.getParameter("email"));
        user.setAddress(request.getParameter("address"));
        user.setPassport(request.getParameter("passport"));
        if (request.getParameter("group")!=null)
            user.setGroup(TaxGroup.valueOf(request.getParameter("group").toUpperCase()));
        return user;
    }

}
