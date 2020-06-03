package com.javacourse.controller.user;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.entities.User;
import com.javacourse.model.entities.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class DeleteReportController implements ControllerCommand {
    private DAOFactory factory;
    private DisplayAllReportsController reportsController;
    private ReportDAO reportDAO;

    @Autowired
    public DeleteReportController(DAOFactory factory) {
        this.factory = factory;
        reportsController = new DisplayAllReportsController(factory);
        reportDAO = factory.createReportDAO();
    }

    @Override
    @GetMapping("/user/delete-report")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Report> reports = reportDAO.getReportsByUser(user);
        for (Report report : reports) {
            if (report.getId().equals(Integer.parseInt(request.getParameter("id")))){
                reportDAO.delete(report);
            }
        }
        return reportsController.execute(request, response);
    }

}
