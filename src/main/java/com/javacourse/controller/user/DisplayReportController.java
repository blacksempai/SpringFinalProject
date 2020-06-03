package com.javacourse.controller.user;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.TaxReportSummary;
import com.javacourse.model.TaxReportSummaryCalculator;
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
public class DisplayReportController implements ControllerCommand {
    private DAOFactory factory;
    private ReportDAO reportDAO;

    @Autowired
    public DisplayReportController(DAOFactory factory) {
        this.factory = factory;
        reportDAO = factory.createReportDAO();
    }

    @Override
    @GetMapping("/user/my-report")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Report> reports = reportDAO.getReportsByUser(user);
        for (Report report:reports) {
            if (report.getId().equals(Integer.parseInt(request.getParameter("id")))){
                TaxReportSummary summary = TaxReportSummaryCalculator.calculateSummary(report,factory);
                request.setAttribute("report",report);
                request.setAttribute("summary", summary);
                return PagePath.getProperty("page.report-view");
            }
        }
        return PagePath.getProperty("page.error");
    }
}
