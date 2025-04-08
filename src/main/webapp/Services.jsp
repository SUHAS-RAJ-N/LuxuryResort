<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Our Services - Resort Paradise</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
	rel="stylesheet">

<style>
body {
	font-family: 'Poppins', sans-serif;
	background-color: #f8f9fa;
}

.service-card {
	transition: transform 0.3s ease, box-shadow 0.3s ease;
	border-radius: 15px;
	overflow: hidden;
}

.service-card:hover {
	transform: scale(1.05);
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.service-card img {
	width: 100%;
	height: 100%;
	object-fit: cover;
}

.card-body {
	text-align: center;
}

.card-title {
	font-size: 1.25rem;
	font-weight: bold;
	color: #2c3e50;
}

.card-text {
	font-size: 1rem;
	color: #7f8c8d;
}

footer {
	background-color: #343a40;
	color: white;
	padding: 20px 0;
}

footer p {
	margin-bottom: 0;
}

.back-btn {
	display: inline-block;
	padding: 10px 20px;
	font-size: 1rem;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	text-decoration: none;
	transition: background-color 0.3s ease;
}

.back-btn:hover {
	background-color: #0056b3;
}

</style>
</head>
<body>

	<!-- Navigation Bar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="Index.jsp">Resort Paradise</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
		</div>
	</nav>

	<!-- Back Button -->
	<div class="container my-4 text-end">
		<a href="Dashboard.jsp" class="back-btn"><i class="fas fa-arrow-left"></i> Back to Dashboard</a>
	</div>

	<!-- Services Section -->
	<div class="container my-5">
		<h2 class="text-center mb-4">Our Premium Services</h2>
		<div class="row g-4"> <!-- Added spacing between columns -->
			<!-- Service 1: Spa & Wellness -->
			<div class="col-md-4 d-flex align-items-stretch">
				<div class="card service-card">
					<img src="https://www.travelscout24.de/wp-content/uploads/sites/14/Wellness-Spa.jpg" class="card-img-top" alt="Spa & Wellness">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-spa"></i> Spa & Wellness
						</h5>
						<p class="card-text">Relax and rejuvenate with our world-class spa treatments.</p>
					</div>
				</div>
			</div>

			<!-- Service 2: Fine Dining -->
			<div class="col-md-4 d-flex align-items-stretch">
				<div class="card service-card">
					<img src="https://images.unsplash.com/photo-1528605248644-14dd04022da1?ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=80" class="card-img-top" alt="Fine Dining">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-utensils"></i> Fine Dining
						</h5>
						<p class="card-text">Enjoy gourmet meals crafted by our award-winning chefs.</p>
					</div>
				</div>
			</div>

			<!-- Service 3: Adventure Sports -->
			<div class="col-md-4 d-flex align-items-stretch">
				<div class="card service-card">
					<img src="https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-1.2.1&auto=format&fit=crop&w=600&q=80" class="card-img-top" alt="Adventure Sports">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-water"></i> Adventure Sports
						</h5>
						<p class="card-text">Experience thrilling water sports and outdoor adventures.</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer class="text-center">
		<p>&copy; 2025 Resort Paradise. All Rights Reserved.</p>
	</footer>

	<!-- Bootstrap JS -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
