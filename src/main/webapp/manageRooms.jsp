<%@ page import="java.util.List, com.res.DAO.RoomsDAOImplementation, com.res.DTO.Rooms" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Manage Rooms</title>
</head>
<body>
    <h2>Manage Rooms</h2>
    
    <a href="addRoom.jsp">➕ Add New Room</a> <br><br>

    <table border="1">
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
                <a href="EditRoomServlet?id=<%= room.getId() %>">✏ Edit</a> |
                <a href="DeleteRoomServlet?id=<%= room.getId() %>" onclick="return confirm('Are you sure?');">🗑 Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
