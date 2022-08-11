package com.example.servletstest.exception;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(value = "/error-handler")
public class ExceptionHandlerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Exception exception = (Exception) req.getAttribute("javax.servlet.error.exception");

        Integer statusCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        if (exception instanceof PermissionDeniedException) {
            statusCode = 403;
        }
        String servletName = (String) req.getAttribute("javax.servlet.error.servlet_name");
        String requestUri = (String) req.getAttribute("javax.servlet.error.request_uri");

        String message = exception.getMessage();
        req.setAttribute("errorMessage", message);
        req.setAttribute("errorStatus", statusCode);
        req.setAttribute("servletName", servletName);
        req.setAttribute("requestUri", requestUri);
//        req.setAttribute("stackTrace", stringBuilder.toString());

        req.getRequestDispatcher("/WEB-INF/jsp/errorPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
