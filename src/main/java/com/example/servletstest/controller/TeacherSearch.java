package com.example.servletstest.controller;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.Charsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Controller for search teacher.
 */
@WebServlet(urlPatterns = "/teacherSearch")
public class TeacherSearch extends HttpServlet {
    UsersDao usersDao=new UsersDao();
    private UserService userService = new UserService(usersDao);
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        var usersList = userService.searchTeacher(text);

        String output = objectMapper.writeValueAsString(usersList);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.ISO_8859_1.name());
        out.print(output);
        out.flush();
    }
}
