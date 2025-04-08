<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.res.DTO.Feedback" %>

<html>
<body>
    <h2>Feedback List</h2>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Room ID</th>
            <th>Rating</th>
            <th>Comment</th>
            <th>Date</th>
        </tr>
        <% 
            ArrayList<Feedback> feedbackList = (ArrayList<Feedback>) request.getAttribute("feedbackList");
            for (Feedback feedback : feedbackList) {
        %>
        <tr>
            <td><%= feedback.getUserId() %></td>
            <td><%= feedback.getRoomId() %></td>
            <td><%= feedback.getRating() %></td>
            <td><%= feedback.getComment() %></td>
            <td><%= feedback.getFeedbackDate() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
