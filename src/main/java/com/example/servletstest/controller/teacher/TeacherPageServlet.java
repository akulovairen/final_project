package com.example.servletstest.controller.teacher;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.dto.CourseDto;
import com.example.servletstest.model.Course;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.CourseUserService;
import com.example.servletstest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Controller for courses with status progress for teacher.
 */
@WebServlet(value = "/teacherPage")
public class TeacherPageServlet extends HttpServlet {
    private final CourseService courseService = new CourseService(new CourseDao());
    private UsersDao usersDao=new UsersDao();

    /**
     * Gets courses with status progress by user id.
     *
     * @param request - request object with user id
     * @param response - response object
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int teacherId = (int) request.getSession().getAttribute("user_id");

        List<CourseDto> courseTeacher = courseService.findCourseByTeacher(teacherId,"progress");
        request.setAttribute("courseTeacher",courseTeacher);

        request.getRequestDispatcher("/WEB-INF/jsp/teacher/teacherPage.jsp").forward(request, response);
    }
}
