package com.example.servletstest.controller;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.model.User;
import com.example.servletstest.service.UserService;
import com.example.servletstest.service.resthandlers.UserAuthenticationService;
import com.example.servletstest.util.LocalizedValidatorUtil;
import com.example.servletstest.util.RequestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.*;

/**
 * Controller for login user.
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final UserAuthenticationService authenticationService=new UserAuthenticationService();
    private UsersDao usersDao=new UsersDao();
    private final UserService usersService=new UserService(usersDao);
    Logger log= LogManager.getLogger(this);

    /**
     *
     * @param request - request object
     * @param response - response object
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("GET REQUEST ID="+ request.getSession().getId());
        Map<String, String> errorMap = (Map<String, String>) request.getSession().getAttribute("messagesMap");
        if(errorMap!=null && !errorMap.isEmpty()){
            request.setAttribute("messagesMap",errorMap);
        }
        Map<String, String> restoredValues = (Map<String, String>) request.getSession().getAttribute("restoredValues");
        if(restoredValues!=null && !restoredValues.isEmpty()){
            request.setAttribute("restoredValues",restoredValues);
        }
        request.getSession().removeAttribute("messagesMap");
        request.getSession().removeAttribute("restoredValues");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Login user. Data validation. Distribution of sign on by roles.
     *
     * @param request - request object with user email, password
     * @param response - response object
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Locale locale = (Locale) request.getSession().getAttribute("userLocale");
        Set<ConstraintViolation<User>> email1 = LocalizedValidatorUtil.getValidatorByLocale(locale).validateValue(User.class, "email", email);
        Map<String, String> messagesMap = new HashMap<>();
        for (ConstraintViolation violation : email1){
            System.out.println(violation.getMessage());
            System.out.println(violation.getInvalidValue());
            messagesMap.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        if (!messagesMap.isEmpty()) {
            request.setAttribute("messagesMap", messagesMap);
            RequestUtils.restoreFormValues(request);
            log.info("POST REQUEST ID="+ request.getSession().getId());
            request.getSession().setAttribute("messagesMap", messagesMap);
            response.sendRedirect("/login");
            return;
        }

        if (authenticationService.isAuthenticated(email, password)) {
            User user = usersService.getUserByEmail(email);
            Boolean locked = user.getLocked();
            if(locked){
                messagesMap.put("authenticationError", LocalizedValidatorUtil.getLocalizationValue("login.block.invalid",locale));
                request.getSession().setAttribute("messagesMap", messagesMap);
                RequestUtils.restoreFormValues(request);
                response.sendRedirect("/login");
                return;
            }
            final HttpSession userSession = request.getSession();
            userSession.setAttribute("email", email);
            userSession.setAttribute("userName", user.getName());
            userSession.setAttribute("userSurname", user.getSurname());
            userSession.setAttribute("role", user.getRole());
            userSession.setAttribute("user_id", user.getId());

            switch (user.getRole()) {
                case "student":
                    response.sendRedirect("/coursesInProgress");
                    break;
                case "admin":
                    response.sendRedirect("/adminPage");
                    break;
                case "teacher":
                    response.sendRedirect("/teacherPage");
                    break;
            }
        } else {
            messagesMap.put("authenticationError", LocalizedValidatorUtil.getLocalizationValue("login.emailPassword.invalid", locale));
            request.getSession().setAttribute("messagesMap", messagesMap);
            RequestUtils.restoreFormValues(request);
            response.sendRedirect("/login");
        }
    }
}
