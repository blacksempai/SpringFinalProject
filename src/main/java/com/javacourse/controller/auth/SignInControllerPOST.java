package com.javacourse.controller.auth;

import com.javacourse.controller.ControllerCommand;
import com.javacourse.dao.AdminDAO;
import com.javacourse.dao.InspectorDAO;
import com.javacourse.dao.UserDAO;
import com.javacourse.dao.factory.DAOFactory;
import com.javacourse.model.entities.Admin;
import com.javacourse.model.entities.Inspector;
import com.javacourse.model.entities.User;
import com.javacourse.security.PasswordHashing;
import com.javacourse.view.Messages;
import com.javacourse.view.PagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInControllerPOST implements ControllerCommand {
    private DAOFactory factory;
    private UserDAO userDAO;
    private InspectorDAO inspectorDAO;
    private AdminDAO adminDAO;
    private String homePage;
    private String signInPage;

    @Autowired
    public SignInControllerPOST(DAOFactory factory) {
        this.factory = factory;
        userDAO = factory.createUserDAO();
        inspectorDAO = factory.createInspectorDAO();
        adminDAO = factory.createAdminDAO();
        homePage = PagePath.getProperty("page.home");
        signInPage = PagePath.getProperty("page.sign-in");
    }

    @Override
    @PostMapping("/auth/sign-in")
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (userDAO.isExists(login)){
            User user = userDAO.getByLogin(login);
            byte[] salt = PasswordHashing.stringToByte(user.getSalt());
            String passHash = PasswordHashing.getSaltedPasswordHash(password,salt);
            if (user.getPasswordHash().equals(passHash)){
                request.getSession().setAttribute("user", user);
                return homePage;
            }
        }
        if (inspectorDAO.isExists(login)){
            Inspector inspector = inspectorDAO.getByLogin(login);
            byte[] salt = PasswordHashing.stringToByte(inspector.getSalt());
            String passHash = PasswordHashing.getSaltedPasswordHash(password,salt);
            if (inspector.getPasswordHash().equals(passHash)){
                request.getSession().setAttribute("inspector", inspector);
                return homePage;
            }
        }
        if (adminDAO.isExists(login)){
            Admin admin = adminDAO.getByLogin(login);
            byte[] salt = PasswordHashing.stringToByte(admin.getSalt());
            String passHash = PasswordHashing.getSaltedPasswordHash(password,salt);
            if (admin.getPasswordHash().equals(passHash)){
                request.getSession().setAttribute("admin", admin);
                return homePage;
            }
        }
        request.setAttribute("message", Messages.getProperty("msg.sign-in-error", request));
        return signInPage;
    }
}
