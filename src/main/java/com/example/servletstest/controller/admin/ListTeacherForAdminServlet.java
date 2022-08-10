package com.example.servletstest.controller.admin;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.model.User;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for list teachers for admin and delete teacher.
 */
@WebServlet( value = "/adminTeacherList")
public class ListTeacherForAdminServlet extends HttpServlet {
    UsersDao usersDao=new UsersDao();
    UserService usersService=new UserService(usersDao);

    /**
     * Gets all teachers.
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageParam = req.getParameter("currentPage");
        int currentPage = currentPageParam == null ? 1 : Integer.parseInt(currentPageParam);
        String recordsPerPageParam = req.getParameter("recordsPerPage");
        int recordsPerPage = recordsPerPageParam == null ? 10 : Integer.parseInt(recordsPerPageParam);
        int offset = currentPage * recordsPerPage - recordsPerPage;

        int rows = usersService.getNumberOfRows(UsersDao.SQLTask.GET_ALL_TEACHER_COUNT.getQUERY());

        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        List<User> allTeacher = usersService.findAllTeacher(offset, recordsPerPage);
        req.setAttribute("allTeacher",allTeacher);

        //TODO: finish pagination in JSP

        req.getRequestDispatcher("/WEB-INF/jsp/admin/adminTeacherList.jsp").forward(req, resp);
    }

    /**
     * Delete teacher by user id.
     *
     * @param req - request object with user id
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("user_id"));
        usersService.deleteUser(userId);
        resp.sendRedirect("/adminTeacherList");
    }
}
