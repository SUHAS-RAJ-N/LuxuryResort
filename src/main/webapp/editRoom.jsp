<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Edit Room</title>
</head>
<body>
    <h2>Edit Room</h2>
    <form action="UpdateRoomServlet" method="post">
        <input type="hidden" name="id" value="<%= request.getAttribute("roomId") %>">
        Name: <input type="text" name="name" value="<%= request.getAttribute("name") %>"><br>
        Type: <input type="text" name="type" value="<%= request.getAttribute("type") %>"><br>
        Price: <input type="text" name="price" value="<%= request.getAttribute("price") %>"><br>
        Description: <input type="text" name="description" value="<%= request.getAttribute("description") %>"><br>
        Image URL: <input type="text" name="imageUrl" value="<%= request.getAttribute("imageUrl") %>"><br>
        Availability:
        <select name="availability">
            <option value="available" <%= "available".equals(request.getAttribute("availability")) ? "selected" : "" %>>Available</option>
            <option value="not available" <%= "not available".equals(request.getAttribute("availability")) ? "selected" : "" %>>Not Available</option>
        </select><br>
        <button type="submit">Update Room</button>
    </form>
</body>
</html>
