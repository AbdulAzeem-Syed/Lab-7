package util;

import java.sql.*;
import java.util.ArrayList;
import model.Customer;

/**
 * Database Connector class for interacting with database
 */
public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:5000/test_schema?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my-secret-pw";

    /**
     * Privatized constructor so as to not allow object creation
     */
    private DatabaseConnector() {
    }

    /**
     * Insert given customer to database
     * @see Customer
     * @param customer Customer object to be added
     */
    public static void addCustomer(Customer customer) {
        //add to database
        String query = "INSERT INTO customer(NAME,ITEMCOUNT) VALUES(?,?)";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, customer.getName());
            stmt.setInt(2, customer.getItemCount());
            int rows = stmt.executeUpdate();
            System.out.println("Rows impacted : " + rows);
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return lost of all customers in database
     * @see Customer
     * @return list of customers
     */
    public static ArrayList<Customer> getAllCustomers() {
//        return list of customers from db
        ArrayList<Customer> customer = new ArrayList<>();

        String query = "SELECT * FROM customer";
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Customer c = new Customer();
                c.setName(rs.getString("name"));
                c.setItemCount(rs.getInt("itemcount"));
                c.setOrderId(rs.getInt("orderId"));
                customer.add(c);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    /**
     * Delete given customer from database
     * @see Customer
     * @param c Customer to be deleted
     * 
     */
    public static void deleteCustomer(Customer c) {
        String query = "delete from customer where orderId = ?";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, c.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit given customer details in the database
     * @param oldCustomer existing customer in database
     * @param newCustomer modified customer details to be added
     */
    public static void editCustomer(Customer oldCustomer, Customer newCustomer) {
        String query = "UPDATE customer SET name=?, itemCount=? WHERE orderId=?";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, newCustomer.getName());
            stmt.setInt(2, newCustomer.getItemCount());
            stmt.setInt(3, oldCustomer.getOrderId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
