package com.example.servletstest.util;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class ContextListener implements ServletRequestListener {

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        String servletPath = servletRequest.getServletPath();
        String servletMethod = servletRequest.getMethod();

        sre.getServletContext().log(String.format("Request '%s %s' Destroyed", servletMethod, servletPath));
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest servletRequest = (HttpServletRequest) sre.getServletRequest();
        String servletPath = servletRequest.getServletPath();
        String servletMethod = servletRequest.getMethod();

        sre.getServletContext().log(String.format("Request '%s %s' Initialized", servletMethod, servletPath));
    }
}
