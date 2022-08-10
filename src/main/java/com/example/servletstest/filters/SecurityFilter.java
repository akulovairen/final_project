package com.example.servletstest.filters;


import com.example.servletstest.exception.PermissionDeniedException;
import com.example.servletstest.model.RoleEnum;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Security filter that grants access to roles.
 */
@WebFilter(filterName = "securityFilter", urlPatterns = "/*")
public class SecurityFilter implements Filter {

    private Logger log = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String path = req.getServletPath();
        if (path.contains("?")) {
            path = path.substring(0, path.indexOf("?"));
        }
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");

        if (role == null) {
            if (path.contains("/courseDescriptionGuest")
                    || path.contains("/coursesListGuest")
                    || path.equals("/login")
                    || path.equals("/registration")
                    || path.equals("/courseByTopicGuest")
                    || path.equals("/locale")
                    || path.equals("/images")
                    || path.equals("/")

            ) {
                chain.doFilter(req, res);
                return;
            }
            res.sendRedirect("/login");
            return;
        }

        if (role.equals(RoleEnum.STUDENT.getValue())||role.equals(RoleEnum.ADMIN.getValue())||role.equals(RoleEnum.TEACHER.getValue())) {
            session.setAttribute( "role", role);
        }
        List<String> accessibleUrls = SecurityConfig.getUrlPatternsForRole(role);
        if (!accessibleUrls.contains(path)) {
            log.warn("Path {} is not accessible for role {}", path, role);
            throw new PermissionDeniedException("Page is not accessible for this role");

        } else {
            chain.doFilter(req, res);
        }
    }
}
