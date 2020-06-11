package com.javacourse.taxApp.controller.user;

import com.javacourse.taxApp.controller.utils.parser.ReportParser;
import com.javacourse.taxApp.model.TaxReportSummaryCalculator;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.model.entities.report.Report;
import com.javacourse.taxApp.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@Controller
public class ReportEditControllerPOST  {
    ReportService reportService;

    @PostMapping("/user/report-edit")
    public String execute(HttpServletRequest request) {
        Report oldReport = getReportFromDB(request);
        if (oldReport==null)
            return "error";
        try {
            Report newReport = parseReportFromRequest(request);
            updateReport(oldReport,newReport);
        }
        catch (NullPointerException | NumberFormatException e){
            log.error("Error editing report",e);
            request.setAttribute("message","msg.form-error");
            request.setAttribute("report", oldReport);
            request.setAttribute("summary", TaxReportSummaryCalculator.calculateSummary(oldReport,reportService));
            return "user/report_edit?id="+request.getParameter("id");
        }
        return "redirect:/";
    }

    private Report getReportFromDB(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Long reportId = Long.parseLong(request.getParameter("id"));
        List<Report> reports = reportService.getAll(user);
        for (Report report : reports) {
            if (report.getId().equals(reportId)){
                return report;
            }
        }
        return null;
    }

    private Report parseReportFromRequest(HttpServletRequest request){
        ReportParser parser = new ReportParser();
        return parser.parseRequest(request);
    }

    private void updateReport(Report oldReport, Report newReport){
        newReport.setStatus(Report.Status.PENDING);
        newReport.setInspector(oldReport.getInspector());
        newReport.setUser(oldReport.getUser());
        newReport.setReview(oldReport.getReview());
        newReport.setId(oldReport.getId());
        reportService.save(newReport);
    }

}
