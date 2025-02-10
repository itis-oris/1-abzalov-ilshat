package com.heartoftatarstan.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/book/confirm")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            String errorMessage = "Авторизуйтесь, чтобы забронировать номер. Если нет аккаунта, <a href='/auth/register'>зарегистрируйтесь</a>.";
            httpResponse.sendRedirect("/auth/login?error=" + java.net.URLEncoder.encode(errorMessage, "UTF-8"));
            return;
        }

        chain.doFilter(request, response);
    }


    @Override
    public void destroy() {}
}
