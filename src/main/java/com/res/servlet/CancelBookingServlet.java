package com.res.servlet;

import com.Resort.utils.CancelEmail;
import com.res.DAO.BookingDAO;
import com.res.DAO.BookingDAOImplementation;
import com.res.DTO.Booking;
import com.res.DTO.Users;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/CancelBookingServlet")
public class CancelBookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookingId = request.getParameter("bookingId");
        String userMail = request.getParameter("mail");

        if (bookingId != null && !bookingId.isEmpty()) {
            // Initialize BookingDAO to cancel the booking
            BookingDAO bookingDAO = new BookingDAOImplementation();
            Booking booking = bookingDAO.getBookingById(Integer.parseInt(bookingId));

            // Cancel the booking if it exists
            boolean cancelSuccess = bookingDAO.cancelBooking(Integer.parseInt(bookingId), userMail);

            // Send email to the customer if cancellation is successful
            if (cancelSuccess) {
                try {
                    // Send email using the EmailUtil class
                    CancelEmail.sendCancellationEmail(booking.getMail(), bookingId);

                    // Add a success message to the request
                    request.setAttribute("message", "Booking canceled successfully and confirmation email sent!");
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Error sending email.");
                }
            } else {
                request.setAttribute("message", "Booking cancellation failed.");
            }

            // Forward the request to the appropriate page
            RequestDispatcher dispatcher = request.getRequestDispatcher("Dashboard.jsp"); // Or CancelBooking.jsp, based on your flow
            dispatcher.forward(request, response);
        } else {
            // Handle invalid booking ID
            request.setAttribute("message", "Invalid booking ID.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("CancelBooking.jsp"); // Or any appropriate page
            dispatcher.forward(request, response);
        }
    }
}
