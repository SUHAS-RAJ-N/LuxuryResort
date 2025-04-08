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

@WebServlet("/UpdateRoomServlet")
public class UpdateRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        String availability = request.getParameter("availability");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortdb", "root", "password");
            PreparedStatement stmt = conn.prepareStatement("UPDATE rooms SET name=?, type=?, price=?, description=?, image_url=?, status=? WHERE id=?");

            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setDouble(3, price);
            stmt.setString(4, description);
            stmt.setString(5, imageUrl);
            stmt.setString(6, availability);
            stmt.setInt(7, roomId);
            stmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("manageRooms.jsp");
    }
}

