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

/**
 * Controller for courses by topic for student.
 */
@WebServlet(value = "/courseByTopic")
public class CourseByTopicServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets courses by topic.
     *
     * @param req - request object with user id, topic id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("user_id");
        int topicId = Integer.parseInt(req.getParameter("topic_id"));

        List<Topic> allTopic = topicService.getAllTopic();
        List<Course> available = courseService.findByStatusAvailableByTopic(topicId,userId);

        req.setAttribute("allTopic", allTopic);
        req.setAttribute("available", available);

        req.getRequestDispatcher("/WEB-INF/jsp/student/courseByTopic.jsp").forward(req, resp);
    }

}
