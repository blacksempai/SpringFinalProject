package com.javacourse.taxApp.controller.admin;

import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AdminPanelController {
    @Qualifier("inspectorService")
    private AccountService<Inspector> inspectorService;

    @Autowired
    public AdminPanelController(AccountService<Inspector> inspectorService) {
        this.inspectorService = inspectorService;
    }

    @GetMapping("/admin/panel")
    public String adminPanel(HttpServletRequest request) {
        request.setAttribute("inspectors",inspectorService.getAll());
        return "admin/panel";
    }
}
