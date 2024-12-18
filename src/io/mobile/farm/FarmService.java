package io.mobile.farm;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FarmService {

    private static final String DB_URL = Conf.DB_URL;
    private static final String DB_USER = Conf.DB_USER;
    private static final String DB_PASSWORD = Conf.DB_PASSWORD;

    // Method to connect to the database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    // Method to select all farms
    public List<Farm> selectAll() {
        List<Farm> farms = new ArrayList<>();
        String query = "SELECT farm_id, name, location FROM farms";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Farm farm = new Farm(
                        rs.getString("farm_id"),
                        rs.getString("name"),
                        rs.getString("location")
                );
                farms.add(farm);
            }
        } catch (SQLException e) {
            System.err.println("Error selecting all farms: " + e.getMessage());
        }

        return farms;
    }

    // Method to select a farm by ID
    public Farm selectById(String farmId) {
        Farm farm = null;
        String query = "SELECT farm_id, name, location FROM farms WHERE farm_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, farmId);
            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    farm = new Farm(
                            rs.getString("farm_id"),
                            rs.getString("name"),
                            rs.getString("location")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error selecting farm by ID: " + e.getMessage());
        }

        return farm;
    }

    // Method to insert a new farm
    public void insert(Farm farm) {
        String query = "INSERT INTO farms (farm_id, name, location) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, farm.getFarm_id());
            pstmt.setString(2, farm.getName());
            pstmt.setString(3, farm.getLocation());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting farm: " + e.getMessage());
        }
    }

    // Method to update a farm by ID
    public void updateById(String farmId, Farm updatedFarm) {
        String query = "UPDATE farms SET name = ?, location = ? WHERE farm_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, updatedFarm.getName());
            pstmt.setString(2, updatedFarm.getLocation());
            pstmt.setString(3, farmId);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating farm by ID: " + e.getMessage());
        }
    }

    // Method to delete a farm by ID
    public void deleteById(String farmId) {
        String query = "DELETE FROM farms WHERE farm_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, farmId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting farm by ID: " + e.getMessage());
        }
    }
}
