<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.res.DAO.RoomsDAOImplementation, com.res.DTO.Rooms" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Rooms</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>Manage Rooms</h2>
        <a href="addRoom.jsp" class="btn btn-primary mb-3">➕ Add New Room</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Type</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Image</th>
                    <th>Availability</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    RoomsDAOImplementation roomDAO = new RoomsDAOImplementation();
                    List<Rooms> roomList = roomDAO.getAllRooms();
                    for (Rooms room : roomList) {
                %>
                <tr>
                    <td><%= room.getId() %></td>
                    <td><%= room.getName() %></td>
                    <td><%= room.getType() %></td>
                    <td>$<%= room.getPrice() %></td>
                    <td><%= room.getDescription() %></td>
                    <td><img src="<%= room.getImageUrl() %>" width="100"></td>
                    <td><%= room.isAvailable() ? "Available" : "Not Available" %></td>
                    <td>
                        <a href="EditRoomServlet?id=<%= room.getId() %>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="DeleteRoomServlet?id=<%= room.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?');">Delete</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
