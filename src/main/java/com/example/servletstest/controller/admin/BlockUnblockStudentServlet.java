package com.example.servletstest.controller.admin;

import com.example.servletstest.dao.UsersDao;
import com.example.servletstest.model.User;
import com.example.servletstest.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Controller for block/unblock students for admin.
 */
@WebServlet(name = "BlockUnblockStudent", value = "/blockUnblockStudent")
public class BlockUnblockStudentServlet extends HttpServlet {
    UsersDao usersDao=new UsersDao();
    UserService usersService=new UserService(usersDao);

    /**
     * Gets all users with pagination.
     *
     * @param req - request object with status available
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String currentPageParam = req.getParameter("currentPage");
        int currentPage = currentPageParam == null ? 1 : Integer.parseInt(currentPageParam);
        String recordsPerPageParam = req.getParameter("recordsPerPage");
        int recordsPerPage = recordsPerPageParam == null ? 10 : Integer.parseInt(recordsPerPageParam);
        int offset = currentPage * recordsPerPage - recordsPerPage;

        int rows = usersService.getNumberOfRows(UsersDao.SQLTask.GET_ALL_USERS_COUNT.getQUERY());

        int nOfPages = rows / recordsPerPage;
        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        req.setAttribute("noOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);

        List<User> allUsers = usersService.findAllUsers(offset,recordsPerPage);
        req.setAttribute("allUsers",allUsers);

        req.getRequestDispatcher("/WEB-INF/jsp/admin/blockUnblockStudent.jsp").forward(req, resp);

    }

    /**
     * Update user by locked.
     *
     * @param req - request object with user id, lock
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("user_id"));
        boolean lock = Boolean.parseBoolean(req.getParameter("lock"));

        if(lock){
            boolean userBlock = usersService.userBlock(userId);
        }else {
            boolean userUnblock = usersService.userUnblock(userId);
        }
        resp.sendRedirect("/blockUnblockStudent");
    }
}
