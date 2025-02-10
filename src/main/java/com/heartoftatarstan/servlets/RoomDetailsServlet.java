package com.heartoftatarstan.servlets;

import com.heartoftatarstan.dao.RoomDAO;
import com.heartoftatarstan.models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/rooms/details")
public class RoomDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roomIdParam = req.getParameter("roomId");
        if (roomIdParam == null) {
            resp.sendRedirect("/");
            return;
        }

        int roomId;
        try {
            roomId = Integer.parseInt(roomIdParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect("/");
            return;
        }

        RoomDAO roomDAO = new RoomDAO();
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            resp.sendRedirect("/");
            return;
        }

        req.setAttribute("room", room);
        req.getRequestDispatcher("/jsp/room_details.jsp").forward(req, resp);
    }
}
