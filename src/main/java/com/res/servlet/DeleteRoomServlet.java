package com.res.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DeleteRoomServlet")
public class DeleteRoomServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortdb", "root", "password");
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM rooms WHERE id = ?");
            stmt.setInt(1, roomId);
            stmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("manageRooms.jsp");
    }
}
