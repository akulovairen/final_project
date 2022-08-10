package com.example.servletstest.controller.admin;
import com.example.servletstest.dao.CourseDao;
import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Course;
import com.example.servletstest.model.Topic;
import com.example.servletstest.model.User;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.TopicService;
import com.example.servletstest.service.UserService;
import com.example.servletstest.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 * Controller for edit course.
 */
@WebServlet(value = "/editCourse")
public class EditCourseServlet extends HttpServlet {
    private final CourseService courseService=new CourseService(new CourseDao());
    private final UsersDao usersDao=new UsersDao();
    private final UserService usersService=new UserService(usersDao);
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets course by course id.
     *
     * @param req - request object with course id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int courseId = Integer.parseInt(req.getParameter("course_id"));
        Optional<Course> course = courseService.findCourse(courseId);
        course.ifPresent(course1 -> req.setAttribute("course",course1));

        List<User> allTeacher = usersService.findAllTeacher(1, 20);
        req.setAttribute("allTeacher", allTeacher);

        List<Topic> allTopic = topicService.getAllTopic();
        req.setAttribute("allTopic", allTopic);

        Map<String, String> errorMap = (Map<String, String>) req.getSession().getAttribute("messagesMap");
        if(errorMap!=null && !errorMap.isEmpty()){
            req.setAttribute("messagesMap",errorMap);
        }
        Map<String, String> restoredValues = (Map<String, String>) req.getSession().getAttribute("restoredValues");
        if(restoredValues!=null && !restoredValues.isEmpty()){
            req.setAttribute("restoredValues",restoredValues);
        }
        req.getSession().removeAttribute("messagesMap");
        req.getSession().removeAttribute("restoredValues");
        req.getRequestDispatcher("/WEB-INF/jsp/admin/editCourse.jsp").forward(req, resp);

    }

    /**
     * Update course by course id.
     *
     * @param req - request object with course id, name, date start, duration, description, topic list, teacher id
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        LocalDate dateStart= LocalDate.parse(req.getParameter("date_start"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        String description = req.getParameter("description");
        int topicId = Integer.parseInt(req.getParameter("topicList"));
        int teacherId = Integer.parseInt(req.getParameter("teacher_id"));

        Locale locale = (Locale) req.getSession().getAttribute("userLocale");
        try {
            courseService.updateCourse(id,name,dateStart,duration, description,topicId,teacherId,locale);
            resp.sendRedirect("/adminCoursesList");
        } catch (CustomValidationException e) {
            req.getSession().setAttribute("messagesMap", e.getErrorsMap());
            RequestUtils.restoreFormValues(req);
            resp.sendRedirect("/editCourse?course_id=" + id);
        }
    }
}
