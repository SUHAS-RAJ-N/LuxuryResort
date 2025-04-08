package com.res.servlet;

import java.io.IOException;

import com.res.DAO.RoomsDAO;
import com.res.DAO.RoomsDAOImplementation;
import com.res.DTO.Rooms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addRoom")  // Ensure the URL pattern matches your form action
public class AddRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String name = request.getParameter("name");
        String type = request.getParameter("type");
//        double price = Double.parseDouble(request.getParameter("price"));
        double price = Double.parseDouble(request.getParameter("price").trim());

        String description = request.getParameter("description");
        String imageUrl = request.getParameter("imageUrl");
        boolean availability = Boolean.parseBoolean(request.getParameter("availability"));

        Rooms room = new Rooms(0, name, type, price, description, imageUrl, availability);
        RoomsDAOImplementation roomDAO = new RoomsDAOImplementation();

        boolean success = roomDAO.addRoom(room);
        
        if (success) {
            response.sendRedirect("manageRooms.jsp?success=true");
        } else {
            response.sendRedirect("addRoom.jsp?error=true");
        }
    }
}