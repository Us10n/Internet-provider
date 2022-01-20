package com.epamjwd.provider.controller.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();

    private static final String AVAILABLE_LANGUAGES_REGEX = "(en-US)|(ru-RU)";
    private static final String DEFAULT_LOCALE = "en-US";
    private static final String COOKIE_LOCALE_NAME = "lang";
    private static final String LOCALE_PARAMETER = "cookieLocale";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.info("do locale filter"+ request.getRequestURL());

        String locale = request.getParameter(LOCALE_PARAMETER);

        Cookie localeCookie;
        if (request.getCookies() == null) {
            localeCookie = new Cookie(COOKIE_LOCALE_NAME, DEFAULT_LOCALE);
        } else {
            localeCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equalsIgnoreCase(COOKIE_LOCALE_NAME))
                    .findFirst().orElse(new Cookie(COOKIE_LOCALE_NAME, DEFAULT_LOCALE));
        }

        Cookie newCookie;
        if (locale == null || !locale.matches(AVAILABLE_LANGUAGES_REGEX)) {
            newCookie = localeCookie;
        } else {
            logger.info("Locale wa changed");
            newCookie = new Cookie(COOKIE_LOCALE_NAME, locale);
        }
        newCookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(newCookie);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
