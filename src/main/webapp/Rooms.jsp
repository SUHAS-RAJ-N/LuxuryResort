<%@ page import="java.util.List" %>
<%@ page import="com.res.DTO.Rooms" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Rooms List</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <h2>Available Rooms</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Price</th>
            <th>Description</th>
            <th>Image</th>
            <th>Availability</th>
        </tr>
        <%
            List<Rooms> rooms = (List<Rooms>) request.getAttribute("rooms");
            if (rooms != null) {
                for (Rooms room : rooms) {
        %>
        <tr>
            <td><%= room.getId() %></td>
            <td><%= room.getName() %></td>
            <td><%= room.getType() %></td>
            <td>$<%= room.getPrice() %></td>
            <td><%= room.getDescription() %></td>
            <td><img src="<%= room.getImageUrl() %>" width="100"></td>
            <td><%= room.isAvailable() ? "Available" : "Booked" %></td>
        </tr>
        <% 
                }
            }
        %>
    </table>

    <h2>Add New Room</h2>
    <form action="rooms" method="post">
        <input type="hidden" name="action" value="add">
        Name: <input type="text" name="name" required><br>
        Type: <input type="text" name="type" required><br>
        Price: <input type="number" name="price" step="0.01" required><br>
        Description: <input type="text" name="description"><br>
        Image URL: <input type="text" name="imageUrl"><br>
        Availability: <input type="checkbox" name="isAvailable" value="true"><br>
        <input type="submit" value="Add Room">
    </form>
</body>
</html>
