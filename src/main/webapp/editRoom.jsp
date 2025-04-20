<%@ page import="com.res.DAO.RoomsDAOImplementation, com.res.DTO.Rooms" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Edit Room</title>
</head>
<body>
    <h2>Edit Room</h2>
    
    <form action="EditRoomServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%= request.getAttribute("roomId") %>">
        
        <label for="name">Room Name:</label>
        <input type="text" id="name" name="name" value="<%= request.getAttribute("roomName") %>" required><br><br>

        <label for="type">Room Type:</label>
        <input type="text" id="type" name="type" value="<%= request.getAttribute("roomType") %>" required><br><br>

        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="<%= request.getAttribute("roomPrice") %>" required><br><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required><%= request.getAttribute("roomDescription") %></textarea><br><br>

        <label for="image">Room Image:</label>
        <input type="file" id="image" name="image" accept="image/*"><br><br>

        <button type="submit">Save Changes</button>
    </form>
</body>
</html>
