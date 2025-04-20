package com.res.servlet;

import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.Resort.utils.EmailSender;
import com.res.DAO.BookingDAOImplementation;
import com.res.DTO.Booking;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAOImplementation bookingDAO;

    public void init() {
        bookingDAO = new BookingDAOImplementation();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // Avoid creating a new session if it doesn't exist

        if (session == null || session.getAttribute("mail") == null) {
            request.setAttribute("error", "Please log in first.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return; // Stop further execution
        }

        String mail = (String) session.getAttribute("mail");

        try {
            // Retrieve booking details
            String roomType = request.getParameter("roomType");
            String price = request.getParameter("price");

            // Validate check-in and check-out dates
            String checkInStr = request.getParameter("checkIn");
            String checkOutStr = request.getParameter("checkOut");

            if (checkInStr == null || checkOutStr == null || checkInStr.isEmpty() || checkOutStr.isEmpty()) {
                request.setAttribute("error", "Invalid check-in or check-out date.");
                request.getRequestDispatcher("Booking.jsp").forward(request, response);
                return;
            }

            Date checkIn = Date.valueOf(checkInStr);
            Date checkOut = Date.valueOf(checkOutStr);

            // Check room availability
            if (!bookingDAO.isRoomAvailable(roomType, checkIn, checkOut)) {
                request.setAttribute("error", "Room unavailable.");
                request.getRequestDispatcher("Booking.jsp").forward(request, response);
                return;
            }

            // Book the room
            Booking booking = new Booking(0, mail, roomType, checkIn, checkOut);
            int bookingId = bookingDAO.bookRoom(booking);

            if (bookingId > 0) {
                // Send confirmation email
                String subject = "Booking Confirmation - " + roomType;
                String body = "<html><body>"
                        + "<h2>Booking Confirmation</h2>"
                        + "<p>Dear Customer,</p>"
                        + "<p>Your booking for a <b>" + roomType + "</b> room is confirmed.</p>"
                        + "<p><b>Booking ID:</b> " + bookingId + "</p>"
                        + "<p><b>Check-in Date:</b> " + checkIn + "</p>"
                        + "<p><b>Check-out Date:</b> " + checkOut + "</p>"
                        + "<p><b>Price:</b> Rs." + price + "</p>"
                        + "<img src='cid:roomImage' alt='" + roomType + "' style='width:100%;max-width:600px;border-radius:10px;margin-top:10px;'/>"
                        + "<p>Thank you for choosing our resort!</p>"
                        + "<p>Best Regards,</p>"
                        + "<p><b>Resort Team</b></p>"
                        + "</body></html>";

                try {
                    EmailSender.sendEmail(mail, subject, body, true);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("❌ Email sending failed: " + e.getMessage());
                }

                // Redirect to success page instead of forwarding
                response.sendRedirect("BookingSuccess.jsp?bookingId=" + bookingId + "&roomType=" + roomType);

            } else {
                request.setAttribute("error", "Booking failed. Please try again.");
                request.getRequestDispatcher("Booking.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing your booking.");
            request.getRequestDispatcher("Booking.jsp").forward(request, response);
        }
    }
}
