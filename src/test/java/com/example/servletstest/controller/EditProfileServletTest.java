package com.example.servletstest.controller;

import com.example.servletstest.controller.teacher.TeacherAvailableCourseServlet;
import com.example.servletstest.util.LocalizedValidatorUtil;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditProfileServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private EditProfileServlet testingServlet;

    @BeforeEach
    public void setUp() {
        final ServletContext servletContext = mock(ServletContext.class);
        testingServlet = new EditProfileServlet() {
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }


    @Test
    void testGet() {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher(anyString())).thenReturn(dispatcher);
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user_id")).thenReturn(1);

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

    @Test
    void testPost() {
        try {
            HttpSession session = mock(HttpSession.class);
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute("user_id")).thenReturn(1);
            when(request.getParameter("name")).thenReturn("ivan");
            when(request.getParameter("surname")).thenReturn("Fariot");
            when(request.getParameter("email")).thenReturn("ivan@gmail.com");
            when(request.getParameter("birthday")).thenReturn(LocalDate.now().minusDays(1L).toString());
            when(session.getAttribute("userLocale")).thenReturn(LocalizedValidatorUtil.UKRAINE_LOCALE);
            testingServlet.doPost(request, response);
//            courseService.updateCourse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}