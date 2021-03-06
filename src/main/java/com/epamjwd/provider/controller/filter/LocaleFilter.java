package com.epamjwd.provider.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebFilter(urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    private static final String AVAILABLE_LANGUAGES_REGEX = "(en-US)|(ru-RU)";
    private static final String DEFAULT_LOCALE = "ru-RU";
    private static final String COOKIE_LOCALE_NAME = "lang";
    private static final String LOCALE_PARAMETER = "cookieLocale";


    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String locale = request.getParameter(LOCALE_PARAMETER);

        Cookie localeCookie;
        if (request.getCookies() == null) {
            localeCookie = new Cookie(COOKIE_LOCALE_NAME, DEFAULT_LOCALE);
        } else {
            localeCookie = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equalsIgnoreCase(COOKIE_LOCALE_NAME))
                    .findFirst().orElse(new Cookie(COOKIE_LOCALE_NAME, DEFAULT_LOCALE));
        }

        if (locale != null && locale.matches(AVAILABLE_LANGUAGES_REGEX)) {
            localeCookie.setValue(locale);
        }
        response.addCookie(localeCookie);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
