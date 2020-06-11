package com.javacourse.taxApp.controller.user;

import com.javacourse.taxApp.controller.utils.parser.ReportParser;
import com.javacourse.taxApp.model.entities.User;
import com.javacourse.taxApp.model.entities.report.Report;
import com.javacourse.taxApp.service.AccountService;
import com.javacourse.taxApp.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@Controller
public class ReportSubmissionController {
    @Qualifier("userService")
    AccountService<User> userService;
    ReportService reportService;


    @GetMapping("/user/new-report")
    public String getReportForm() {
        return "user/report_form";
    }

    @PostMapping("/user/new-report")
    public String submitReport(HttpServletRequest request) {
        try {
            Report report = buildReport(request);
            reportService.save(report);
        } catch (NullPointerException | NumberFormatException e) {
            log.error(e.getMessage());
            request.setAttribute("message", "msg.form-error");
            return "user/report_form";
        }
        return "redirect:/";
    }

    private Report buildReport(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        ReportParser parser = user.getGroup().getReportParser();
        Report report = parser.parseRequest(request);
        report.setUser(user);
        report.setInspector(reportService.chooseInspector());
        return report;
    }

}
