package com.example.servletstest.controller.teacher;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.CourseUserDao;
import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Gradebook;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.CourseUserService;
import com.example.servletstest.service.GradebookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Controller for course description for teacher.
 */
@WebServlet(value = "/courseDescriptionTeacher")
public class CourseDescriptionTeacherServlet  extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private final GradebookService gradebookService=new GradebookService(new GradebookDao());
    private final CourseUserService courseUserService=new CourseUserService(new CourseUserDao());

    /**
     * Gets course description by course id. Gets gradebook by course id.
     *
     * @param req - request object with course id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("course_id"));
        Optional<Course> course = courseService.findCourse(courseId);
        course.ifPresent(course1 -> req.setAttribute("course", course1));

        List<Gradebook> allGradesByCourse = gradebookService.findAllGradesByCourse(courseId);
        int countStudent = courseUserService.countStudent(courseId);

        req.setAttribute("countStudent", countStudent);
        req.setAttribute("courseGradebook", allGradesByCourse);

        req.getRequestDispatcher("/WEB-INF/jsp/teacher/courseDescriptionTeacher.jsp").forward(req, resp);
    }
}
