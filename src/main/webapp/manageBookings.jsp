<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.res.DAO.BookingDAO"%>
<%@ page import="com.res.DTO.Booking"%>
<%@ page import="com.res.DAO.BookingDAOImplementation"%>
<%@ page import="com.res.DTO.Users"%>
<%@ page import="java.util.List"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<%
    // Retrieve session
    HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("userId") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    // Determine if the user is admin (userId = 1)
    int userId = (int) session1.getAttribute("userId");
    boolean isAdmin = (userId == 1);

    Users user = (Users) session1.getAttribute("customer");

    BookingDAO bookingDAO = new BookingDAOImplementation();
    List<Booking> bookings;

    if (isAdmin) {
        bookings = bookingDAO.getAllBookings();  // Admin sees all bookings
    } else {
        if (user == null) {
            response.sendRedirect("Login.jsp");
            return;
        }
        bookings = bookingDAO.getBookingsByUser(user.getMail()); // User sees only their bookings
    }

    // Display cancellation message if any
    String message = (String) request.getAttribute("message");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Bookings</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>

	<div class="container mt-4">
		<h2>Manage Bookings</h2>

		<% if (message != null) { %>
		<div class="alert alert-info">
			<%= message %>
		</div>
		<% } %>

		<% if (bookings.isEmpty()) { %>
		<!-- Show image when no bookings are found -->
		<div class="text-center">
			<img
				src="https://img.freepik.com/premium-photo/cup-coffee-with-sad-face-it_856223-2545.jpg"
				alt="No Bookings"
				style="max-width: 300px; width: 100%; height: auto; border-radius: 50%;">
			<p style="font-size: 18px; color: #555;">No active bookings
				available.</p>
		</div>

		<% } else { %>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Booking ID</th>
					<% if (isAdmin) { %>
					<th>User Email</th>
					<% } %>
					<th>Room Type</th>
					<th>Check-in</th>
					<th>Check-out</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<% for (Booking booking : bookings) { %>
				<tr>
					<td><%= booking.getId() %></td>
					<% if (isAdmin) { %>
					<td><%= booking.getMail() %></td>
					<% } %>
					<td><%= booking.getRoomType() %></td>
					<td><%= booking.getCheckIn() %></td>
					<td><%= booking.getCheckOut() %></td>
					<td>
						<form action="CancelBooking.jsp" method="get">
							<input type="hidden" name="bookingId"
								value="<%= booking.getId() %>"> <input type="hidden"
								name="mail" value="<%= booking.getMail() %>">
							<button type="submit" class="btn btn-danger">Cancel</button>
						</form>
					</td>
				</tr>
				<% } %>
			</tbody>
		</table>
		<% } %>

		<% if (isAdmin) { %>
		<a href="admin-dashboard.jsp" class="btn btn-secondary">Back to
			Dashboard</a>
		<% } else { %>
		<a href="Dashboard.jsp" class="btn btn-secondary">Back to
			Dashboard</a>
		<% } %>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
