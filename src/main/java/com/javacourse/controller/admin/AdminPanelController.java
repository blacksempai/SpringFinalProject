package com.javacourse.controller.admin;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.view.PagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminPanelController implements ControllerCommand {
    private DAOFactory factory;
    private InspectorDAO inspectorDAO;

    @Autowired
    public AdminPanelController(DAOFactory factory) {
        this.factory = factory;
        inspectorDAO = factory.createInspectorDAO();
    }

    @Override
    @GetMapping("/admin/panel")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("inspectors",inspectorDAO.getAllInspectors());
        return PagePath.getProperty("page.admin");
    }
}
