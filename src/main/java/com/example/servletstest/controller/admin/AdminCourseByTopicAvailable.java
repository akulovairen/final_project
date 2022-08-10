package com.example.servletstest.controller.admin;

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
 * Controller for course with status available by topic for admin.
 */
@WebServlet(value = "/adminCourseByTopicAvailable")
public class AdminCourseByTopicAvailable extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets courses with status available by topic.
     *
     * @param req - request object with topic id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageParam = req.getParameter("currentPage");
        int currentPage = currentPageParam == null ? 1 : Integer.parseInt(currentPageParam);
        String recordsPerPageParam = req.getParameter("recordsPerPage");
        int recordsPerPage = recordsPerPageParam == null ? 10 : Integer.parseInt(recordsPerPageParam);
        int offset = currentPage * recordsPerPage - recordsPerPage;

        String sortingColumn = req.getParameter("sortingColumn");
        sortingColumn = sortingColumn == null ? "c.name" : sortingColumn;
        String sortingMode = req.getParameter("sortingMode");
        sortingMode = sortingMode == null ? "ASC" : sortingMode;
        int topicId = Integer.parseInt(req.getParameter("topic_id"));
        req.setAttribute("topic_id", topicId);

        int rows = courseService.getNumberOfRowsAdminCourseTopicStatus(CourseDao.SQLTask.GET_COURSE_BY_ADMIN_STATUS_AND_TOPIC_COUNT.getQUERY(), topicId, "available");

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
        req.setAttribute("allTopic", allTopic);

        List<Course> coursesByTopicAndAvailable = courseService.findCoursesByTopicAndStatus(topicId,"available", recordsPerPage, offset, sortingColumn,sortingMode);
        req.setAttribute("courseTopicAvailable", coursesByTopicAndAvailable);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminCourseByTopicAvailable.jsp").forward(req, resp);
    }
}
