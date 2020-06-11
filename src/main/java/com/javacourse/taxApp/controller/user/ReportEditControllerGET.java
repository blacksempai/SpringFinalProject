package com.javacourse.taxApp.controller.user;

import com.javacourse.taxApp.model.TaxReportSummary;
import com.javacourse.taxApp.model.TaxReportSummaryCalculator;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.model.entities.report.Report;
import com.javacourse.taxApp.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Controller
public class ReportEditControllerGET {
    ReportService reportService;

    @GetMapping("/user/report-edit")
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Long reportID = Long.parseLong(request.getParameter("id"));
        List<Report> reports = reportService.getAll(user);
        for (Report report:reports) {
            if (report.getId().equals(reportID)) {
                TaxReportSummary summary = TaxReportSummaryCalculator.calculateSummary(report,reportService);
                request.setAttribute("report",report);
                request.setAttribute("summary", summary);
                return "user/report_edit";
            }
        }
        return "error";
    }
}
