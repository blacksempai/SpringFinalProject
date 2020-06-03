package com.javacourse.controller.user;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.entities.User;
import com.javacourse.model.entities.report.Report;
import com.javacourse.view.PagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DisplayAllReportsController implements ControllerCommand {
    private DAOFactory factory;
    private ReportDAO reportDAO;

    @Autowired
    public DisplayAllReportsController(DAOFactory factory) {
        this.factory = factory;
        reportDAO = factory.createReportDAO();
    }

    @Override
    @GetMapping("/user/all-reports")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Report> reports = reportDAO.getReportsByUser(user);
        request.setAttribute("reports", reports);
        return PagePath.getProperty("page.reports");
    }
}
