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
import jakarta.servlet.RequestDispatcher;

@WebServlet("/EditRoomServlet")
public class EditRoomServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int roomId = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/resortdb", "root", "password");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM rooms WHERE id = ?");
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("roomId", rs.getInt("id"));
                request.setAttribute("name", rs.getString("name"));
                request.setAttribute("type", rs.getString("type"));
                request.setAttribute("price", rs.getDouble("price"));
                request.setAttribute("description", rs.getString("description"));
                request.setAttribute("imageUrl", rs.getString("image_url"));
                request.setAttribute("availability", rs.getString("status"));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("editRoom.jsp");
        dispatcher.forward(request, response);
    }
}
