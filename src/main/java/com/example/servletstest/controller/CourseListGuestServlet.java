package com.example.servletstest.controller;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Topic;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.TopicService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for all available courses for guests.
 */
@WebServlet(value = "/coursesListGuest")
public class CourseListGuestServlet extends HttpServlet {
    private final CourseDao courseDao=new CourseDao();
    private final CourseService courseService=new CourseService(courseDao);
    private final TopicService topicService=new TopicService(new TopicDao());

    private Logger log = LogManager.getLogger(CourseListGuestServlet.class);

    /**
     * Gets all available courses.
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Topic> allTopic = topicService.getAllTopic();
        List<Course> forGuest = courseService.findByStatusForGuest();

        req.setAttribute("forGuest", forGuest);
        req.setAttribute("allTopic", allTopic);

        req.getRequestDispatcher("/WEB-INF/jsp/coursesListGuest.jsp").forward(req, resp);
    }
}
