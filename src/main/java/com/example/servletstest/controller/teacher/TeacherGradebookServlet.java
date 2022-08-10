package com.example.servletstest.controller.teacher;

import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.model.Gradebook;
import com.example.servletstest.service.GradebookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "TeacherGradebookServlet", value = "/teacherGradebook")
public class TeacherGradebookServlet extends HttpServlet {
    private final GradebookService gradebookService=new GradebookService(new GradebookDao());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int gradebookId = (int) req.getSession().getAttribute("gradebook_id");
        Map<String, List<Gradebook>> gradebookForTeacher = gradebookService.findGradebookForTeacher(gradebookId);
        req.setAttribute("gradebookTeacher", gradebookForTeacher);

        req.getRequestDispatcher("/WEB-INF/jsp/teacherGradebook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/HTML");


        int test1 = Integer.parseInt(req.getParameter("test1"));
        int test2= Integer.parseInt(req.getParameter("test2"));
        int test3 = Integer.parseInt(req.getParameter("test3"));
        int test4 = Integer.parseInt(req.getParameter("test4"));
        int id = Integer.parseInt(req.getParameter("id"));

        Gradebook gradebook = new Gradebook.GradebookBuilder()
                .withId(id)
                .withTest1(test1)
                .withTest2(test2)
                .withTest3(test3)
                .withTest4(test4).build();

//        Gradebook gradebook = new Gradebook();

//        gradebook.setId(id);
//        gradebook.setTest1(test1);
//        gradebook.setTest2(test2);
//        gradebook.setTest3(test3);
//        gradebook.setTest4(test4);

        gradebookService.updateGradebook(gradebook);
        resp.sendRedirect("/teacherGradebook");
    }

}
