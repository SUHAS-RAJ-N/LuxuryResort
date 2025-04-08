package com.res.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    public static Connection requestConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/hotel";
        String user = "root";  // Change this if your MySQL username is different
        String password = "tiger";  // Change this to your MySQL password

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(true); // Ensure transactions are committed automatically
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
