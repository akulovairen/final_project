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
import javax.servlet.http.HttpSession;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RegistrationTeacherServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    private RegistrationTeacherServlet testingServlet;

    @BeforeEach
    public void setUp() {
        final ServletContext servletContext = mock(ServletContext.class);
        testingServlet = new RegistrationTeacherServlet() {
            public ServletContext getServletContext() {
                return servletContext; // return the mock
            }
        };
        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void testGet() {
        boolean success;
        try {
            RequestDispatcher dispatcher = mock(RequestDispatcher.class);
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
        boolean success;
        try {
            when(request.getParameter("password")).thenReturn("1234");
            when(request.getParameter("password_repeat")).thenReturn("1234");
            when(request.getParameter("name")).thenReturn("Oleg");
            when(request.getParameter("email")).thenReturn("oleg@gmail.com");
            when(request.getParameter("surname")).thenReturn("Doe");
            when(request.getParameter("birthday")).thenReturn("2022-05-06");
            testingServlet.doPost(request, response);
            success = true;
        } catch (Exception e) {
            success = false;
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(success);
    }
}
