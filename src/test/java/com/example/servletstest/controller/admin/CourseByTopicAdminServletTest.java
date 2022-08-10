package com.example.servletstest.controller.admin;

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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseByTopicAdminServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private CourseByTopicAdminServlet testingServlet;

    @BeforeEach
    public void setUp() {
        final ServletContext servletContext = mock(ServletContext.class);
        testingServlet = new CourseByTopicAdminServlet() {
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };
    }

    @Test
    public void testGet() {
        boolean success;
        try {
            RequestDispatcher dispatcher = mock(RequestDispatcher.class);
            when(request.getParameter("topic_id")).thenReturn("1");
            when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
            testingServlet.doGet(request, response);
            success = true;
        } catch (Exception e) {
            success = false;
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(success);
    }
}
