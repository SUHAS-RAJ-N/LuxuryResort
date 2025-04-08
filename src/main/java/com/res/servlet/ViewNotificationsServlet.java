package com.res.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.res.DTO.Notification;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewNotifications")
public class ViewNotificationsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "tigerx");

            String query = "SELECT * FROM notifications";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            ArrayList<Notification> notificationList = new ArrayList<>();
            while (rs.next()) {
                Notification notification = new Notification();  // This will now work
                notification.setNotificationId(rs.getInt("notification_id"));
                notification.setUserId(rs.getInt("user_id"));
                notification.setMessage(rs.getString("message"));
                notification.setStatus(rs.getString("status"));
                notification.setNotificationDate(rs.getTimestamp("notification_date"));
                notificationList.add(notification);
            }


            request.setAttribute("notificationList", notificationList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("viewNotifications.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException se) { se.printStackTrace(); }
        }
    }
}
