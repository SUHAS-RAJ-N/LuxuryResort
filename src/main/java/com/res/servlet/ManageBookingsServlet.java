package com.res.servlet;

import java.io.IOException;
import java.util.List;

import com.res.DAO.BookingDAO;
import com.res.DAO.BookingDAOImplementation;
import com.res.DTO.Booking;
import com.res.DTO.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/manageBookings") // This is the URL to access the servlet
public class ManageBookingsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the customer from session
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("customer");

        if (user == null) {
            response.sendRedirect("Login.jsp"); // Redirect to login page if the user is not logged in
            return;
        }

        // Retrieve user bookings using BookingDAO
        BookingDAO bookingDAO = new BookingDAOImplementation();
        List<Booking> userBookings = bookingDAO.getBookingsByUser(user.getMail());

        // Set the user bookings in request scope
        request.setAttribute("userBookings", userBookings);

        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/ManageBookings.jsp");
        dispatcher.forward(request, response);
    }
}
