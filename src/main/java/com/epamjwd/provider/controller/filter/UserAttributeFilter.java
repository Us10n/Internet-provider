package com.epamjwd.provider.controller.filter;

import com.epamjwd.provider.exception.ServiceException;
import com.epamjwd.provider.model.entity.BankAccount;
import com.epamjwd.provider.model.entity.User;
import com.epamjwd.provider.model.service.BankAccountService;
import com.epamjwd.provider.model.service.ServiceHolder;
import com.epamjwd.provider.model.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/*")
public class UserAttributeFilter implements Filter {
    private static final String USER_ID_ATTRIBUTE = "userId";
    private static final String USER_ROLE_ATTRIBUTE = "userRole";
    private static final String USER_TARIFF_ID_ATTRIBUTE = "userTariffId";
    private static final String USER_STATUS_ATTRIBUTE = "userStatus";
    private static final String INTERNAL_ERROR_PAGE = "/controller?command=internalError";

    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();

        Long currentUserId = (Long) session.getAttribute(USER_ID_ATTRIBUTE);

        if (currentUserId != null) {
            UserService userService = ServiceHolder.getInstance().getUserService();
            BankAccountService bankAccountService = ServiceHolder.getInstance().getBankAccountService();
            try {
                Optional<User> optionalUser = userService.findUserById(currentUserId);
                if (optionalUser.isEmpty()) {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(INTERNAL_ERROR_PAGE);
                    requestDispatcher.forward(servletRequest, servletResponse);
                    return;
                }
                BankAccount bankAccount = bankAccountService.findBankAccountByUserId(currentUserId);
                User loadedUser = optionalUser.get();
                session.setAttribute(USER_TARIFF_ID_ATTRIBUTE, bankAccount.getTariffId().orElse(null));
                session.setAttribute(USER_ROLE_ATTRIBUTE, loadedUser.getRole());
                session.setAttribute(USER_STATUS_ATTRIBUTE, loadedUser.getStatus());
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
