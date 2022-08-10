package com.example.servletstest.controller.admin;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Topic;
import com.example.servletstest.service.resthandlers.UserRegistrationService;
import com.example.servletstest.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Controller for registration teacher.
 */
@WebServlet(value = "/registrationTeacher")
public class RegistrationTeacherServlet extends HttpServlet {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final UserRegistrationService userRegistrationService = new UserRegistrationService(new UsersDao());

    /**
     *
     * @param req - request object
     * @param resp - response object
     */
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
        req.getRequestDispatcher("/WEB-INF/jsp/admin/registrationTeacher.jsp").forward(req,resp);
    }

    /**
     * Registration teacher.
     *
     * @param req - request object with email, password, password repeat, name, surname, birthday
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email").toLowerCase();
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("password_repeat");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"), formatter);

//        if (!password.equals(passwordRepeat)) {
//            Map<String, String> messagesMap = Map.of("repeat_password", "Паролі повинні збігатися");
//            req.getSession().setAttribute("messagesMap", messagesMap);
//            RequestUtils.restoreFormValues(req);
//            resp.sendRedirect("/registrationTeacher");
//            return;
//        }
        Locale locale = (Locale) req.getSession().getAttribute("userLocale");
        try {
            userRegistrationService.registerUser(name, surname, email, password, passwordRepeat, "teacher", birthday,locale );
            resp.sendRedirect("/adminTeacherList");
        } catch (CustomValidationException e) {
            req.getSession().setAttribute("messagesMap", e.getErrorsMap());
            RequestUtils.restoreFormValues(req);
            resp.sendRedirect("/registrationTeacher");
        }
    }
}
