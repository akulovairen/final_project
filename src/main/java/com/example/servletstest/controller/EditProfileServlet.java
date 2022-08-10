package com.example.servletstest.controller;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.User;
import com.example.servletstest.service.UserService;
import com.example.servletstest.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static java.lang.System.out;

/**
 * Controller for edit profile.
 */
@WebServlet(value = "/editProfile")
public class EditProfileServlet extends HttpServlet {
    private UsersDao usersDao=new UsersDao();
    private final UserService usersService= new UserService(usersDao);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Gets user profile info by user id.
     *
     * @param req - request object with user id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("user_id");
        Optional<User> userById = usersService.getUserById(userId);
        userById.ifPresent(user -> req.setAttribute("user", user));

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
        req.getRequestDispatcher("/WEB-INF/jsp/editProfile.jsp").forward(req, resp);
    }

    /**
     * Update user profile.
     *
     * @param req - request object with user id, name, surname, email,birthday
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String birthdayStr = req.getParameter("birthday");
        LocalDate birthday = birthdayStr == null || birthdayStr.isEmpty() ? null : LocalDate.parse(birthdayStr, formatter);
        int userId = (int) req.getSession().getAttribute("user_id");

        Locale locale = (Locale) req.getSession().getAttribute("userLocale");
        try {
            usersService.updateUser(userId, name, surname, birthday, email,locale);
            resp.sendRedirect("/profile");
        } catch (CustomValidationException e) {
            req.getSession().setAttribute("messagesMap", e.getErrorsMap());
            RequestUtils.restoreFormValues(req);
            resp.sendRedirect("/editProfile?user_id=" + userId);
        }
    }
}
