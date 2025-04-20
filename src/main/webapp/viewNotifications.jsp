<%@ page import="java.util.List, com.res.DTO.Notification" %>
<html>
<head>
    <title>View Notifications</title>
</head>
<body>
    <h2>User Notifications</h2>
    <table border="1">
        <tr>
            <th>Notification ID</th>
            <th>User ID</th>
            <th>Message</th>
            <th>Status</th>
            <th>Notification Date</th>
        </tr>
        <%
            List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");
            for (Notification notification : notifications) {
        %>
        <tr>
            <td><%= notification.getNotificationId() %></td>
            <td><%= notification.getUserId() %></td>
            <td><%= notification.getMessage() %></td>
            <td><%= notification.getStatus() %></td>
            <td><%= notification.getNotificationDate() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
