package com.example.servletstest.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for logout.
 */
@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * Logout user.
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/coursesListGuest");
    }
}
