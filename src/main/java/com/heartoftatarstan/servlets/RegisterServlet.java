package com.heartoftatarstan.servlets;

import com.heartoftatarstan.dao.UserDAO;
import com.heartoftatarstan.utils.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/auth/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAO();

        try {
            String hashedPassword = PasswordUtils.hashPassword(password);

            if (userDAO.registerUser(firstName, lastName, email, hashedPassword, "user")) {
                resp.sendRedirect("/auth/login");
            } else {
                req.setAttribute("error", "Пользователь уже существует");
                req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            throw new ServletException("Ошибка при регистрации пользователя", e);
        }
    }
}
