package com.example.servletstest.controller.admin;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for change course status.
 */
@WebServlet(value = "/changeCourseStatus")
public class ChangeCourseStatusServlet extends HttpServlet {
    private final CourseService courseService = new CourseService(new CourseDao());

    /**
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminCoursesList.jsp").forward(req, resp);
    }

    /**
     * Update course status.
     *
     * @param req - request object with course id, status
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("course_id"));
        String status = req.getParameter("status");

        courseService.updateStatusCourse(status, courseId);

        resp.sendRedirect("/adminCoursesList");
    }
}
