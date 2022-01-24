package com.epamjwd.provider.controller.filter;

import com.epamjwd.provider.controller.AccessLevel;
import com.epamjwd.provider.controller.command.constants.CommandName;
import com.epamjwd.provider.model.entity.Role;
import com.epamjwd.provider.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebFilter(urlPatterns = "/controller")
public class CommandAccessFilter implements Filter {
    private static final int FORBIDDEN_STATUS = 403;
    private static final String USER_ATTRIBUTE = "user";
    private static final String COMMAND_PARAMETER = "command";

    private HashMap<String, List<AccessLevel>> commandAccessMap = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        commandAccessMap.put(CommandName.INTERNAL_ERROR, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.PAGE_NOT_FOUND_ERROR, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.FORBIDDEN_ERROR, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.HOME, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.LOG_IN, List.of(AccessLevel.LEVEL_GUEST));
        commandAccessMap.put(CommandName.SIGN_UP, List.of(AccessLevel.LEVEL_GUEST));
        commandAccessMap.put(CommandName.TARIFFS, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.SORT, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.PROMOTIONS, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.SINGLE_TARIFF, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.CHANGE_LOCALE, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.ABOUT_US, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.SIGN_UP_USER, List.of(AccessLevel.LEVEL_GUEST));
        commandAccessMap.put(CommandName.LOG_IN_USER, List.of(AccessLevel.LEVEL_GUEST));
        commandAccessMap.put(CommandName.LOG_OUT_USER, List.of(AccessLevel.LEVEL_USER, AccessLevel.LEVEL_ADMIN));
        commandAccessMap.put(CommandName.VERIFY, List.of(AccessLevel.LEVEL_ANY));
        commandAccessMap.put(CommandName.PROFILE, List.of(AccessLevel.LEVEL_USER, AccessLevel.LEVEL_ADMIN));
        commandAccessMap.put(CommandName.TARIFF_ADD_PAGE, List.of(AccessLevel.LEVEL_ANY, AccessLevel.LEVEL_ADMIN)); //todo(after debug remove level_any)
        commandAccessMap.put(CommandName.TARIFF_ADD, List.of(AccessLevel.LEVEL_ANY, AccessLevel.LEVEL_ADMIN)); //todo(after debug remove level_any)
        commandAccessMap.put(CommandName.TARIFF_EDIT_PAGE, List.of(AccessLevel.LEVEL_ANY, AccessLevel.LEVEL_ADMIN)); //todo(after debug remove level_any)
        commandAccessMap.put(CommandName.TARIFF_EDIT, List.of(AccessLevel.LEVEL_ANY, AccessLevel.LEVEL_ADMIN)); //todo(after debug remove level_any)
        commandAccessMap.put(CommandName.BALANCE_RECHARGE_PAGE, List.of(AccessLevel.LEVEL_ANY, AccessLevel.LEVEL_ADMIN, AccessLevel.LEVEL_USER)); //todo(after debug remove level_any)
        commandAccessMap.put(CommandName.BALANCE_RECHARGE, List.of(AccessLevel.LEVEL_ADMIN, AccessLevel.LEVEL_USER));
        commandAccessMap.put(CommandName.PROFILE_EDIT, List.of(AccessLevel.LEVEL_ADMIN, AccessLevel.LEVEL_USER));
        commandAccessMap.put(CommandName.PROMOTION_EDIT, List.of(AccessLevel.LEVEL_ANY, AccessLevel.LEVEL_ADMIN)); //todo(after debug remove level_any)
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String command = request.getParameter(COMMAND_PARAMETER);

        User user = (User) request.getSession().getAttribute(USER_ATTRIBUTE);
        AccessLevel currentAccessLevel;
        if (user != null) {
            currentAccessLevel = user.getRole() == Role.ADMIN ?
                    AccessLevel.LEVEL_ADMIN : AccessLevel.LEVEL_USER;
        } else {
            currentAccessLevel = AccessLevel.LEVEL_GUEST;
        }
        if (command != null) {
            if (commandAccessMap.get(command) == null ||
                    (!commandAccessMap.get(command).contains(currentAccessLevel) &&
                            !commandAccessMap.get(command).contains(AccessLevel.LEVEL_ANY))) {
                response.setStatus(FORBIDDEN_STATUS);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/controller?command=pageNotFoundError");
                requestDispatcher.forward(servletRequest, servletResponse);
                return;
            }

        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
