package com.javacourse.controller.inspector;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.TaxReportSummary;
import com.javacourse.model.TaxReportSummaryCalculator;
import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.report.Report;
import com.javacourse.view.PagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ReportReviewController implements ControllerCommand {
    private DAOFactory factory;
    private ReportDAO reportDAO;

    @Autowired
    public ReportReviewController(DAOFactory factory) {
        this.factory = factory;
        reportDAO = factory.createReportDAO();
    }

    @Override
    @GetMapping("/inspector/review")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
        List<Report> reports = reportDAO.getReportsByInspector(inspector);
        for (Report report:reports) {
            if (report.getId().equals(Integer.parseInt(request.getParameter("id")))) {
                TaxReportSummary summary = TaxReportSummaryCalculator.calculateSummary(report,factory);
                request.setAttribute("report",report);
                request.setAttribute("summary", summary);
                return PagePath.getProperty("page.review");
            }
        }
        return PagePath.getProperty("page.error");
    }
}
