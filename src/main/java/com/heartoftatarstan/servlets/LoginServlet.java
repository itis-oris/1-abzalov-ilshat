package com.heartoftatarstan.servlets;

import com.heartoftatarstan.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String error = req.getParameter("error");
        if (error != null) {
            req.setAttribute("error", error);
        }
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
        logger.debug("Открыта страница Login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAO();

        try {
            if (!userDAO.userExists(email)) {
                req.setAttribute("error", "Такого пользователя нет. <a href='/auth/register'>Зарегистрируйтесь</a>.");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                return;
            }

            if (!userDAO.validateUser(email, password)) {
                req.setAttribute("error", "Неверный пароль");
                req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
                return;
            }

            logger.debug("User " + email + " logged in");

            HttpSession session = req.getSession();
            session.setAttribute("email", email);

            String role = userDAO.getUserRole(email);
            session.setAttribute("role", role);

            if ("admin".equals(role)) {
                resp.sendRedirect("/admin/bookings");
            } else {
                resp.sendRedirect("/");
            }
        } catch (Exception e) {
            throw new ServletException("Ошибка при авторизации пользователя", e);
        }
    }

}
