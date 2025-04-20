<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Booking Reports</title>
</head>
<body>
    <h2>Booking Reports</h2>
    <table border="1">
        <tr><th>Room Type</th><th>Total Bookings</th></tr>
        <%
            Map<String, Integer> stats = (Map<String, Integer>) request.getAttribute("stats");
            if (stats != null && !stats.isEmpty()) {
                for (Map.Entry<String, Integer> entry : stats.entrySet()) {
        %>
            <tr>
                <td><%= entry.getKey() %></td>
                <td><%= entry.getValue() %></td>
            </tr>
        <% 
                }
            } else {
        %>
            <tr><td colspan="2">No data available</td></tr>
        <% 
            }
        %>
    </table>
</body>
</html>
