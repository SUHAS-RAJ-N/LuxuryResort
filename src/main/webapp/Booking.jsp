<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Your Stay</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .container {
            max-width: 600px;
        }
        .form-label {
            font-weight: bold;
        }
    </style>
</head>
<body>

<%
    String roomType = request.getParameter("roomType");
    String price = request.getParameter("price");
    String errorMessage = (String) request.getAttribute("error");
    String successMessage = (String) request.getAttribute("message");

    Integer bookingId = (Integer) request.getAttribute("bookingId");
    String bookingIdStr = (bookingId != null) ? String.valueOf(bookingId) : "";

    String userEmail = (String) session.getAttribute("mail"); // Get email from session
%>

<div class="container mt-5">
    <h2 class="mb-4"><i class="fas fa-bed"></i> Book Your Stay</h2>

    <% if (errorMessage != null) { %>
        <div class="alert alert-danger"><i class="fas fa-exclamation-circle"></i> <%= errorMessage %></div>
    <% } %>	

    <% if (successMessage != null) { %>
        <div class="alert alert-success"><i class="fas fa-check-circle"></i> <%= successMessage %>
            <% if (!bookingIdStr.isEmpty()) { %>
                <br> Booking ID: <b><%= bookingIdStr %></b>
            <% } %>
        </div>
    <% } %>

    <form action="BookingServlet" method="post">
        <div class="mb-3">
            <label class="form-label"><i class="fas fa-hotel"></i> Room Type</label>
            <input type="text" name="roomType" class="form-control" value="<%= (roomType != null) ? roomType : "" %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label"><i class="fas fa-dollar-sign"></i> Price per Night</label>
            <input type="text" name="price" class="form-control" value="<%= (price != null) ? price : "" %>" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label"><i class="fas fa-calendar-alt"></i> Check-in Date</label>
            <input type="date" id="checkIn" name="checkIn" class="form-control" required>
        </div>

        <div class="mb-3">
            <label class="form-label"><i class="fas fa-calendar-check"></i> Check-out Date</label>
            <input type="date" id="checkOut" name="checkOut" class="form-control" required>
        </div>

        <!-- Added Number of Nights Field -->
        <div class="mb-3">
            <label class="form-label"><i class="fas fa-calendar-day"></i> Number of Nights</label>
            <input type="text" id="numNights" class="form-control" value="0" readonly>
        </div>

        <div class="mb-3">
            <label class="form-label"><i class="fas fa-credit-card"></i> Total Price</label>
            <input type="text" id="totalPrice" class="form-control" value="0.00" readonly>
        </div>

        <button type="submit" class="btn btn-primary"><i class="fas fa-check"></i> Confirm Booking</button>
    </form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        let today = new Date().toISOString().split("T")[0];
        let checkIn = document.getElementById("checkIn");
        let checkOut = document.getElementById("checkOut");
        let numNights = document.getElementById("numNights");
        let totalPrice = document.getElementById("totalPrice");
        let pricePerNight = parseFloat("<%= (price != null) ? price : "0" %>");

        checkIn.setAttribute("min", today);
        checkOut.setAttribute("min", today);

        checkIn.addEventListener("change", function () {
            checkOut.setAttribute("min", this.value);
            calculatePriceAndNights();
        });

        checkOut.addEventListener("change", function () {
            calculatePriceAndNights();
        });

        function calculatePriceAndNights() {
            let checkInDate = new Date(checkIn.value);
            let checkOutDate = new Date(checkOut.value);

            if (checkInDate && checkOutDate && checkOutDate > checkInDate) {
                let nights = (checkOutDate - checkInDate) / (1000 * 3600 * 24);
                let total = nights * pricePerNight;

                // Apply discount if more than 1 night
                if (nights > 1) {
                    let discount = total * 0.10; // 10% discount
                    total -= discount;
                    totalPrice.value = total.toFixed(2) + " (10% Discount Applied)";
                } else {
                    totalPrice.value = total.toFixed(2);
                }

                numNights.value = nights;
            } else {
                numNights.value = "0";
                totalPrice.value = "0.00";
            }
        }
    });
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
