<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.res.DTO.Notification" %>

<html>
<body>
    <h2>Notifications</h2>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Message</th>
            <th>Status</th>
            <th>Date</th>
        </tr>
        <% 
            ArrayList<Notification> notificationList = (ArrayList<Notification>) request.getAttribute("notificationList");
            for (Notification notification : notificationList) {
        %>
        <tr>
            <td><%= notification.getUserId() %></td>
            <td><%= notification.getMessage() %></td>
            <td><%= notification.getStatus() %></td>
            <td><%= notification.getNotificationDate() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
