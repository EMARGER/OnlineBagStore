
<%@page import="com.bagstore.dto.ProductDTO"%>
<%@page import="com.bagstore.dto.CategoryDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Homepage</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
.search-container input {
	padding: 10px 20px;
	border: none;
	border-radius: 25px;
	background-color: #28a745;
	color: white;
	cursor: pointer;
	
	
}
.profile-img {
    width: 35px; /* Image size chhota rakho */
    height: 35px;
    border-radius: 50%; /* Circle banane ke liye */
    object-fit: cover; /* Image ka shape maintain karega */
    margin-left: 15px; /* Links aur image ke beech space */
    border: 2px solid white; /* Thoda white border dene ke liye */
    background-color: white
}
.nav-link:hover{
	color: yellow;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#">🛒 MyShop</a>
			<div class="d-flex align-items-center">
				<a href="login.jsp" class="nav-link">Login</a> <a href="signUp.jsp"
					class="nav-link">Register</a> <a href="login.jsp" class="nav-link">Orders</a>

				<!-- Profile Image -->
				<a href="login.jsp" ><img src="img/profileLogo.png" alt="Profile" class="profile-img"></a>
			</div>
		</div>
	</nav>


	<!-- Banner Slider -->
	<div id="bannerCarousel" class="carousel slide mt-5"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="img/banner1.png" class="d-block w-100 bannerHeight"
					alt="Banner 1">
			</div>
			<div class="carousel-item">
				<img src="img/banner2.jpg" class="d-block w-100 bannerHeight"
					alt="Banner 2">
			</div>
			<div class="carousel-item">
				<img src="img/banner3.jpg" class="d-block w-100 bannerHeight"
					alt="Banner 3">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#bannerCarousel" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#bannerCarousel" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
		</button>
	</div>

	<!-- Search by Category -->
	<div class="search-container">
		<form action="firstHome" method="get"
			style="width: 100%; max-width: 800px;">
			<input type="hidden" name="task" value="findAll"> <select
				id="categorySelect" name="category"
				style="width: 100% !important; max-width: 600px; padding: 10px; border-radius: 25px; outline: none; border: 2px solid #ccc;">
				<option value="Comman Bag">Comman Bag</option>
				<option value="Tote Bag">Tote Bag</option>
				<option value="Backpack">Backpack</option>
				<option value="Bucket Bag">Bucket Bag</option>
				<option value="Handbags">Handbags</option>
				<option value="Wallet">Wallet</option>
			</select> <input type="submit" name="search" value="Search">
		</form>
	</div>
	<!-- Product Cards -->
	<%
	if (request.getAttribute("productDTOs") != null) {
		List<ProductDTO> productDTOs = (List) request.getAttribute("productDTOs");
		for (ProductDTO productDTO : productDTOs) {
	%>
	<div class="container d-flex justify-content-center mt-4">

		<div class="product-card">
			<a href="login.jsp" style="width: 50%; height: 100%; display: block;">
				<img src="<%=productDTO.getImg() %>" alt="Product Image">
			</a>
			<div class="card-body">
				<h5 class="card-title"><%=productDTO.getName()%></h5>
				<p class="card-text"><%=productDTO.getDescription()%></p>
				<h3 class="card-title text-success"><%=productDTO.getPrice()%></h3>
				<div>
					<a href="login.jsp"><button class="btn btn-primary">Buy
							Now</button></a>
				</div>
			</div>
		</div>

	</div>
	<%
	}
	}
	%>



	<div class="container d-flex justify-content-center mt-4">
		<div class="product-card">
			<a href="login.jsp"
				style="width: 50%; height: 100%; display: block;"> <img
				src="img/bag1.avif" alt="Product Image">
			</a>
			<div class="card-body">
				<h5 class="card-title">Product Name</h5>
				<p class="card-text">This is a high-quality product with great
					features.</p>
				<div>
					<a href="login.jsp"><button class="btn btn-primary">Buy
							Now</button></a>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer>
		<p>&copy; 2025 MyShop. All rights reserved.</p>
		<div>
			<a href="#">Privacy Policy</a> <a href="#">Terms of Service</a> <a
				href="#">Contact Us</a>
		</div>
	</footer>

</body>
</html>