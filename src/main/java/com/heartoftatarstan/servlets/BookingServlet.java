package com.heartoftatarstan.servlets;

import com.heartoftatarstan.dao.BookingDAO;

import com.heartoftatarstan.dao.RoomDAO;
import com.heartoftatarstan.models.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/book/confirm")
public class BookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoomDAO roomDAO = new RoomDAO();
        List<Room> rooms = roomDAO.getAvailableRooms();

        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("/jsp/book_room.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = (String) req.getSession().getAttribute("email");
        int roomId = Integer.parseInt(req.getParameter("roomType"));
        String checkInDate = req.getParameter("checkInDate");
        String checkOutDate = req.getParameter("checkOutDate");

        BookingDAO bookingDAO = new BookingDAO();
        boolean success = bookingDAO.bookRoom(email, roomId, checkInDate, checkOutDate);

        if (success) {
            String roomName = bookingDAO.getRoomNameById(roomId);
            req.setAttribute("successMessage", String.format("Вы успешно забронировали номер \"%s\" с %s по %s.", roomName, checkInDate, checkOutDate));
        } else {
            req.setAttribute("error", "К сожалению, выбранный номер недоступен на указанные даты.");
        }
        RoomDAO roomDAO = new RoomDAO();
        List<Room> rooms = roomDAO.getAvailableRooms();

        req.setAttribute("rooms", rooms);
        req.getRequestDispatcher("/jsp/book_room.jsp").forward(req, resp);
    }
}
