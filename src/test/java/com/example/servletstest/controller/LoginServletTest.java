package com.example.servletstest.controller;

import com.example.servletstest.controller.admin.EditCourseServlet;
import com.example.servletstest.service.CourseService;
import com.example.servletstest.util.LocalizedValidatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class LoginServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private LoginServlet testingServlet;

    @BeforeEach
    public void setUp() {
        final ServletContext servletContext = mock(ServletContext.class);
        testingServlet = new LoginServlet() {
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userLocale")).thenReturn(LocalizedValidatorUtil.UKRAINE_LOCALE);
    }

    @Test
    void testPost() {
        try {
            when(request.getParameter("email")).thenReturn("ib@gmail.com");
            when(request.getParameter("password")).thenReturn("123");

            testingServlet.doPost(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}