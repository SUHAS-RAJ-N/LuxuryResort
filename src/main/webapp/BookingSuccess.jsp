<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Booking Successful</title>
<link rel="stylesheet" href="styles.css">
<!-- Link your CSS file -->
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	text-align: center;
	padding: 50px;
}

.container {
	max-width: 500px;
	background: white;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin: auto;
}

h2 {
	color: #27ae60;
}

p {
	font-size: 16px;
	margin: 10px 0;
}

.btn {
	display: inline-block;
	padding: 10px 20px;
	margin-top: 20px;
	background-color: #27ae60;
	color: white;
	text-decoration: none;
	border-radius: 5px;
}

.btn:hover {
	background-color: #219150;
}
</style>
</head>
<body>
	<div class="container">
		<h2>🎉 Booking Confirmed!</h2>
		<p>Thank you for booking with us. Your reservation details are as
			follows:</p>

		<%
            String bookingId = request.getParameter("bookingId"); // Retrieve from URL query parameter
            HttpSession sessionObj = request.getSession(false);
            String mail = (sessionObj != null) ? (String) sessionObj.getAttribute("mail") : "Not Available";
        %>
		<%
    String roomType = request.getParameter("roomType");
    String imagePath = "images/" + roomType.toLowerCase().replace(" ", "") + ".jpg";
%>


		<p>
			<b>Booking ID:</b>
			<%= bookingId %></p>
		<p>
			<b>Registered Email:</b>
			<%= mail %></p>
		<p>You will receive a confirmation email shortly with all the
			details.</p>

		<a href="Dashboard.jsp" class="btn">Go to Dashboard</a>
	</div>
</body>
</html>
