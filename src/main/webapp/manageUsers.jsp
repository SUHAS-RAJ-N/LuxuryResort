<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.res.DAO.UsersDAOImplementation" %>
<%@ page import="com.res.DAO.UsersDAO" %>
<%@ page import="com.res.DTO.Users" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("role") == null || !session1.getAttribute("role").equals("admin")) {
        response.sendRedirect("Login.jsp");
        return;
    }

    UsersDAO usersDAO = new UsersDAOImplementation();
    ArrayList<Users> userList = usersDAO.getAllUsers();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Manage Users</h2>

    <table class="table table-bordered table-hover">
       <thead class="table-dark">
    <tr>
        <th>User ID</th>
        <th>Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Registration Date</th> <!-- Add registration date column -->
        <th>Total Bookings</th>     <!-- Add total bookings column -->
    </tr>
</thead>
<tbody>
<%
    for (Users u : userList) {
%>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getName() %></td>
        <td><%= u.getMail() %></td>
        <td><%= u.getPhone() %></td>
        <td><%= u.getDate() %></td> <!-- Display registration date -->
        <td><%= u.getTotalBookings() %></td>     <!-- Display total bookings -->
        <td><a href="deleteUser?id=<%= u.getId() %>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this user?');">Delete</a></td>
    </tr>
<%
    }
%>
</tbody>

    </table>

    <a href="admin-dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
