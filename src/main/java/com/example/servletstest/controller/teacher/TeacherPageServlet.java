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
        String currentPageParam = request.getParameter("currentPage");
        int currentPage = currentPageParam == null ? 1 : Integer.parseInt(currentPageParam);
        String recordsPerPageParam = request.getParameter("recordsPerPage");
        int recordsPerPage = recordsPerPageParam == null ? 10 : Integer.parseInt(recordsPerPageParam);
        int offset = currentPage * recordsPerPage - recordsPerPage;

        String sortingColumn = request.getParameter("sortingColumn");
        sortingColumn = sortingColumn == null ? "c.name" : sortingColumn;
        String sortingMode = request.getParameter("sortingMode");
        sortingMode = sortingMode == null ? "ASC" : sortingMode;

        int teacherId = (int) request.getSession().getAttribute("user_id");
        int rows = courseService.getNumberOfRowsTeacher(CourseDao.SQLTask.GET_COURSE_BY_TEACHER_AVAILABLE_COUNT.getQUERY(), teacherId);

        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("sortingColumn", sortingColumn);
        request.setAttribute("sortingMode", sortingMode);

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);

        List<CourseDto> courseTeacher = courseService.findCourseByTeacher(teacherId,"progress",recordsPerPage,offset);
        request.setAttribute("courseTeacher",courseTeacher);


        List<Course> courseProgress = courseService.findCourseNotFinished(teacherId,recordsPerPage,offset );
        request.setAttribute("courseProgress", courseProgress);

//        int countStudent = courseUserService.countStudent(courseId);
//        request.setAttribute("countStudent",countStudent);

        request.getRequestDispatcher("/WEB-INF/jsp/teacher/teacherPage.jsp").forward(request, response);
    }
}
