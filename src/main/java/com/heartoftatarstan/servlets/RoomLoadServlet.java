package com.heartoftatarstan.servlets;

import com.heartoftatarstan.dao.RoomDAO;
import com.heartoftatarstan.models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/rooms/load")
public class RoomLoadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = Integer.parseInt(req.getParameter("limit"));
        int offset = Integer.parseInt(req.getParameter("offset"));

        RoomDAO roomDAO = new RoomDAO();
        List<Room> rooms = roomDAO.getRoomsPaginated(limit, offset);

        StringBuilder json = new StringBuilder();
        json.append("[");

        for (int i = 0; i < rooms.size(); i++) {
            Room room = rooms.get(i);
            json.append("{")
                    .append("\"id\":").append(room.getId()).append(",")
                    .append("\"name\":\"").append(escapeJson(room.getName())).append("\",")
                    .append("\"description\":\"").append(escapeJson(room.getDescription())).append("\",")
                    .append("\"capacity\":").append(room.getCapacity()).append(",")
                    .append("\"price\":").append(room.getPrice()).append(",")
                    .append("\"image_url\":\"").append(escapeJson(room.getImage_url())).append("\"")
                    .append("}");
            if (i < rooms.size() - 1) {
                json.append(",");
            }
        }

        json.append("]");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(json.toString());
    }

    private String escapeJson(String value) {
        return value == null ? "" : value.replace("\"", "\\\"");
    }
}
