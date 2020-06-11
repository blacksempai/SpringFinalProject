package com.javacourse.taxApp.controller.admin;

import com.javacourse.taxApp.controller.utils.ValidationManager;
import com.javacourse.taxApp.model.entities.Inspector;
import com.javacourse.taxApp.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor(onConstructor_ = @Autowired)
@Controller
public class InspectorRegisterController {
    private AdminPanelController adminPanel;
    @Qualifier("inspectorService")
    private AccountService<Inspector> inspectorService;


    @PostMapping({"/admin/add","/admin/change"})
    public String registerInspector(HttpServletRequest request) {
        Inspector inspector = buildInspector(request);
        if (ValidationManager.isValid(inspector)){
            if (inspectorService.register(inspector))
                return adminPanel.adminPanel(request);
            request.setAttribute("message", "msg.login-exists");
            return adminPanel.adminPanel(request);
        }
        request.setAttribute("message", ValidationManager.getFirstErrorMessage(inspector));
        return adminPanel.adminPanel(request);
    }

    private Inspector buildInspector(HttpServletRequest request){
        Inspector inspector = new Inspector();
        inspector.setUsername(request.getParameter("username"));
        inspector.setPassword(request.getParameter("password"));
        inspector.setFullName(request.getParameter("full_name"));
        inspector.setEmail(request.getParameter("email"));
        inspector.setComplaintNumber(Integer.parseInt(request.getParameter("complaints")));
        inspector.setReportsInService(Integer.parseInt(request.getParameter("service")));
        return inspector;
    }

}
