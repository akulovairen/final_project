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
 * Controller for courses with status finished for teacher.
 */
@WebServlet(value = "/teacherCompleted")
public class TeacherCompletedCourseServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());

    /**
     * Gets courses with status finished by user id.
     *
     * @param req - request object with user id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int teacherId = (int) req.getSession().getAttribute("user_id");
        int rows = courseService.getNumberOfRowsTeacherStatus(CourseDao.SQLTask.GET_COURSE_BY_TEACHER_AND_STATUS_COUNT.getQUERY(), teacherId,"finished");

        List<CourseDto> courseByTeacher = courseService.findCourseByTeacher(teacherId, "finished");
        req.setAttribute("courseFinished", courseByTeacher);

        req.getRequestDispatcher("/WEB-INF/jsp/teacher/teacherCompleted.jsp").forward(req, resp);
    }
}
