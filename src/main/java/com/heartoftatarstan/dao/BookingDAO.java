package com.heartoftatarstan.dao;

import com.heartoftatarstan.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingDAO {
    public boolean bookRoom(String email, int roomId, String checkInDate, String checkOutDate) {
        String query = "INSERT INTO bookings (email, room_id, check_in_date, check_out_date) " +
                "SELECT ?, ?, ?, ? " +
                "WHERE NOT EXISTS (" +
                "  SELECT 1 FROM bookings " +
                "  WHERE room_id = ? AND (check_in_date < ? AND check_out_date > ?)" +
                ")";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setInt(2, roomId);
            stmt.setDate(3, Date.valueOf(checkInDate));
            stmt.setDate(4, Date.valueOf(checkOutDate));
            stmt.setInt(5, roomId);
            stmt.setDate(6, Date.valueOf(checkOutDate));
            stmt.setDate(7, Date.valueOf(checkInDate));
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getRoomNameById(int roomId) {
        String query = "SELECT name FROM rooms WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
