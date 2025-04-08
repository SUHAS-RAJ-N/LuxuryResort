package com.res.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.res.DTO.Feedback;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/manageFeedback")
public class ManageFeedbackServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "tiger");

            String query = "SELECT * FROM feedback";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            ArrayList<Feedback> feedbackList = new ArrayList<>();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setFeedbackId(rs.getInt("feedback_id"));
                feedback.setUserId(rs.getInt("user_id"));
                feedback.setRoomId(rs.getInt("room_id"));
                feedback.setRating(rs.getInt("rating"));

                // Handle potential null values in comment
                String comment = rs.getString("comment");
                if (comment != null) {
                    feedback.setComment(comment);
                } else {
                    feedback.setComment(""); // or handle accordingly
                }

                feedback.setFeedbackDate(rs.getTimestamp("feedback_date"));
                feedbackList.add(feedback);
            }


            request.setAttribute("feedbackList", feedbackList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("manageFeedback.jsp");
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
