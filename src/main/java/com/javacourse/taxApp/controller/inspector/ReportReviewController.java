package com.javacourse.taxApp.controller.inspector;

import com.javacourse.taxApp.model.TaxReportSummary;
import com.javacourse.taxApp.model.TaxReportSummaryCalculator;
import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.report.Report;
import com.javacourse.taxApp.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Controller
public class ReportReviewController{
    private ReportService reportService;

    @GetMapping("/inspector/review")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
        List<Report> reports = reportService.getAll(inspector);
        for (Report report:reports) {
            if (report.getId().equals(Long.parseLong(request.getParameter("id")))) {
                TaxReportSummary summary = TaxReportSummaryCalculator.calculateSummary(report,reportService);
                request.setAttribute("report",report);
                request.setAttribute("summary", summary);
                return "inspector/report_review";
            }
        }
        return "error";
    }
}
