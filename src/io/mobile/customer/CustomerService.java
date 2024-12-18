package io.mobile.customer;

import io.mobile.conf.Conf;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private static Customer setCustomer(ResultSet rs) throws SQLException {
        String name = rs.getString("name");
        String customer_id = rs.getString("customer_id");
        String address = rs.getString("address");

        return new Customer(name, customer_id, address);
    }

    public static List<Customer> selectAll() {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        List<Customer> customerList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)){
            String query = "SELECT * FROM customer";

            psmtQuery = conn.prepareStatement(query);

            rs = psmtQuery.executeQuery();

            while (rs.next()) {

                Customer customer = setCustomer(rs);
                customerList.add(customer);

                System.out.println(customer);
            }
            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                }
                catch (SQLException e) {}
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public static Customer selectById(final String customer_id){
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM customer WHERE customer_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, customer_id);
            rs = psmtQuery.executeQuery();
            if (rs.next()) {
                Customer customer = setCustomer(rs);
                return customer;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (psmtQuery != null) {
                try {
                    psmtQuery.close();
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
        }
    }

    public static int insert(String customer_id, String name, String address) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {
            String query = "SELECT * FROM customer WHERE customer_id = ?";
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, customer_id);
            rs = psmtQuery.executeQuery();

            if (!rs.next()) {
                String insertStatement = "INSERT INTO customer VALUES (?,?,?)";
                psmtUpdate = conn.prepareStatement(insertStatement);
                psmtUpdate.setString(1, customer_id);
                psmtUpdate.setString(2, name);
                psmtUpdate.setString(3, address);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {

            // psmtQuery.close(); 를 바로 못 쓰는 이유 : try catch로 묶어줘야함
            if (psmtQuery != null) {
                try {
                    psmtQuery.close(); //코드가 끝났으면 연결 끊어주기
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public static int update(String customer_id, String name, String address) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String query = "SELECT * FROM customer WHERE customer_id = ?"; // SELECT 문 안에선 ; 쓰지 말 것
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, customer_id); //첫번째 String을 customerId로 바꿔줌
            rs = psmtQuery.executeQuery();

            if (rs.next()) { //입력할 값이 있어야 수정이 가능함.
                String updateStatement =
                        "UPDATE customer" +
                                "SET name = ?, address = ?" +
                                "WHERE customer_id = ?";
                psmtUpdate = conn.prepareStatement(updateStatement);

//              psmtUpdate.setString(2, customerName);
                psmtUpdate.setString(1, name);
                psmtUpdate.setString(2, address);
                psmtUpdate.setString(3, customer_id);

                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {

            // psmtQuery.close(); 를 바로 못 쓰는 이유 : try catch로 묶어줘야함
            if (psmtQuery != null) {
                try {
                    psmtQuery.close(); //코드가 끝났으면 연결 끊어주기
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
        }
    }

    public static int delete(String customer_id) {
        ResultSet rs = null;
        PreparedStatement psmtQuery = null;
        PreparedStatement psmtUpdate = null;

        try (Connection conn = DriverManager.getConnection(Conf.DB_URL, Conf.DB_USER, Conf.DB_PASSWORD)) {

            String query = "SELECT * FROM customer WHERE customer_id = ?"; // SELECT 문 안에선 ; 쓰지 말 것
            psmtQuery = conn.prepareStatement(query);
            psmtQuery.setString(1, customer_id); //첫번째 String을 customerId로 바꿔줌
            rs = psmtQuery.executeQuery();

            if (rs.next()) { //입력할 값이 있어야 삭제가 가능함.
                String deleteStatement =
                        "DELETE FROM customer WHERE customer_id = ?";
                psmtUpdate = conn.prepareStatement(deleteStatement);

                psmtUpdate.setString(1, customer_id);
                return psmtUpdate.executeUpdate();
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {

            // psmtQuery.close(); 를 바로 못 쓰는 이유 : try catch로 묶어줘야함
            if (psmtQuery != null) {
                try {
                    psmtQuery.close(); //코드가 끝났으면 연결 끊어주기
                } catch (SQLException e) {
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {

                }
            }
        }
    }
}
