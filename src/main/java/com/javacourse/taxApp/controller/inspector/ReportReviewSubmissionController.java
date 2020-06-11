package com.javacourse.taxApp.controller.inspector;

import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.model.entities.report.Report;
import com.javacourse.taxApp.service.AccountService;
import com.javacourse.taxApp.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Controller
public class ReportReviewSubmissionController {
    private ReportService reportService;
    @Qualifier("inspectorService")
    private AccountService<Inspector> inspectorService;


    @PostMapping("/inspector/review")
    public String reviewSubmission(HttpServletRequest request, HttpServletResponse response) {
        Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
        Long reportId = Long.parseLong(request.getParameter("id"));
        List<Report> reports = reportService.getAll(inspector);
        for (Report report : reports) {
            if (report.getId().equals(reportId)){
                updateReport(request,report);
                updateInspector(inspector);
            }
        }
        return "inspector/inspector_reports";
    }

    private void updateReport(HttpServletRequest request, Report report){
        if(request.getParameter("status").equals("true")) {
            report.setStatus(Report.Status.ACCEPTED);
        }
        if (request.getParameter("status").equals("false")){
            report.setStatus(Report.Status.DECLINED);
            String reviewText = request.getParameter("review_text");
            report.setReview(reviewText);
        }
        reportService.save(report);
    }

    private void updateInspector(Inspector inspector){
        inspector.setReportsInService(inspector.getReportsInService()-1);
        inspectorService.register(inspector);
    }

}
