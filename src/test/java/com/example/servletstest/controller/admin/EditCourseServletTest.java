package com.example.servletstest.controller.admin;

import com.example.servletstest.service.CourseService;
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

import java.io.PrintWriter;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EditCourseServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private CourseService courseService;
    private EditCourseServlet testingServlet;

    @BeforeEach
    public void setUp() {
        final ServletContext servletContext = mock(ServletContext.class);
        testingServlet = new EditCourseServlet() {
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
            when(request.getParameter("course_id")).thenReturn("1");
            when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
            testingServlet.doGet(request, response);
            success = true;
        } catch (Exception e) {
            success = false;
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(success);
    }

    @Test
    public void testPost() {
        try {
            when(request.getParameter("id")).thenReturn("1");
            when(request.getParameter("name")).thenReturn("Course 1");
            when(request.getParameter("description")).thenReturn("Course 1 description");
            when(request.getParameter("date_start")).thenReturn(LocalDate.now().plusDays(1L).toString());
            when(request.getParameter("duration")).thenReturn("5");
            when(request.getParameter("topicList")).thenReturn("1");
            when(request.getParameter("teacher_id")).thenReturn("1");
            testingServlet.doPost(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
