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
import java.util.Map;

/**
 * Controller for all courses by status available for admin.
 */
@WebServlet( value = "/adminCoursesAvailableList")
public class AvailableListCourseForAdminServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets all courses by status progress.
     *
     * @param req - request object with status available
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

        int rows = courseService.getNumberOfRows(CourseDao.SQLTask.GET_COURSE_BY_ADMIN_STATUS_COUNT.getQUERY(), "available");

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

//        int topicId = Integer.parseInt(req.getParameter("topic_id"));

//        List<Course> coursesByTopicAndAvailable = courseService.findCoursesByTopicAndStatus(topicId,"available");
//        req.setAttribute("courseTopic", coursesByTopicAndAvailable);

        List<Course> availableStatus = courseService.findCourseStatus("available" ,recordsPerPage, offset, sortingColumn, sortingMode);
        req.setAttribute("available", availableStatus);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminCoursesAvailableList.jsp").forward(req, resp);

    }
}
