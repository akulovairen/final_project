package com.example.servletstest.controller.student;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Topic;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Controller for courses with status available for student.
 */
@WebServlet(value = "/coursesAvailable")
public class CoursesAvailableServlet extends HttpServlet {
    private final CourseDao courseDao=new CourseDao();
    private final CourseService courseService=new CourseService(courseDao);
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets courses with status available.
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("user_id");
        List<Topic> allTopic = topicService.getAllTopic();
        req.setAttribute("allTopic", allTopic);

        List<Course> available = courseService.findByStatusAvailable(userId);
        req.setAttribute("available", available);

        req.getRequestDispatcher("/WEB-INF/jsp/student/coursesAvailable.jsp").forward(req, resp);
    }
}
