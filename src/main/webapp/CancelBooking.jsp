<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.res.DTO.Booking" %>
<%@ page import="com.res.DAO.BookingDAOImplementation" %>
<%@ page import="com.res.DAO.BookingDAO" %>
<%@ page import="com.res.DTO.Users" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cancel Booking - Resort Paradise</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    // Retrieve user from session
    Users user = (Users) session.getAttribute("customer");

    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Debugging output for the user
    out.println("User ID: " + user.getId());
    out.println("User Email: " + user.getMail());

    // Retrieve all bookings of the logged-in user
    BookingDAO bookingDAO = new BookingDAOImplementation();
    List<Booking> userBookings = bookingDAO.getBookingsByUser(user.getMail()); // Corrected
%>

<div class="container mt-5">
    <h2>Cancel Your Booking</h2>
    <p>Here are your current bookings. Select a booking to cancel.</p>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Booking ID</th>
                <th>Room Type</th>
                <th>Check-in Date</th>
                <th>Check-out Date</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
                if (userBookings.isEmpty()) {
                    out.println("<tr><td colspan='5' class='text-center'>No bookings found.</td></tr>");
                } else {
                    for (Booking booking : userBookings) {
            %>
            <tr>
                <td><%= booking.getId() %></td> 
                <td><%= booking.getRoomType() %></td>
                <td><%= booking.getCheckIn() %></td> 
                <td><%= booking.getCheckOut() %></td>
                <td>
                    <form action="CancelBookingServlet" method="GET">
                        <input type="hidden" name="bookingId" value="<%= booking.getId() %>">
                        <input type="hidden" name="roomType" value="<%= booking.getRoomType()%>">
                        <input type="hidden" name="mail" value="<%= user.getMail() %>">
                        <button type="submit" class="btn btn-danger">Cancel</button>
                    </form>
                </td>
            </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
