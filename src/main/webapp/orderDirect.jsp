<%@page import="com.bagstore.dto.ProductDTO"%>
<%@page import="com.bagstore.dto.CartDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.bagstore.service.ProductService"%>
<%@page import="com.bagstore.dao.ProductDAO"%>
<%@page import="com.bagstore.util.DBUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order Now</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
.order-container {
	margin: 50px auto;
	max-width: 800px;
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	padding: 20px;
}

.product-card {
	display: flex;
	align-items: center;
	margin-bottom: 20px;
	border-bottom: 1px solid #ddd;
	padding-bottom: 15px;
}

.product-card img {
	width: 100px;
	height: 100px;
	object-fit: contain;
	border-radius: 5px;
}

.product-card .details {
	margin-left: 20px;
	flex: 1;
}

.form-section {
	margin-top: 30px;
}

.btn-primary {
	background-color: #28a745;
	border: none;
}

.btn-primary:hover {
	background-color: #218838;
}

.btn-secondary {
	background-color: #6c757d;
}

.btn-secondary:hover {
	background-color: #5a6268;
}

.link{
		background: none;
		border: none;
		color: white
	}
	.profile-fram {
	    width: 100%;
	    height: 600px; 
	    margin-bottom: 0px;
        margin-top:0px;
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
    
</style>
</head>
<body>
	<%
	if (session.getAttribute("userId") == null) {
		response.sendRedirect("login.jsp");
	}
	%>

	<nav class="navbar navbar-expand-lg navbar-dark ">
		<div class="container">
			<a class="navbar-brand" href="#">🛒 MyShop</a>
			<div class="d-flex align-items-center">
				
				<form action="mainHome" method="get">	
					<input type="hidden" name="task" value="findProductByDefault">
					<input type="submit" name="Home" value="Home" class="link">
				</form>		
				
				<form action="cart" method="get">	
					<input type="hidden" name="task" value="findAll">
					<input type="hidden" name="id" value="<%=session.getAttribute("userId")%>">
					<input type="submit" name="cart" value="Cart" class="link">
				</form>
				
				<form action="wishList" method="get">
					<input type="hidden" name="task" value="findAll">
					<input type="hidden" name="id" value="<%=session.getAttribute("userId")%>">
					<input type="submit" name="wishList" value="WishList" class="link">
				</form>
				
				<form action="history" method="get">
					<input type="hidden" name="task" value="findAll">
					<input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>">
					<input type="submit" name="orderNow" value="Orders" class="link">
				</form>
				
				<form action="mainHome" method="get">
					<input type="hidden" name="task" value="logout">
					<input type="submit" name="logout" value="Logout" class="link">
					
				</form>
				
				<!-- Profile Image -->
				<button class="profile-btn" data-bs-toggle="modal" data-bs-target="#profileModal">
					<img src="img/<%=session.getAttribute("userImg")%>" alt="Profile Picture" class="profile-img">
				</button>
				<div class="modal fade" id="profileModal" tabindex="-1" aria-hidden="true">
				    <div class="modal-dialog modal-lg">
				        <div class="modal-content">
				            <div class="modal-header">
				                <h5 class="modal-title">Profile</h5>
				                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
				            </div>
				            <div class="modal-body">
				                <iframe src="profile.jsp" class="profile-fram"></iframe>
				            </div>
				        </div>
				    </div>
				</div>
			</div>
		</div>
	</nav>
	<!-- Order Section -->


	<div class="container order-container">
		<h2 class="text-center mb-4">Order Now</h2>

		<!-- Product Summary -->
		<h5>Product Details:</h5>

		<%
		

		if (request.getAttribute("productDTO") != null) {
			ProductDTO productDTO = (ProductDTO)request.getAttribute("productDTO");
			double subTotal = productDTO.getPrice();
			double tax = 10;
			
			
		%>
		<div class="product-card">
			<img src="<%=productDTO.getImg()%>" alt="Product Image">
			<div class="details">
				<h6><%=productDTO.getName()%></h6>
				<p class="mb-1"><%=productDTO.getPrice()%></p>
				<p>
					Quantity:
					<%=1%></p>
			</div>
			<p class="text-muted"><%=subTotal%></p>
		</div>

		<%
		

		double taxAmount = (subTotal * tax) / 100;
		double totalAmount = subTotal + taxAmount;
		%>

		<h5 class="text-end">
			Total:
			<%=totalAmount%>(after tax)
		</h5>


		<!-- Shipping Details Form -->
		<div class="form-section">
			<h5>Shipping Details:</h5>
			<form action="order" method="post" >
				<input type="hidden" name="task" value="saveSingleProduct"> 
			
			
				<div class="mb-3">
					<label for="name" class="form-label">Full Name : </label>
					<%=session.getAttribute("userName")%>
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">Email Address : </label>
					<%=session.getAttribute("userEmail")%>
				</div>
				<div class="mb-3">
					<label for="phone" class="form-label">Phone Number : </label>
					<%=session.getAttribute("userPhoneNumber")%>
				</div>
				<div class="mb-3">
					<label for="address" class="form-label">Shipping Address</label>
					 <input class="form-control" type="text" name="address"  value="<%=session.getAttribute("userAddress")%>" placeholder="Enter your address"></input>
				</div>
				<div class="mb-3">
					<label class="form-label">City</label> <input type="text"
						class="form-control" name="city" placeholder="Enter your city"
						value="<%=session.getAttribute("userCity")%>" required>
				</div>
				<div class="mb-3">
					<label class="form-label">Pin Code</label> <input type="text"
						class="form-control" name="pinCode"
						placeholder="Enter your pin code"
						value="<%=session.getAttribute("userPincode")%>" required>
				</div>

				<div class="mb-3">
					<label class="form-label">Select Payment Mode</label> 
					<select name="paymentMode" class="form-control" >
						<option value="Cash On Delivery">Cash On Delivery</option>
						<option value="NetBanking">NetBanking</option>
						<option value="UPI">UPI</option>
					</select>
				</div>

				
				
				
				<input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>">
				<input type="hidden" name="totalAmount" value="<%=totalAmount%>">
				<input type="hidden" name="productId" value="<%=productDTO.getId()%>">
				<input type="submit" class="btn btn-primary w-100" value="Place Order">




			</form>

			
		</div>
	</div>
	<%
	}
	%>
	<footer>
		<p>&copy; 2025 MyShop. All rights reserved.</p>
		<div>
			<a href="#">Privacy Policy</a> <a href="#">Terms of Service</a> <a
				href="#">Contact Us</a>
		</div>
	</footer>

</body>
</html>