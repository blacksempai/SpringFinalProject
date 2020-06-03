package com.javacourse.controller.admin;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.entities.Inspector;
import com.javacourse.security.PasswordHashing;
import com.javacourse.view.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class InspectorChangeController implements ControllerCommand {
    private DAOFactory factory;
    private AdminPanelController adminPanel;
    private InspectorDAO inspectorDAO;

    @Autowired
    public InspectorChangeController(DAOFactory factory) {
        this.factory = factory;
        inspectorDAO = factory.createInspectorDAO();
        AdminPanelController adminPanel = new AdminPanelController(factory);;
    }

    @Override
    @PostMapping("/admin/change")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("inspectors",inspectorDAO.getAllInspectors());
        if (isValid(request)) {
            Inspector inspector = buildInspector(request);
            inspectorDAO.update(inspector);
        }
        return adminPanel.execute(request,response);
    }

    private Inspector buildInspector(HttpServletRequest request){
        Inspector inspector = new Inspector();
        byte[] salt = PasswordHashing.getSalt();
        String passHash = PasswordHashing.getSaltedPasswordHash(request.getParameter("password"),salt);
        String saltStr = PasswordHashing.byteToString(salt);
        inspector.setLogin(request.getParameter("login"));
        inspector.setPasswordHash(passHash);
        inspector.setSalt(saltStr);
        inspector.setFullName(request.getParameter("full_name"));
        inspector.setEmail(request.getParameter("email"));
        inspector.setComplaintNumber(Integer.parseInt(request.getParameter("complaints")));
        inspector.setReportsInService(Integer.parseInt(request.getParameter("service")));
        return inspector;
    }

    private boolean isValid(HttpServletRequest request){
        if(request.getParameter("login")==null||(request.getParameter("password")==null||request.getParameter("email")==null)){
            request.setAttribute("message", Messages.getProperty("msg.fields-error",request));
            return false;
        }
        if (!loginIsValid(request.getParameter("login"))){
            request.setAttribute("message",Messages.getProperty("msg.login-error",request));
            return false;
        }
        if (!passwordIsValid(request.getParameter("password"))){
            request.setAttribute("message",Messages.getProperty("msg.pass-error",request));
            return false;
        }
        if (!emailIsValid(request.getParameter("email"))){
            request.setAttribute("message",Messages.getProperty("msg.mail-error",request));
            return false;
        }
        return true;
    }

    private boolean loginIsValid(String login){
        Pattern pattenObj = Pattern
                .compile("^[0-9a-zA-Z]{3,20}$");
        Matcher matcherObj = pattenObj.matcher(login);
        return matcherObj.matches();
    }

    private boolean passwordIsValid(String password){
        Pattern pattenObj = Pattern
                .compile("^(?=.*[0-9].*)(?=.*[a-z].*)(?=.*[A-Z].*)[0-9a-zA-Z]{8,}$");
        Matcher matcherObj = pattenObj.matcher(password);
        return matcherObj.matches();
    }

    private boolean emailIsValid(String email){
        Pattern pattenObj = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcherObj = pattenObj.matcher(email);
        return matcherObj.matches();
    }
}
