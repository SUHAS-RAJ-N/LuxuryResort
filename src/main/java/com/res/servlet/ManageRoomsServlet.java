package com.res.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ManageRoomsServlet")
public class ManageRoomsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection getConnection() throws NamingException, Exception {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ResortDB");
        return ds.getConnection();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String roomName = request.getParameter("roomName");
        String priceStr = request.getParameter("price");

        int roomId = 0;
        if (request.getParameter("roomId") != null && !request.getParameter("roomId").isEmpty()) {
            roomId = Integer.parseInt(request.getParameter("roomId"));
        }

        double price = 0;
        if (priceStr != null && !priceStr.isEmpty()) {
            price = Double.parseDouble(priceStr);
        }

        try (Connection conn = getConnection()) {
            PreparedStatement pst = null;

            if ("add".equals(action)) {
                pst = conn.prepareStatement("INSERT INTO rooms (name, price) VALUES (?, ?)");
                pst.setString(1, roomName);
                pst.setDouble(2, price);
            } else if ("update".equals(action) && roomId > 0) {
                pst = conn.prepareStatement("UPDATE rooms SET name=?, price=? WHERE id=?");
                pst.setString(1, roomName);
                pst.setDouble(2, price);
                pst.setInt(3, roomId);
            } else if ("delete".equals(action) && roomId > 0) {
                pst = conn.prepareStatement("DELETE FROM rooms WHERE id=?");
                pst.setInt(1, roomId);
            }

            if (pst != null) {
                pst.executeUpdate();
            }

            response.sendRedirect("manageRooms.jsp?success=Room updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("manageRooms.jsp?error=Something went wrong!");
        }
    }
}
