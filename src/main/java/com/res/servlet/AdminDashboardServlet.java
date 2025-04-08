package com.res.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.res.Connection.Connector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AdminDashboardServlet")
public class AdminDashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null || userId != 1) {
            response.sendRedirect("index.jsp");
            return;
        }

        try (Connection con = Connector.requestConnection()) {
            int totalUsers = getCount(con, "SELECT COUNT(*) FROM users WHERE role='user'");
            int totalBookings = getCount(con, "SELECT COUNT(*) FROM booking");
            int totalRooms = getCount(con, "SELECT COUNT(*) FROM rooms");

            request.setAttribute("totalUsers", totalUsers);
            request.setAttribute("totalBookings", totalBookings);
            request.setAttribute("totalRooms", totalRooms);

            Map<String, Integer> bookingsChart = new LinkedHashMap<>();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT MONTHNAME(booking_time) as month, COUNT(*) as count FROM booking GROUP BY MONTH(booking_time), MONTHNAME(booking_time) ORDER BY MONTH(booking_time)"
            );

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bookingsChart.put(rs.getString("month"), rs.getInt("count"));
            }
            request.setAttribute("bookingsChart", bookingsChart);

            request.getRequestDispatcher("admin-dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }

    private int getCount(Connection con, String query) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        }
    }
}
