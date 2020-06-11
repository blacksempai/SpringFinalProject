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
public class DisplayReportController  {
    private ReportService reportService;

    @GetMapping("/user/my-report")
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Report> reports = reportService.getAll(user);
        for (Report report:reports) {
            if (report.getId().equals(Long.parseLong(request.getParameter("id")))){
                TaxReportSummary summary = TaxReportSummaryCalculator.calculateSummary(report, reportService);
                request.setAttribute("report",report);
                request.setAttribute("summary", summary);
                return "user/report_view";
            }
        }
        return "/error";
    }
}
