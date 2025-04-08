package com.res.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

@WebServlet("/DashboardStatsServlet")
public class DashboardStatsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject json = new JSONObject();

        // Database credentials
        String url = "jdbc:mysql://localhost:3306/resortdb";
        String user = "root";
        String pass = "password";

        try {
            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                // Total Bookings
                try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM bookings");
                     ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) json.put("totalBookings", rs.getInt(1));
                }

                // Total Revenue
                try (PreparedStatement stmt = conn.prepareStatement("SELECT SUM(amount) FROM payments");
                     ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) json.put("totalRevenue", rs.getDouble(1));
                }

                // Available Rooms
                try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM rooms WHERE isAvailable = 1");
                     ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) json.put("availableRooms", rs.getInt(1));
                }
            }
        } catch (Exception e) {
            json.put("error", e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }

        response.getWriter().write(json.toString());
    }
}
