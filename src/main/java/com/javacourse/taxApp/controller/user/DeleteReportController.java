package com.javacourse.taxApp.controller.user;

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
public class DeleteReportController {
    ReportService reportService;
    DisplayAllReportsController reportsController;

    @GetMapping("/user/delete-report")
    public String deleteReport(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Report> reports = reportService.getAll(user);
        for (Report report : reports) {
            if (report.getId().equals(Long.parseLong(request.getParameter("id")))){
                reportService.delete(report);
            }
        }
        return reportsController.displayReports(request);
    }

}
