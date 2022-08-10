package com.example.servletstest.controller.student;

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
import java.util.Optional;

/**
 * Controller for course description for student.
 */
@WebServlet(value = "/coursesDescription")
public class CourseDescriptionServlet extends HttpServlet {

    private final CourseService courseService = new CourseService(new CourseDao());
    private final GradebookService gradebookService = new GradebookService(new GradebookDao());
    private final CourseUserService courseUserService = new CourseUserService(new CourseUserDao());

    /**
     * Gets course by course id and user id. Gets gradebook by course id and user id.
     *
     * @param req - request object with course id, user id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("course_id"));
        Optional<Course> course = courseService.findCourse(courseId);
        course.ifPresent(course1 -> req.setAttribute("course", course1));

        int userId = (int) req.getSession().getAttribute("user_id");
        String fullName = req.getSession().getAttribute("userName") + " " + req.getSession().getAttribute("userSurname");
        req.setAttribute("userFullName", fullName);
        Optional<Gradebook> gradeByUserAndCourse = gradebookService.findGradeByUserAndCourse(userId, courseId);
        gradeByUserAndCourse.ifPresent(gradebook -> req.setAttribute("gradeByUserAndCourse",gradebook));

        req.getRequestDispatcher("/WEB-INF/jsp/student/coursesDescription.jsp").forward(req, resp);
    }

    /**
     * Create grsdebook for student by course id. Create connection between course and student.
     *
     * @param req - request object with course id, user id
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int studentId = (int) req.getSession().getAttribute("user_id");
        int courseId = Integer.parseInt(req.getParameter("courseId"));

        gradebookService.createGradebook(studentId, courseId);
        courseUserService.createCourseUser(courseId, studentId, "register");

//        resp.sendRedirect("/coursesDescription?course_id="+courseId);
        resp.sendRedirect("/courseRegister");
    }
}
