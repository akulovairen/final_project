package com.example.servletstest.controller;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.model.User;
import com.example.servletstest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Controller for user profile.
 */
@WebServlet( value = "/profile")
public class ProfileServlet extends HttpServlet {
    private UsersDao usersDao=new UsersDao();
    private final UserService usersService=new UserService(usersDao);

    /**
     * Gets user profile by user id.
     *
     * @param req - request object with user id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("user_id");
        Optional<User> userById = usersService.getUserById(userId);
        userById.ifPresent(user -> {
            req.setAttribute("userFound", user);
            req.setAttribute("role", user.getRole());
        });

        req.getRequestDispatcher("/WEB-INF/jsp/profile.jsp").forward(req, resp);
    }

}
