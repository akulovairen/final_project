package com.example.servletstest.filters;

import com.example.servletstest.util.LocalizedValidatorUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
public class ResourceBundleFilter implements Filter {
    Logger log = LogManager.getLogger(this);
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpSession session = req.getSession();
        log.info("FILTER SESSION ID = " + session.getId());

        String lngParam = req.getParameter("lang");
        String languageSession = (String) session.getAttribute("language");
        log.info("Filter start. LngParam = {}, languageSession = {}", lngParam, languageSession);

        if (lngParam == null && languageSession == null) {
            languageSession = "";
            session.setAttribute("language",languageSession);
            session.setAttribute("userLocale", LocalizedValidatorUtil.UKRAINE_LOCALE);
        } else if (lngParam != null) {
            languageSession = lngParam;
            if("EN".equals(languageSession)){
                languageSession = "en_US";
                session.setAttribute("language",languageSession);
                session.setAttribute("userLocale", LocalizedValidatorUtil.ENGLISH_LOCALE);
            }else {
                languageSession = "";
                session.setAttribute("language", languageSession);
                session.setAttribute("userLocale", LocalizedValidatorUtil.UKRAINE_LOCALE);
            }
        }
        log.info("Filter end. LngParam = {}, languageSession = {}", lngParam, languageSession);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
