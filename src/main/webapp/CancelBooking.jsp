<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.res.DTO.Booking"%>
<%@ page import="com.res.DAO.BookingDAOImplementation"%>
<%@ page import="com.res.DAO.BookingDAO"%>
<%@ page import="com.res.DTO.Users"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cancel Booking</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    Users user = (Users) session.getAttribute("customer");
    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    String cancelMsg = (String) session.getAttribute("cancelMsg");
    if (cancelMsg != null) {
%>
    <div class="alert alert-info alert-dismissible fade show mx-5 mt-3" role="alert">
        <%= cancelMsg %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
<%
        session.removeAttribute("cancelMsg");
    }

    boolean isAdmin = (user.getId() == 1);
    BookingDAO bookingDAO = new BookingDAOImplementation();
    List<Booking> bookings = isAdmin ?
        bookingDAO.getAllBookings() :
        bookingDAO.getBookingsByUser(user.getMail());
%>

<div class="container mt-5">
    <h2><%= isAdmin ? "Manage All Bookings" : "Cancel Your Booking" %></h2>

    <table class="table table-striped">
        <thead>
            <tr>
                <th>Booking ID</th>
                <% if (isAdmin) { %><th>User Email</th><% } %>
                <th>Room Type</th>
                <th>Check-in</th>
                <th>Check-out</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <% if (bookings == null || bookings.isEmpty()) { %>
            <tr>
                <td colspan="<%= isAdmin ? 6 : 5 %>" class="text-center">No bookings found.</td>
            </tr>
        <% } else {
            for (Booking b : bookings) {
        %>
            <tr>
                <td><%= b.getId() %></td>
                <% if (isAdmin) { %><td><%= b.getMail() %></td><% } %>
                <td><%= b.getRoomType() %></td>
                <td><%= b.getCheckIn() %></td>
                <td><%= b.getCheckOut() %></td>
                <td>
                    <form action="CancelBookingServlet" method="post">
                        <input type="hidden" name="bookingId" value="<%= b.getId() %>">
                        <input type="hidden" name="mail" value="<%= b.getMail() %>">
                        <button type="submit" class="btn btn-danger btn-sm">Cancel</button>
                    </form>
                </td>
            </tr>
        <% } } %>
        </tbody>
    </table>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
