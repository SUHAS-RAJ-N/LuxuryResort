<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.res.DAO.BookingDAO" %>
<%@ page import="com.res.DAO.BookingDAOImplementation" %>
<%@ page import="com.res.DTO.Booking" %>
<%@ page import="com.res.DTO.Users" %>

<%
    // Retrieve the user from session
    Users user = (Users) session.getAttribute("customer");

    // Check if user is logged in
    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Retrieve booking ID
    String bookingId = request.getParameter("bookingId");
    String roomType=request.getParameter("roomType");

    if (bookingId != null && !bookingId.isEmpty()) {
        // Call DAO to cancel the booking
        BookingDAO bookingDAO = new BookingDAOImplementation(); // Instantiate the correct implementation
        boolean success = bookingDAO.cancelBooking(Integer.parseInt(bookingId), user.getMail()); // Use user's email

        if (success) {
            out.println("<script>alert('Booking cancelled successfully!'); window.location='Dashboard.jsp';</script>");
        } else {
            out.println("<script>alert('Failed to cancel the booking. Please try again.'); window.location='CancelBooking.jsp';</script>");
        }
    } else {
        out.println("<script>alert('Invalid booking ID.'); window.location='CancelBooking.jsp';</script>");
    }
%>
