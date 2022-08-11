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

/**
 * Controller for courses by topic for guests.
 */
@WebServlet(value = "/courseByTopicGuest")
public class CourseByTopicGuestServlet extends HttpServlet {
    private final TopicService topicService=new TopicService(new TopicDao());
    private final CourseDao courseDao=new CourseDao();
    private final CourseService courseService=new CourseService(courseDao);

    /**
     * Gets courses by topic
     *
     * @param req - request object with topic id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int topicId = Integer.parseInt(req.getParameter("topic_id"));

        String currentPageParam = req.getParameter("currentPage");
        int currentPage = currentPageParam == null ? 1 : Integer.parseInt(currentPageParam);
        String recordsPerPageParam = req.getParameter("recordsPerPage");
        int recordsPerPage = recordsPerPageParam == null ? 10 : Integer.parseInt(recordsPerPageParam);
        int offset = currentPage * recordsPerPage - recordsPerPage;

        String sortingColumn = req.getParameter("sortingColumn");
        sortingColumn = sortingColumn == null ? "c.name" : sortingColumn;
        String sortingMode = req.getParameter("sortingMode");
        sortingMode = sortingMode == null ? "ASC" : sortingMode;

        int rows = courseService.getNumberOfRowsAdminCourseTopicStatus(CourseDao.SQLTask.GET_COURSE_BY_ADMIN_STATUS_AND_TOPIC_COUNT.getQUERY(),topicId, "progress" );
//
        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        req.setAttribute("sortingColumn", sortingColumn);
        req.setAttribute("sortingMode", sortingMode);

        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        List<Topic> allTopic = topicService.getAllTopic();

        List<Course> coursesByTopicAndAvailable = courseService.findCoursesByTopicAndStatus(topicId,"available", recordsPerPage, offset, sortingColumn,sortingMode);

        req.setAttribute("allTopic", allTopic);
        req.setAttribute("courseByTopic", coursesByTopicAndAvailable);

        req.getRequestDispatcher("/WEB-INF/jsp/courseByTopicGuest.jsp").forward(req, resp);
    }

}
