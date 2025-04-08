<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.res.DTO.Users"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Resort Paradise</title>

    <!-- Bootstrap & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            padding: 30px;
            color: #495057;
        }

        .dashboard-container {
            max-width: 1100px;
            margin: auto;
            background: #ffffff;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 12px 24px rgba(0, 0, 0, 0.1);
        }

        .logout-btn {
            text-align: right;
            font-size: 1.2em;
        }

        .logout-btn a {
            color: #e74c3c;
            font-weight: bold;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .logout-btn a:hover {
            color: #c0392b;
        }

        h2 {
            font-size: 2rem;
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 10px;
        }

        .dashboard-links {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 30px;
            margin-top: 40px;
        }

        .card {
            background-color: #f9fafb;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            text-align: center;
            transition: transform 0.3s ease, box-shadow 0.3s ease, background-color 0.3s ease;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.15);
            background-color: #ecf0f1;
        }

        .card i {
            font-size: 3rem;
            color: #3498db;
            margin-bottom: 20px;
        }

        .card p {
            margin-top: 10px;
            font-size: 1.1rem;
            color: #2c3e50;
        }

        .card a {
            color: #3498db;
            font-weight: 600;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .card a:hover {
            color: #2980b9;
        }

        .card .info {
            margin-top: 15px;
            color: #7f8c8d;
            font-size: 0.9rem;
        }
    </style>
</head>
<body>

<%
    // Retrieve customer from session
    Users user = (Users) session.getAttribute("customer");

    // Redirect to login page if user is not logged in
    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>

<div class="dashboard-container">
    <div class="logout-btn">
        <a href="Logout"><i class="fas fa-sign-out-alt"></i> Logout</a>
    </div>

    <h2>Welcome back, <%= user.getName() %>! 🎉</h2>
    <p>Access all your resort features and enjoy the convenience of managing bookings, viewing services, and more.</p>

    <hr>
    <h4>Dashboard Overview</h4>

    <div class="dashboard-links">
        <!-- Manage Bookings Link -->
        <div class="card">
            <i class="fas fa-tachometer-alt"></i>
            <p><a href="manageBookings.jsp">Manage Bookings</a></p>
            <div class="info">Review and modify your bookings.</div>
        </div>

        <!-- View Services Link -->
        <div class="card">
            <i class="fas fa-concierge-bell"></i>
            <p><a href="Services.jsp">View Services</a></p>
            <div class="info">Explore resort services and offerings.</div>
        </div>

        <!-- Customer Support Link -->
        <div class="card">
            <i class="fas fa-headset"></i>
            <p><a href="contact.jsp">Customer Support</a></p>
            <div class="info">Contact support for assistance.</div>
        </div>

        <!-- View Gallery Link -->
        <div class="card">
            <i class="fas fa-images"></i>
            <p><a href="gallery.jsp">View Gallery</a></p>
            <div class="info">Browse beautiful images of the resort.</div>
        </div>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
