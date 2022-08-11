package com.example.servletstest.controller.teacher;

import com.example.servletstest.dao.GradebookDao;
import com.example.servletstest.dto.GradebookDto;
import com.example.servletstest.exception.CustomValidationException;
import com.example.servletstest.exception.serviceException.gradebook.GradebookParseException;
import com.example.servletstest.model.Gradebook;
import com.example.servletstest.service.GradebookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Controller for gradebook by course for teacher.
 */
@WebServlet(value = "/teacherGradebookByCourse")
public class TeacherGradebookByCourseServlet extends HttpServlet {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static Logger log = LogManager.getLogger(TeacherGradebookByCourseServlet.class);
    private final GradebookService gradebookService=new GradebookService(new GradebookDao());

    /**
     * Gets gradebook by course id.
     *
     * @param req - request object with course id
     * @param resp - response object
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int courseId = Integer.parseInt(req.getParameter("course_id"));

        List<Gradebook> allGradesByCourse = gradebookService.findAllGradesByCourse(courseId);

        req.setAttribute("courseGradebook", allGradesByCourse);

        Map<String, String> errorMap = (Map<String, String>) req.getSession().getAttribute("messagesMap");
        if(errorMap!=null && !errorMap.isEmpty()){
            req.setAttribute("messagesMap",errorMap);
        }
        req.getSession().removeAttribute("messagesMap");

        req.getRequestDispatcher("/WEB-INF/jsp/teacher/teacherGradebookByCourse.jsp").forward(req, resp);
    }

    /**
     * Updates grades in gradebook by course id.
     *
     * @param req - request object
     * @param resp - response object
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = convertRequestAsJsonString(req);
        GradebookDto gradebookDto = objectMapper.readValue(body, GradebookDto.class);

        Locale locale = (Locale) req.getSession().getAttribute("userLocale");
        try {
            gradebookService.updateGradebookByCourse(gradebookDto,locale);
            resp.sendRedirect("/teacherGradebookByCourse?course_id=" + gradebookDto.getCourseId());
        } catch (CustomValidationException e) {
            resp.sendError(400);
        }
    }

    public static String convertRequestAsJsonString(HttpServletRequest request)  {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw new GradebookParseException("Cannot parse gradebook", ex);
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    log.warn("Cannot close bufferReader");
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }
}
