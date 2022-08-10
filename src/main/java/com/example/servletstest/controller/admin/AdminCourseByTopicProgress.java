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
 * Controller for course with status progress by topic for admin.
 */
@WebServlet(value = "/adminCourseByTopicProgress")
public class AdminCourseByTopicProgress extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets courses with status progress by topic.
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
        String status = req.getParameter("status");
//        int teacherId = (int) req.getSession().getAttribute("user_id");
        int rows = courseService.getNumberOfRowsAdminCourseTopicStatus(CourseDao.SQLTask.GET_COURSE_BY_ADMIN_STATUS_AND_TOPIC_COUNT.getQUERY(), topicId, "progress" );

        int nOfPages = rows / recordsPerPage;
//        if(nOfPages==0 && recordsPerPage>0){
//            nOfPages=1;
//        }else
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        req.setAttribute("sortingColumn", sortingColumn);
        req.setAttribute("sortingMode", sortingMode);

        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        req.setAttribute("topic_id",topicId);

        List<Topic> allTopic = topicService.getAllTopic();
        req.setAttribute("allTopic", allTopic);

//        List<Course> coursesByTopicAndAvailable = courseService.findCoursesByTopicAndStatus(topicId,"available");
//        req.setAttribute("courseTopicAvailable", coursesByTopicAndAvailable);

        List<Course> coursesByTopicAndProgress = courseService.findCoursesByTopicAndStatus(topicId,"progress",recordsPerPage, offset, sortingColumn,sortingMode);
        req.setAttribute("courseTopic", coursesByTopicAndProgress);

//        List<Course> coursesByTopicAndFinished = courseService.findCoursesByTopicAndStatus(topicId,"finished");
//        req.setAttribute("courseTopicFinished", coursesByTopicAndFinished);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminCourseByTopicProgress.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
