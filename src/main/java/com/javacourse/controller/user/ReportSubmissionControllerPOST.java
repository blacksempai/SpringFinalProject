package com.javacourse.controller.user;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.controller.utils.parser.ReportParser;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;
import com.javacourse.model.entities.report.Report;
import com.javacourse.view.Messages;
import com.javacourse.view.PagePath;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ReportSubmissionControllerPOST implements ControllerCommand {
    private DAOFactory factory;
    private static Logger logger = Logger.getLogger(ReportSubmissionControllerPOST.class);
    private InspectorDAO inspectorDAO;
    private ReportDAO reportDAO;
    private String home;
    private String report;

    @Autowired
    public ReportSubmissionControllerPOST(DAOFactory factory) {
        this.factory = factory;
        inspectorDAO = factory.createInspectorDAO();
        reportDAO = factory.createReportDAO();
        home = PagePath.getProperty("page.home");
        report = PagePath.getProperty("page.report-form");
    }

    @Override
    @PostMapping("/user/new-report")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Report report = buildTaxReportFromRequest(request);
            reportDAO.create(report);
        }
        catch (NullPointerException | NumberFormatException e){
            logger.error("Error posting new report",e);
            request.setAttribute("message", Messages.getProperty("msg.form-error", request));
            return report;
        }
        return home;
    }

    private Report buildTaxReportFromRequest(HttpServletRequest request)
            throws NumberFormatException, NullPointerException{
        User user = (User) request.getSession().getAttribute("user");
        ReportParser parser = user.getGroup().getReportParser();
        Report report = parser.parseRequest(request);
        report.setInspector(selectInspectorAndUpdate());
        report.setUser(user);
        return report;
    }

    private Inspector selectInspectorAndUpdate(){
        Inspector inspector = inspectorDAO.getInspectorWithLessReportsInService();
        int reportsInService = inspector.getReportsInService();
        inspector.setReportsInService(reportsInService+1);
        inspectorDAO.update(inspector);
        return inspector;
    }

}
