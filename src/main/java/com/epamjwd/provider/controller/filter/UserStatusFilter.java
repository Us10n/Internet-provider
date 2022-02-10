package com.epamjwd.provider.controller.filter;

import com.epamjwd.provider.model.entity.UserStatus;
import com.epamjwd.provider.model.pool.ActiveUserPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class UserStatusFilter implements Filter {
    private static final String COMMAND_PARAMETER = "command";
    private static final String LOG_OUT_PARAMETER = "logoutUser";
    private static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    private static final String USER_STATUS_ATTRIBUTE = "userStatus";
    private static final String LOG_OUT_COMMAND = "/controller?command=logoutUser";


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String commandParameter = request.getParameter(COMMAND_PARAMETER);

        if (session != null) {
            UserStatus userStatus = (UserStatus) session.getAttribute(USER_STATUS_ATTRIBUTE);
            String userEmail = (String) session.getAttribute(USER_EMAIL_ATTRIBUTE);
            if (userStatus != null && userEmail != null) {
                if (commandParameter != null && commandParameter.equalsIgnoreCase(LOG_OUT_PARAMETER)) {
                    ActiveUserPool.getInstance().removeUser(userEmail);
                } else {
                    if (userStatus == UserStatus.BANNED && ActiveUserPool.getInstance().isActive(userEmail)) {
                        ActiveUserPool.getInstance().removeUser(userEmail);
                        request.getRequestDispatcher(LOG_OUT_COMMAND)
                                .forward(request, response);
                        return;
                    }
                }

            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
