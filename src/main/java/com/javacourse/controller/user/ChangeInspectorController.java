package com.javacourse.controller.user;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.ReportDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;
import com.javacourse.model.entities.report.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ChangeInspectorController implements ControllerCommand {
    private DAOFactory factory;
    private DisplayAllReportsController reportsController;
    private InspectorDAO inspectorDAO;
    private ReportDAO reportDAO;

    @Autowired
    public ChangeInspectorController(DAOFactory factory) {
        this.factory = factory;
        reportsController = new DisplayAllReportsController(factory);
        inspectorDAO = factory.createInspectorDAO();
        reportDAO = factory.createReportDAO();
    }

    @Override
    @GetMapping("/user/change-inspector")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Report> reports = reportDAO.getReportsByUser(user);
        for (Report report : reports) {
            if (report.getId().equals(Integer.parseInt(request.getParameter("id")))){
                changeInspectorInReport(report);
                reportDAO.update(report);
            }
        }
        return reportsController.execute(request, response);
    }

    private void changeInspectorInReport(Report report){
        Inspector inspector = report.getInspector();
        inspector.setComplaintNumber(inspector.getComplaintNumber()+1);
        inspector.setReportsInService(inspector.getReportsInService()-1);
        inspectorDAO.update(inspector);

        inspector = getAnotherInspector(inspector);
        inspector.setReportsInService(inspector.getReportsInService()+1);
        report.setInspector(inspector);
        inspectorDAO.update(inspector);
    }

    private Inspector getAnotherInspector(Inspector inspector){
        Inspector anotherInspector = null;
        List<Inspector> inspectors = inspectorDAO.getAllInspectors();
        int reportsInService = -1;
        for (Inspector i : inspectors) {
            if (i.getId().equals(inspector.getId()))
                continue;
            if (i.getReportsInService()>reportsInService){
                reportsInService = i.getReportsInService();
                anotherInspector = i;
            }
        }
        return anotherInspector;
    }

}
