package com.example.servletstest.controller.admin;

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

/**
 * Controller for courses by topic for admin.
 */
@WebServlet(value = "/courseByTopicAdmin")
public class CourseByTopicAdminServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());

    /**
     * Gets courses by topic.
     *
     * @param req - request object with topic id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int topicId = Integer.parseInt(req.getParameter("topic_id"));
        List<Course> coursesByTopicAndAvailable = courseService.findCourseByTopic(topicId);

        req.setAttribute("courseTopic", coursesByTopicAndAvailable);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/courseByTopicAdmin.jsp").forward(req, resp);
    }
}
