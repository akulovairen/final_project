package com.example.servletstest.controller.admin;

import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.service.TopicService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for delete topic.
 */
@WebServlet(name = "DeleteTopicServlet", value = "/deleteTopic")
public class DeleteTopicServlet extends HttpServlet {
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/admin/createTopic.jsp").forward(req, resp);
    }

    /**
     * Delete topic by topic id.
     *
     * @param req - request object with topic id
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("topic_id"));
        topicService.deleteTopic(id);

        resp.sendRedirect("/createTopic");
    }
}
