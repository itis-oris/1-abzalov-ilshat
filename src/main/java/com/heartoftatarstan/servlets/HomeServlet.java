package com.heartoftatarstan.servlets;

import com.heartoftatarstan.dao.RoomDAO;
import com.heartoftatarstan.models.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    private final RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Room> rooms = roomDAO.getRoomsPaginated(4, 0);
        request.setAttribute("rooms", rooms);
        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }
}
