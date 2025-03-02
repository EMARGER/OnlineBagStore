<%@page import="com.bagstore.dto.UserDTO"%>
<%@page import="com.bagstore.dto.ProductDTO"%>
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

.link {
	background: none;
	border: none;
	color: white
}

.profile-fram {
	width: 100%;
	height: 600px;
	margin-bottom: 0px;
	margin-top: 0px;
	border: none;
}

.profile-btn {
	width: 45px; /* Button ka size */
	height: 45px;
	border-radius: 50%; /* Circular shape */
	border: none;
	padding: 0;
	overflow: hidden; /* Extra image part hide karega */
	display: flex;
	align-items: center;
	justify-content: center;
	background-color: white; /* Background color */
	border: 2px solid lightgreen; /* Light border */
	cursor: pointer;
}

.profile-img {
	width: 100%; /* Image pura fill kare */
	height: 100%;
	border-radius: 50%; /* Circular image */
	object-fit: cover;
}

.profile-btn:hover {
	border-color: #007bff; /* Blue border on hover */
	transform: scale(1.1);
	transition: 0.3s ease-in-out;
}
.logo-img{
	width: 180px; 
	height: 40px
}

</style>

</head>
<body>


	<%
	if (session.getAttribute("userId") == null) {
		response.sendRedirect("login.jsp");
	}
	%>


	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark fixed-top">
		<div class="container">
			<a class="navbar-brand" href="#"><img alt="" src="img/logo1.png" class="logo-img"></a>
			<div class="d-flex align-items-center">



				<form action="mainHome" method="get">
					<input type="hidden" name="task" value="findProductByDefault">
					<input type="submit" name="Home" value="Home" class="link"
						style="color: lightgreen;">
				</form>
				<form action="cart" method="get">
					<input type="hidden" name="task" value="findAll"> <input
						type="hidden" name="id"
						value="<%=session.getAttribute("userId")%>"> <input
						type="submit" name="cart" value="Cart" class="link">
				</form>

				<form action="wishList" method="get">
					<input type="hidden" name="task" value="findAll"> <input
						type="hidden" name="id"
						value="<%=session.getAttribute("userId")%>"> <input
						type="submit" name="wishList" value="WishList" class="link">
				</form>

				<form action="history" method="get">
					<input type="hidden" name="task" value="findAll"> <input
						type="hidden" name="userId"
						value="<%=session.getAttribute("userId")%>"> <input
						type="submit" name="orderNow" value="Orders" class="link">
				</form>

				<form action="mainHome" method="get">
					<input type="hidden" name="task" value="logout"> <input
						type="submit" name="logout" value="Logout" class="link">

				</form>

				<!-- Profile Image -->
				<button class="profile-btn" data-bs-toggle="modal"
					data-bs-target="#profileModal">
					<img src="img/<%=session.getAttribute("userImg")%>"
						alt="Profile Picture" class="profile-img">
				</button>
				
			</div>
		</div>
	</nav>

<div class="modal fade" id="profileModal" tabindex="-1"
					aria-hidden="true">
					<div class="modal-dialog modal-lg">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title">Profile</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<iframe src="profile.jsp" class="profile-fram"></iframe>
							</div>
						</div>
					</div>
				</div>
	<!-- Banner Slider -->
	<div id="bannerCarousel" class="carousel slide mt-5"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="img/banner6.png" class="d-block w-100 bannerHeight"
					alt="Banner 1">
			</div>
			<div class="carousel-item">
				<img src="img/banner2.jpg" class="d-block w-100 bannerHeight"
					alt="Banner 2">
			</div>
			<div class="carousel-item">
				<img src="img/banner5.jpg" class="d-block w-100 bannerHeight"
					alt="Banner 3">
			</div>
			<div class="carousel-item">
				<img src="img/banner7.jpg" class="d-block w-100 bannerHeight"
					alt="Banner 4">
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
		<form action="mainHome" method="get"
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
				<img src="<%=productDTO.getImg()%>" alt="Product Image">
			</a>
			<div class="card-body">
				<h5 class="card-title"><%=productDTO.getName()%></h5>
				<p class="card-text"><%=productDTO.getDescription()%></p>
				<h3 class="card-title text-success"><%=productDTO.getPrice()%></h3>
				<!-- Wishlist & Add to Cart in One Row -->
				<div class="d-flex justify-content-between">
					<form action="mainHome" method="post">

						<input type="hidden" name="userId"
							value="<%=session.getAttribute("userId")%>"> <input
							type="hidden" name="productId" value="<%=productDTO.getId()%>">
						<input type="hidden" name="task" value="saveToWishlist">
						<button type="submit" class="btn btn-danger">❤️ Wishlist</button>

					</form>

					<form action="mainHome" method="post">
						<input type="hidden" name="userId"
							value="<%=session.getAttribute("userId")%>"> <input
							type="hidden" name="productId" value="<%=productDTO.getId()%>">
						<input type="hidden" name="task" value="addToCart">
						<button type="submit" class="btn btn-success">🛒 Add to
							Cart</button>
					</form>
				</div>

				<!-- Buy Now Button Below -->
				<div class="mt-2">
					<form action="order" method="get">
						<input type="hidden" name="productId"
							value="<%=productDTO.getId()%>"> <input type="hidden"
							name="task" value="orderSingleProduct">
						<button type="submit" class="btn btn-primary w-100">🛍
							Buy Now</button>
					</form>


				</div>
			</div>
		</div>

	</div>
	<%
	}
	}
	%>

	<!-- Footer -->
	<footer>
		<p>&copy; 2025 MyShop. All rights reserved.</p>
		<div>
			<a href="https://www.linkedin.com/in/goutam-dogayan-113a42255"
				target="blank">Goutam Dogayan</a> <a
				href="https://www.linkedin.com/in/krishnaprajapati057/"
				target="blank">Krishna Kumal Prajapati</a> <a
				href="https://www.linkedin.com/in/atul-patel-200a3a303/"
				target="blank">Atul Patel</a>
		</div>
	</footer>

</body>
</html>