package com.javacourse.controller.inspector;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.factory.DAOFactory;
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
public class DisplayPendingReportsController implements ControllerCommand {
    private DAOFactory factory;
    private ReportDAO reportDAO;

    @Autowired
    public DisplayPendingReportsController(DAOFactory factory) {
        this.factory = factory;
        reportDAO = factory.createReportDAO();
    }

    @Override
    @GetMapping("/inspector/reports")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Inspector inspector = (Inspector) request.getSession().getAttribute("inspector");
        List<Report> reports = reportDAO.getReportsByInspector(inspector);
        removeAllNonPendingReports(reports);
        request.setAttribute("reports", reports);
        return PagePath.getProperty("page.inspector-reports");
    }

    private void removeAllNonPendingReports(List<Report> reports){
        reports.removeIf(report -> !report.getStatus().equals(Report.Status.PENDING));
    }
}
