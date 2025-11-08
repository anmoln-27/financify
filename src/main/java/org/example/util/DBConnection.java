package org.example.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/finance_db";
    private static final String USER = "root";
    private static final String PASS = "pitampura27"; // change this to your actual MySQL password

    private static Connection connection;

    // This method will be used by other classes to get the database connection
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASS);
        }
        return connection;
    }

    // Quick test: lets us run this file directly to check the connection
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            System.out.println("✅ Connected successfully to finance_db!");
            conn.close();
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}
