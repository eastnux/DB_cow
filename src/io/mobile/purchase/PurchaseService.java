package io.mobile.purchase;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseService {
    private static Purchase setPurchase(ResultSet rs) throws SQLException {
        int order_nub = rs.getInt("order_nub");
        String order_date = rs.getString("order_date");
        int order_quantity = rs.getInt("order_quantity");
        String shipping_address = rs.getString("shipping_address");

        return new Purchase(order_nub, order_date, order_quantity, shipping_address);
    }

    public static List<Purchase> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        List<Purchase> purchaseList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM purchase";

            psmtQuery = conn.prepareStatement(query);

            rs = psmtQuery.executeQuery();

            while (rs.next()) {
                Purchase purchase = setPurchase(rs);
                purchaseList.add(purchase);
                System.out.println(purchase);
            }
            return purchaseList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(psmtQuery, rs);
        }
    }

    public static Purchase selectByOrderNumber(final int order_nub) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM purchase WHERE order_nub = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, order_nub);
            rs = psmtQuery.executeQuery();
            if (rs.next()) {
                return setPurchase(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeResources(psmtQuery, rs);
        }
    }

    public static int insert(int order_nub, String order_date, int order_quantity, String shipping_address) {
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM purchase WHERE order_nub = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, order_nub);
            ResultSet rs = psmtQuery.executeQuery();

            if (!rs.next()) {
                String insertStatement = "INSERT INTO purchase VALUES (?, ?, ?, ?)";
                psmtUpdate = conn.prepareStatement(insertStatement);
                psmtUpdate.setInt(1, order_nub);
                psmtUpdate.setString(2, order_date);
                psmtUpdate.setInt(3, order_quantity);
                psmtUpdate.setString(4, shipping_address);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(psmtQuery, null);
            closeResources(psmtUpdate, null);
        }
    }

    public static int update(int order_nub, String order_date, int order_quantity, String shipping_address) {
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM purchase WHERE order_nub = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, order_nub);
            ResultSet rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String updateStatement =
                        "UPDATE purchase SET order_date = ?, order_quantity = ?, shipping_address = ? WHERE order_nub = ?";
                psmtUpdate = conn.prepareStatement(updateStatement);
                psmtUpdate.setString(1, order_date);
                psmtUpdate.setInt(2, order_quantity);
                psmtUpdate.setString(3, shipping_address);
                psmtUpdate.setInt(4, order_nub);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(psmtQuery, null);
            closeResources(psmtUpdate, null);
        }
    }

    public static int delete(int order_nub) {
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM purchase WHERE order_nub = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setInt(1, order_nub);
            ResultSet rs = psmtQuery.executeQuery();

            if (rs.next()) {
                String deleteStatement = "DELETE FROM purchase WHERE order_nub = ?";
                psmtUpdate = conn.prepareStatement(deleteStatement);
                psmtUpdate.setInt(1, order_nub);
                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            closeResources(psmtQuery, null);
            closeResources(psmtUpdate, null);
        }
    }

    private static void closeResources(PreparedStatement stmt, ResultSet rs) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
