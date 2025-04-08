<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.res.DTO.Users"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gallery - Resort Paradise</title>

    <!-- Bootstrap & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
            padding: 30px;
        }

        .gallery-container {
            max-width: 1200px;
            margin: auto;
            background: #fff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
        }

        .gallery-container h2 {
            font-size: 2rem;
            font-weight: 600;
            color: #2c3e50;
            margin-bottom: 30px;
        }

        .gallery-container p {
            font-size: 1.1rem;
            color: #7f8c8d;
            margin-bottom: 40px;
        }

        .gallery-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }

        .gallery-item {
            position: relative;
            overflow: hidden;
            border-radius: 15px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
            cursor: pointer;
        }

        .gallery-item img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            transition: transform 0.4s ease;
        }

        .gallery-item:hover img {
            transform: scale(1.1);
        }

        .gallery-item .caption {
            position: absolute;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.6);
            color: white;
            text-align: center;
            padding: 10px;
            font-size: 1.1rem;
            font-weight: 500;
            visibility: hidden;
        }

        .gallery-item:hover .caption {
            visibility: visible;
        }

        .back-btn {
            display: inline-block;
            background-color: #3498db;
            color: #fff;
            padding: 12px 20px;
            font-weight: bold;
            border-radius: 30px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .back-btn:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>

<%
    // Retrieve customer from session
    Users user = (Users) session.getAttribute("customer");

    // Redirect to login page if user is not logged in
    if (user == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>

<div class="gallery-container">
    <h2>Resort Gallery</h2>
    <p>Explore our beautiful resort through the gallery below.</p>

    <div class="gallery-grid">
        <!-- Gallery Item 1 -->
        <div class="gallery-item">
            <img src="https://hotelstclair.com/wp-content/uploads/2015/09/Executive-Ocean-VIew-Room-3.jpg" alt="Gallery Image 1">
            <div class="caption">Beach Front View</div>
        </div>

        <!-- Gallery Item 2 -->
        <div class="gallery-item">
            <img src="https://premieregypthotels.com/images/uploads/premier-le-r%C3%AAve-majestic-suite-2021313125432.jpg" alt="Gallery Image 2">
            <div class="caption">Luxury Rooms</div>
        </div>

        <!-- Gallery Item 3 -->
        <div class="gallery-item">
            <img src="https://content.themulia.com/proxy/https://cdn.prod.website-files.com/6624ff6a5db57a668993dd5e/66a1d86734ae657ecd701e9c_Mulia%20Villas%20-%20Presidential%20Villa%20-%20Bedroom%202.webp" alt="Gallery Image 3">
            <div class="caption">Dining Area</div>
        </div>

        <!-- Gallery Item 4 -->
        <div class="gallery-item">
            <img src="https://www.royaltonhideawayrivieracancun.com/images/slides/slide-19.jpg" alt="Gallery Image 4">
            <div class="caption">Resort Pool</div>
        </div>

        <!-- Gallery Item 5 -->
        <div class="gallery-item">
            <img src="https://theclifflipe.com/wp-content/uploads/2020/04/Jacuzzi-Suite-Ocean-View-5.jpeg" alt="Gallery Image 5">
            <div class="caption">Sunset View</div>
        </div>

        <!-- Gallery Item 6 -->
        <div class="gallery-item">
            <img src="https://cdn.webhotelier.net/photos/w=1920/plmr-jsmv/L418700.jpg" alt="Gallery Image 6">
            <div class="caption">Poolside Lounge</div>
        </div>
    </div>

    <br>
    <a href="Dashboard.jsp" class="back-btn">Back to Dashboard</a>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
