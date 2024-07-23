package com.emergency_link.emergency_link;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataBaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/Emergency_Link";
        String username = "root";
        String password = "dorida12";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO Hospital_Info (hpid, duty_name, post_cdn1, post_cdn2, duty_addr, main_phone, er_phone, inpa_avail, er_avail) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "H123");
                statement.setString(2, "Central Hospital");
                statement.setInt(3, 12345);
                statement.setInt(4, 6789);
                statement.setString(5, "123 Main St");
                statement.setString(6, "1234567890");
                statement.setString(7, "9876543210");
                statement.setBoolean(8, true);
                statement.setBoolean(9, true);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new hospital info was inserted successfully!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}