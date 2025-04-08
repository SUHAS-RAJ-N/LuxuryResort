package com.res.servlet;

import com.res.DAO.RoomsDAOImplementation;
import com.res.DTO.Rooms;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rooms")
public class RoomsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoomsDAOImplementation roomsDAO;

    public void init() {
        roomsDAO = new RoomsDAOImplementation();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Rooms> roomList = roomsDAO.getAllRooms();
        request.setAttribute("rooms", roomList);
        request.getRequestDispatcher("rooms.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            double price = Double.parseDouble(request.getParameter("price"));
            String description = request.getParameter("description");
            String imageUrl = request.getParameter("imageUrl");
            boolean isAvailable = Boolean.parseBoolean(request.getParameter("isAvailable"));

            Rooms room = new Rooms(0, name, type, price, description, imageUrl, isAvailable);
            roomsDAO.addRoom(room);
            response.sendRedirect("rooms");
        }
    }
}
