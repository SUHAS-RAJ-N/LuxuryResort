<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    // Validate Admin Session
    HttpSession session1 = request.getSession(false);
    if (session1 == null || session1.getAttribute("userId") == null) {
        response.sendRedirect("Login.jsp");
        return;
    }

    Integer userId = (Integer) session1.getAttribute("userId");
    if (userId == null || userId != 1) {
        response.sendRedirect("Index.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Poppins', sans-serif;
        }
        .sidebar {
            height: 100vh;
            background-color: #343a40;
            padding-top: 20px;
            color: white;
            position: sticky;
            top: 0;
        }
        .sidebar a {
            text-decoration: none;
            color: white;
            display: block;
            padding: 12px 20px;
            font-size: 1rem;
            transition: background 0.3s ease;
        }
        .sidebar a:hover {
            background-color: #495057;
        }
        .dashboard-content {
            padding: 20px;
        }
        .card:hover {
            transform: translateY(-5px);
            transition: transform 0.3s ease;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <!-- Sidebar -->
        <nav class="col-md-3 col-lg-2 d-md-block sidebar">
            <h3 class="text-center">Admin Panel</h3>
            <a href="admin-dashboard.jsp"><i class="fas fa-home"></i> Dashboard</a>
            <a href="manageRooms.jsp"><i class="fas fa-bed"></i> Manage Rooms</a>
            <a href="manageBookings.jsp"><i class="fas fa-calendar-check"></i> Manage Bookings</a>
            <a href="manageUsers.jsp"><i class="fas fa-users"></i> Manage Users</a>
            <a href="viewReports.jsp"><i class="fas fa-chart-line"></i> View Reports</a>
            <a href="notifications.jsp"><i class="fas fa-bell"></i> Notifications</a>
            <a href="feedback.jsp"><i class="fas fa-comment-alt"></i> Customer Feedback</a>
            <a href="Logout" onclick="return confirmLogout();"><i class="fas fa-sign-out-alt"></i> Logout</a>
        </nav>

        <!-- Main Content -->
        <main class="col-md-9 col-lg-10 dashboard-content">
            <h2>Welcome, Admin</h2>
            
            <div class="row mt-4">
                <div class="col-md-4">
                    <div class="card bg-primary text-white">
                        <div class="card-body">
                            <h5 class="card-title">Manage Rooms</h5>
                            <p>Add, edit, and delete resort rooms.</p>
                            <a href="manageRooms.jsp" class="btn btn-light">Go to Rooms</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card bg-success text-white">
                        <div class="card-body">
                            <h5 class="card-title">Manage Bookings</h5>
                            <p>View and update customer bookings.</p>
                            <a href="manageBookings.jsp" class="btn btn-light">Go to Bookings</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card bg-warning text-dark">
                        <div class="card-body">
                            <h5 class="card-title">Manage Users</h5>
                            <p>View and manage user accounts.</p>
                            <a href="manageUsers.jsp" class="btn btn-dark">Go to Users</a>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="row mt-4">
                <div class="col-md-4">
                    <div class="card bg-info text-white">
                        <div class="card-body">
                            <h5 class="card-title">View Reports</h5>
                            <p>Analyze booking and revenue reports.</p>
                            <a href="viewReports.jsp" class="btn btn-light">Go to Reports</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="card bg-danger text-white">
                        <div class="card-body">
                            <h5 class="card-title">Notifications</h5>
                            <p>Manage and send notifications to users.</p>
                            <a href="notifications.jsp" class="btn btn-light">Go to Notifications</a>
                        </div>
                    </div>
                </div>
                
                <div class="col-md-4">
                    <div class="card bg-secondary text-white">
                        <div class="card-body">
                            <h5 class="card-title">Customer Feedback</h5>
                            <p>View and respond to customer reviews.</p>
                            <a href="feedback.jsp" class="btn btn-light">View Feedback</a>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<!-- FontAwesome -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/js/all.min.js"></script>

<script>
function confirmLogout() {
    return confirm("Are you sure you want to log out?");
}
</script>

</body>
</html>
