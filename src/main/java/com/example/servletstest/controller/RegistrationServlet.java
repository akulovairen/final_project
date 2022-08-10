package com.example.servletstest.controller;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.service.resthandlers.UserRegistrationService;
import com.example.servletstest.util.RequestUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Controller for registration user.
 */
@WebServlet(value = "/registration")
public class RegistrationServlet extends HttpServlet {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final UserRegistrationService registrationService=new UserRegistrationService(new UsersDao());
    private Logger log = LogManager.getLogger(this);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> errorMap = (Map<String, String>) req.getSession().getAttribute("messagesMap");
        if(errorMap!=null && !errorMap.isEmpty()){
            req.setAttribute("messagesMap",errorMap);
        }
        Map<String, String> restoredValues = (Map<String, String>) req.getSession().getAttribute("restoredValues");
        if(restoredValues!=null && !restoredValues.isEmpty()){
            req.setAttribute("restoredValues",restoredValues);
        }
        req.getSession().removeAttribute("messagesMap");
        req.getSession().removeAttribute("restoredValues");

        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    /**
     * Registrations user. Data validation.
     *
     * @param req - request object with name, surname, email, password, password_repeat, birthday
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email") == null ? null : req.getParameter("email").toLowerCase();
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password_repeat");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"), formatter);

//        if(!password.equals(passwordRepeat)){
//            Map<String, String> messagesMap = Map.of("repeat_password", "Паролі повинні збігатися");
//            req.getSession().setAttribute("messagesMap", messagesMap);
//            RequestUtils.restoreFormValues(req);
//            resp.sendRedirect("/registration");
////            req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
//            return;
//        }
        Locale locale = (Locale) req.getSession().getAttribute("userLocale");
        try {
            int studentId = registrationService.registerUser(name, surname, email, password, passwordRepeat, "student", birthday, locale);
            HttpSession userSession = req.getSession();
            if("admin".equalsIgnoreCase(String.valueOf(userSession.getAttribute("role")))){
                resp.sendRedirect("/adminPage");
            }else {
                userSession.setAttribute("user_id",studentId);
                userSession.setAttribute("userName", name);
                userSession.setAttribute("userSurname", surname);
                userSession.setAttribute("role", "student");
                resp.sendRedirect("/coursesInProgress");
            }
        } catch (CustomValidationException e) {
            req.getSession().setAttribute("messagesMap", e.getErrorsMap());
            RequestUtils.restoreFormValues(req);
            resp.sendRedirect("/registration");
        }
    }

//    private boolean validateEmail(String emailStr) {
//        log.info("User's EMAIL input: {}", emailStr);
//        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
//        return matcher.find();
//    }
}
