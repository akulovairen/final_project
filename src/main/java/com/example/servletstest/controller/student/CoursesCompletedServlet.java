package com.example.servletstest.controller.student;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.model.Course;
import com.example.servletstest.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Controller for courses with status finished for student.
 */
@WebServlet(value = "/coursesCompleted")
public class CoursesCompletedServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());

    /**
     * Gets courses with status finished by user id.
     *
     * @param req - request object with user id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("user_id");

        List<Course> finished = courseService.findByUserIdAndStatus(userId, "finished");
        req.setAttribute("finished", finished);

        req.getRequestDispatcher("/WEB-INF/jsp/student/coursesCompleted.jsp").forward(req, resp);
    }

}
