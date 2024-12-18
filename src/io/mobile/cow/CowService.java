package io.mobile.cow;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CowService {

    private static final String DB_URL = Conf.DB_URL;
    private static final String DB_USER = Conf.DB_USER;
    private static final String DB_PASSWORD = Conf.DB_PASSWORD;

    // Method to connect to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to select all cows
    public List<Cow> selectAll() {
        List<Cow> cows = new ArrayList<>();
        String query = "SELECT cow_id, age, health_status, gender FROM cows";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Cow cow = new Cow(
                        rs.getString("cow_id"),
                        rs.getString("age"),
                        rs.getString("health_status"),
                        rs.getString("gender")
                );
                cows.add(cow);
            }
        } catch (SQLException e) {
            System.err.println("Error selecting all cows: " + e.getMessage());
        }

        return cows;
    }

    // Method to select a cow by ID
    public Cow selectById(String cowId) {
        Cow cow = null;
        String query = "SELECT cow_id, age, health_status, gender FROM cows WHERE cow_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, cowId);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    cow = new Cow(
                            rs.getString("cow_id"),
                            rs.getString("age"),
                            rs.getString("health_status"),
                            rs.getString("gender")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error selecting cow by ID: " + e.getMessage());
        }

        return cow;
    }

    // Method to insert a new cow
    public void insert(Cow cow) {
        String query = "INSERT INTO cows (cow_id, age, health_status, gender) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, cow.getCow_id());
            pstmt.setString(2, cow.getAge());
            pstmt.setString(3, cow.getHealth_status());
            pstmt.setString(4, cow.getGender());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting cow: " + e.getMessage());
        }
    }

    // Method to update a cow by ID
    public void updateById(String cowId, Cow updatedCow) {
        String query = "UPDATE cows SET age = ?, health_status = ?, gender = ? WHERE cow_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, updatedCow.getAge());
            pstmt.setString(2, updatedCow.getHealth_status());
            pstmt.setString(3, updatedCow.getGender());
            pstmt.setString(4, cowId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating cow by ID: " + e.getMessage());
        }
    }

    // Method to delete a cow by ID
    public void deleteById(String cowId) {
        String query = "DELETE FROM cows WHERE cow_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, cowId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting cow by ID: " + e.getMessage());
        }
    }
}

