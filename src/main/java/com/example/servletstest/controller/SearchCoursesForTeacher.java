package com.example.servletstest.controller;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Controller for search courses for teacher.
 */
@WebServlet(urlPatterns = "/searchCoursesTeacher")
public class SearchCoursesForTeacher extends HttpServlet {
    private final CourseService courseService = new CourseService(new CourseDao());
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Gets course by text.
     *
     * @param req - request object with text
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("text");
        var courseList = courseService.searchCoursesProgressTeacher(text);

        String output = objectMapper.writeValueAsString(courseList);

        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding(StandardCharsets.ISO_8859_1.name());
        out.print(output);
        out.flush();
    }
}
