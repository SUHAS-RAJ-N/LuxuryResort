<%@ page import="java.util.List, com.res.DTO.Feedback" %>
<html>
<head>
    <title>Manage Feedback</title>
</head>
<body>
    <h2>Customer Feedback</h2>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Room ID</th>
            <th>Rating</th>
            <th>Comment</th>
            <th>Feedback Date</th>
        </tr>
        <%
            List<Feedback> feedbackList = (List<Feedback>) request.getAttribute("feedbackList");
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
