package com.heartoftatarstan.dao;

import com.heartoftatarstan.utils.DatabaseConnection;
import com.heartoftatarstan.utils.PasswordUtils;

import java.sql.*;

public class UserDAO {
    private DatabaseConnection instance = DatabaseConnection.getInstance();

    public boolean registerUser(String firstName, String lastName, String email, String passwordHash, String role) throws SQLException {
        String query = "INSERT INTO users (first_name, last_name, email, password_hash, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = instance.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, passwordHash);
            stmt.setString(5, role);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean userExists(String email) throws Exception {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = instance.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }

    public boolean validateUser(String email, String password) throws SQLException {
        String query = "SELECT password_hash FROM users WHERE email = ?";

        try (Connection conn = instance.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password_hash");

                return PasswordUtils.verifyPassword(password, storedHash);
            }
        }

        return false;
    }

    public String getUserRole(String email) throws SQLException {
        String query = "SELECT role FROM users WHERE email = ?";

        try (Connection conn = instance.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role");
            }
        }

        return null;
    }
}
