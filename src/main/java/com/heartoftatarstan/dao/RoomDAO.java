package com.heartoftatarstan.dao;

import com.heartoftatarstan.models.Room;
import com.heartoftatarstan.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT id, name, description, capacity, price, image_url FROM rooms";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                int capacity = rs.getInt("capacity");
                double price = rs.getDouble("price");
                String image_url = rs.getString("image_url");

                rooms.add(new Room(id, name, description, capacity, price, image_url));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public Room getRoomById(int roomId) {
        String query = "SELECT * FROM rooms WHERE id = ?";
        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Room(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("capacity"),
                        rs.getDouble("price"),
                        rs.getString("image_url")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Room> getRoomsPaginated(int limit, int offset) {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT id, name, description, capacity, price, image_url FROM rooms ORDER BY id LIMIT ? OFFSET ?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, limit);
            stmt.setInt(2, offset);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    rooms.add(new Room(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("capacity"),
                            rs.getDouble("price"),
                            rs.getString("image_url")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }
}
