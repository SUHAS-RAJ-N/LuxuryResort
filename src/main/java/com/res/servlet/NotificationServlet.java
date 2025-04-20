package com.res.servlet;

import com.res.DAO.NotificationDAO;
import com.res.DAO.NotificationDAOImplementation;
import com.res.DTO.Notification;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get notifications from the DAO
        NotificationDAO dao = new NotificationDAOImplementation();
        List<Notification> notifications = dao.getAllNotifications();

        // Set notifications as a request attribute
        request.setAttribute("notifications", notifications);

        // Forward to the viewNotifications.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewNotifications.jsp");
        dispatcher.forward(request, response);
    }
}
