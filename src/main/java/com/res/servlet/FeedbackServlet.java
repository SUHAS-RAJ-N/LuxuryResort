package com.res.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/submitFeedback")
public class FeedbackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        Connection con = null;
        PreparedStatement ps = null;

        try {
            

            String query = "INSERT INTO feedback (user_id, room_id, rating, comment) VALUES (?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setInt(2, roomId);
            ps.setInt(3, rating);
            ps.setString(4, comment);
            
            int result = ps.executeUpdate();
            if (result > 0) {
                response.sendRedirect("feedbackSuccess.jsp");
            } else {
                response.sendRedirect("feedbackError.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException se) { se.printStackTrace(); }
        }
    }
}
