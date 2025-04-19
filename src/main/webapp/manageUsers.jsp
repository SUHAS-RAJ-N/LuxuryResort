<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.res.DTO.Users" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    ArrayList<Users> users = (ArrayList<Users>) request.getAttribute("users");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2 class="text-center">Manage Users</h2>

        <% if (users == null || users.isEmpty()) { %>
            <p class="text-center">No users found.</p>
        <% } else { %>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>Email</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th>Registration Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Users user : users) { %>
                        <tr>
                            <td><%= user.getId() %></td>
                            <td><%= user.getMail() %></td>
                            <td><%= user.getName() %></td>
                            <td><%= user.getPhone() %></td>
                            <td><%= user.getDate() %></td>
                            <td>
                                <a href="editUser.jsp?userId=<%= user.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                                <a href="deleteUser?userId=<%= user.getId() %>" class="btn btn-danger btn-sm">Delete</a>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } %>
        <a href="admin-dashboard.jsp" class="btn btn-primary">Back to Dashboard</a>
    </div>
</body>
</html>
