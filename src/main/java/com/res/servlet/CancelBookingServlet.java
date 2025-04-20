package com.res.servlet;

import com.Resort.utils.CancelEmail;
import com.res.DAO.BookingDAO;
import com.res.DAO.BookingDAOImplementation;

import jakarta.mail.MessagingException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/CancelBookingServlet")
public class CancelBookingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("customer") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        String bookingIdStr = request.getParameter("bookingId");
        String mail = request.getParameter("mail");

        if (bookingIdStr != null && mail != null) {
            int bookingId = Integer.parseInt(bookingIdStr);
            BookingDAO bookingDAO = new BookingDAOImplementation();
            boolean success = bookingDAO.cancelBooking(bookingId, mail);

            if (success) {
                // Send cancellation email
                try {
                    CancelEmail.sendCancellationEmail(mail, String.valueOf(bookingId));
                } catch (MessagingException e) {
                    e.printStackTrace();
                    session.setAttribute("cancelMsg", "Booking cancelled, but failed to send email notification.");
                    response.sendRedirect("CancelBooking.jsp");
                    return;
                }

                session.setAttribute("cancelMsg", "Booking cancelled successfully and cancellation email sent.");
            } else {
                session.setAttribute("cancelMsg", "Cancellation failed. Please try again.");
            }
        }

        response.sendRedirect("CancelBooking.jsp");
    }
}
