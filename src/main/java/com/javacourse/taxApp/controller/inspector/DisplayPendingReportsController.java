package com.javacourse.taxApp.controller.inspector;

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
public class DisplayPendingReportsController {
    private ReportService reportService;


    @GetMapping("/inspector/reports")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
        List<Report> reports = reportService.getAll(inspector);
        removeAllNonPendingReports(reports);
        request.setAttribute("reports", reports);
        return "inspector/inspector_reports";
    }

    private void removeAllNonPendingReports(List<Report> reports){
        reports.removeIf(report -> !report.getStatus().equals(Report.Status.PENDING));
    }
}
