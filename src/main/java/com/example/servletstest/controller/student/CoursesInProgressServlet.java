package com.example.servletstest.controller.student;

import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.User;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.GradebookService;
import com.example.servletstest.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for courses with status progress for student.
 */
@WebServlet(value = "/coursesInProgress")
public class CoursesInProgressServlet extends HttpServlet {
    private final CourseService courseService = new CourseService(new CourseDao());
    private final UsersDao usersDao=new UsersDao();
    private final UserService usersService=new UserService(usersDao);
    private final GradebookService gradebookService = new GradebookService(new GradebookDao());

    /**
     * Gets courses with status progress by user id.
     *
     * @param request - request object
     * @param response - response object
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("user_id");
        List<Course> progress = courseService.findByUserIdAndStatus(userId, "progress");
        request.setAttribute("progress", progress);

        Optional<User> userById = usersService.getUserById(userId);
        userById.ifPresent(user ->request.setAttribute("userById", userById));

        request.getRequestDispatcher("/WEB-INF/jsp/student/coursesInProgress.jsp").forward(request, response);
    }

}
