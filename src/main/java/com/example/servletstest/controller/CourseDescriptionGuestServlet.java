package com.example.servletstest.controller;

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
import java.util.Optional;

/**
 * Controller for course description for guests.
 */
@WebServlet(value = "/courseDescriptionGuest")
public class CourseDescriptionGuestServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets course description by course id.
     *
     * @param req - request object with course id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("course_id"));
        Optional<Course> course = courseService.findCourse(courseId);
        course.ifPresent(course1 -> req.setAttribute("course", course1));
        List<Topic> allTopic = topicService.getAllTopic();

        req.setAttribute("allTopic", allTopic);

        req.getRequestDispatcher("/WEB-INF/jsp/courseDescriptionGuest.jsp").forward(req, resp);
    }
}
