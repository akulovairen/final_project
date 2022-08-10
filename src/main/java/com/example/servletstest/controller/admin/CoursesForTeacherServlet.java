package com.example.servletstest.controller.admin;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.model.Course;
import com.example.servletstest.service.CourseService;
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
 * Controller for courses by teacher.
 */
@WebServlet(value = "/adminCoursesForTeacher")
public class CoursesForTeacherServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private Logger log = LogManager.getLogger(this);

    /**
     * Gets all courses for teacher.
     *
     * @param req - request object with user id, course status
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Executing the path /adminCoursesForTeacher");

        int teacherId = Integer.parseInt(req.getParameter("user_id"));
        String status = req.getParameter("status");
        req.setAttribute("user_id",teacherId);

        String currentPageParam = req.getParameter("currentPage");
        int currentPage = currentPageParam == null ? 1 : Integer.parseInt(currentPageParam);
        String recordsPerPageParam = req.getParameter("recordsPerPage");
        int recordsPerPage = recordsPerPageParam == null ? 10 : Integer.parseInt(recordsPerPageParam);
        int offset = currentPage * recordsPerPage - recordsPerPage;

        int rows = courseService.getNumberOfRowsTeacher(CourseDao.SQLTask.GET_COURSES_BY_TEACHER_COUNT.getQUERY(), teacherId);

        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        String sortingColumn = req.getParameter("sortingColumn");
        sortingColumn = sortingColumn == null ? "c.name" : sortingColumn;
        String sortingMode = req.getParameter("sortingMode");
        sortingMode = sortingMode == null ? "ASC" : sortingMode;

        req.setAttribute("sortingColumn", sortingColumn);
        req.setAttribute("sortingMode", sortingMode);

        List<Course> courseTeacher = courseService.findCourseTeacher(teacherId, recordsPerPage, offset, sortingColumn,sortingMode);
        req.setAttribute("courseTeacher",courseTeacher);

//        List<CourseDto> progress = courseService.findCourseByTeacher(teacherId, "progress",recordsPerPage,offset);
//        req.setAttribute("progress", progress);
//
//        List<CourseDto> available = courseService.findCourseByTeacher(teacherId, "available",recordsPerPage,offset);
//        req.setAttribute("available", available);
//
//        List<CourseDto> finished = courseService.findCourseByTeacher(teacherId, "finished",recordsPerPage,offset);
//        req.setAttribute("finished", finished);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminCoursesForTeacher.jsp").forward(req, resp);

    }
}
