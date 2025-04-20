package com.res.servlet;

import com.res.DAO.RoomsDAOImplementation;
import com.res.DTO.Rooms;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/ManageRoomsServlet")
public class ManageRoomsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RoomsDAOImplementation roomDAO = new RoomsDAOImplementation();
        List<Rooms> roomList = roomDAO.getAllRooms();

        request.setAttribute("roomList", roomList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manageRooms.jsp");
        dispatcher.forward(request, response);
    }
}
