package com.example.servletstest.controller.admin;

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
 * Controller for admin.
 */
@WebServlet(name = "AdminPageServlet", value = "/adminPage")
public class AdminPageServlet extends HttpServlet {
    UsersDao usersDao=new UsersDao();
    UserService usersService=new UserService(usersDao);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("user_id");

        Optional<User> userById = usersService.getUserById(userId);
        userById.ifPresent(user -> req.setAttribute("userById", userById));

        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminPage.jsp").forward(req, resp);
    }
}
