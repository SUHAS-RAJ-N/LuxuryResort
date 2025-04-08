package com.res.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sendNotification")
public class NotificationsServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		String message = request.getParameter("message");

		Connection con=null;
		PreparedStatement ps = null;

		try {


			String query = "INSERT INTO notifications (user_id, message, status) VALUES (?, ?, 'unread')";
			ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setString(2, message);

			int result = ps.executeUpdate();
			if (result > 0) {
				response.sendRedirect("notificationSuccess.jsp");
			} else {
				response.sendRedirect("notificationError.jsp");
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
