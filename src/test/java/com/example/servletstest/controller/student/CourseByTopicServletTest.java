package com.example.servletstest.controller.student;

import com.example.servletstest.controller.admin.AdminCourseByTopicProgress;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseByTopicServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private CourseByTopicServlet testingServlet;

    @BeforeEach
    public void setUp() {
        final ServletContext servletContext = mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        testingServlet = new CourseByTopicServlet() {
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };
    }

    @Test
    void doGet() {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user_id")).thenReturn(1);
        when(request.getParameter("topic_id")).thenReturn("1");

        boolean success;
        try {
            testingServlet.doGet(request, response);
            success = true;
        } catch (Exception e) {
            success = false;
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(success);
    }
}