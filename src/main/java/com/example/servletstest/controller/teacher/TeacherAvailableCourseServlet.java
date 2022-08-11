package com.example.servletstest.controller.teacher;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dto.CourseDto;
import com.example.servletstest.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for courses with status available for teacher.
 */
@WebServlet(value = "/teacherAvailableCourse")
public class TeacherAvailableCourseServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());

    /**
     * Gets courses with status available by user id.
     *
     * @param req - request object with user id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageParam = req.getParameter("currentPage");
        int currentPage = currentPageParam == null ? 1 : Integer.parseInt(currentPageParam);
        String recordsPerPageParam = req.getParameter("recordsPerPage");
        int recordsPerPage = recordsPerPageParam == null ? 10 : Integer.parseInt(recordsPerPageParam);
        int offset = currentPage * recordsPerPage - recordsPerPage;

        int teacherId = (int) req.getSession().getAttribute("user_id");
        int rows = courseService.getNumberOfRowsTeacherStatus(CourseDao.SQLTask.GET_COURSE_BY_TEACHER_AND_STATUS_COUNT.getQUERY(), teacherId,"finished");

        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        List<CourseDto> courseByTeacher = courseService.findCourseByTeacher(teacherId, "available");
        req.setAttribute("courseAvailable", courseByTeacher);

        req.getRequestDispatcher("/WEB-INF/jsp/teacher/teacherAvailableCourse.jsp").forward(req, resp);
    }
}
