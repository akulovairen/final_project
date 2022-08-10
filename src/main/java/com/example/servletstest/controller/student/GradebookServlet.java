package com.example.servletstest.controller.student;

import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.model.Gradebook;
import com.example.servletstest.service.GradebookService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller for gradebook for student.
 */
@WebServlet(value = "/gradebookStudent")
public class GradebookServlet extends HttpServlet {
    private final GradebookService gradebookService=new GradebookService(new GradebookDao());

    /**
     * Gets gradebook by user id.
     *
     * @param request - request object with user id
     * @param response - response object
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortingColumn = request.getParameter("sortingColumn");
        sortingColumn = sortingColumn == null ? "c.name" : sortingColumn;
        String sortingMode = request.getParameter("sortingMode");
        sortingMode = sortingMode == null ? "ASC" : sortingMode;

        request.setAttribute("sortingColumn", sortingColumn);
        request.setAttribute("sortingMode", sortingMode);

        int student_id = (int) request.getSession().getAttribute("user_id");
        List<Gradebook> allByStudent = gradebookService.findAllGradesByStudent(student_id,sortingColumn,sortingMode);

        request.setAttribute("allByStudent",allByStudent);
        request.getRequestDispatcher("/WEB-INF/jsp/student/gradebookStudent.jsp").forward(request,response);
    }
}
