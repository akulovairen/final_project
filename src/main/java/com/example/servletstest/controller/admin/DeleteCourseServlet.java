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
 * Controller for delete course.
 */
@WebServlet(value = "/deleteCourse")
public class DeleteCourseServlet extends HttpServlet {
    CourseDao courseDao=new CourseDao();
    CourseService courseService=new CourseService(courseDao);

    /**
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin/editCourse.jsp").forward(req, resp);
    }

    /**
     * Delete course by course id.
     *
     * @param req - request object with course id
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int courseId = Integer.parseInt(req.getParameter("course_id"));
        boolean deleteCourse = courseService.deleteCourse(courseId);

        resp.sendRedirect("/adminCoursesList");
    }
}
