package com.example.servletstest.controller.admin;

import com.example.servletstest.dao.TopicDao;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.model.Topic;
import com.example.servletstest.service.TopicService;
import com.example.servletstest.util.RequestUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Controller for create topic.
 */
@WebServlet(value = "/createTopic")
public class CreateTopicServet extends HttpServlet {
    private final TopicService topicService=new TopicService(new TopicDao());

    /**
     * Gets all topic.
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Topic> allTopic = topicService.getAllTopic();

        req.setAttribute("allTopic",allTopic);

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

        req.getRequestDispatcher("/WEB-INF/jsp/admin/createTopic.jsp").forward(req, resp);

    }

    /**
     * Create topic.
     *
     * @param req - request object with course name
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        Locale locale = (Locale) req.getSession().getAttribute("userLocale");
        try {
            topicService.createTopic(name,locale);
            resp.sendRedirect("/createTopic");
        } catch (CustomValidationException e) {
            req.getSession().setAttribute("messagesMap", e.getErrorsMap());
            RequestUtils.restoreFormValues(req);
            resp.sendRedirect("/createTopic");
//            req.getRequestDispatcher("/WEB-INF/jsp/admin/createTopic.jsp").forward(req, resp);
        }

    }
}
