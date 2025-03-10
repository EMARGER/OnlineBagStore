<%@page import="com.bagstore.dto.ProductDTO"%>
<%@page import="com.bagstore.service.ProductService"%>
<%@page import="com.bagstore.dao.ProductDAO"%>
<%@page import="com.bagstore.util.DBUtil"%>
<%@page import="com.bagstore.dto.CartDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add To Cart</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<style>
.cart-container {
	margin-top: 50px;
}

.cart-table {
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	border-radius: 10px;
	overflow: hidden;
	background-color: white;
}

.cart-summary {
	padding: 20px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
	border-radius: 10px;
	background-color: white;
}

.btn-primary {
	background-color: #007bff;
	border: none;
}

.btn-primary:hover {
	background-color: #0056b3;
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
	    width: 45px; 
	    height: 45px;
	    border-radius: 50%; 
	    border: none;
	    padding: 0;
	    overflow: hidden;
	    display: flex;
	    align-items: center;
	    justify-content: center;
	    background-color: white;
	    border: 2px solid lightgreen; 
	    cursor: pointer;
	}

	.profile-img {
	    width: 100%; 
	    height: 100%;
	    border-radius: 50%; 
	    object-fit: cover;
	}
	.profile-btn:hover {
	    border-color: #007bff; 
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
					<input type="submit" name="Home" value="Home" class="link">
				</form>	
					
				<form action="cart" method="get">	
					<input type="hidden" name="task" value="findAll">
					<input type="hidden" name="id" value="<%=session.getAttribute("userId")%>">
					<input type="submit" name="cart" value="Cart" class="link" style="color: lightgreen;">
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
				
			</div>
		</div>
	</nav>
	
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
	<!-- Cart Section -->


	<div class="container cart-container" style="min-height: 500px" >
		<div class="d-flex justify-content-between align-items-center mb-4 " style="margin-top: 100px">
			<h2>Your Shopping Cart</h2>
		</div>

		<!-- Cart Table -->

		<div class="table-responsive cart-table">
			<table class="table">
				<thead class="table-dark">
					<tr>
						<th>Product</th>
						<th>Name</th>
						<th>Quantity</th>
						<th>Price</th>
						<th>Total</th>
						<th>Action</th>
					</tr>
				</thead>

				<tbody>
					<!-- Example Product -->
				<%
					DBUtil dbUtil = new DBUtil();
				    ProductDAO productDAO=new ProductDAO(dbUtil);
					ProductService productService=new ProductService(productDAO);
	
	
	
					if (request.getAttribute("cartDTOs") != null) {
						List<CartDTO> cartDTOs = (List) request.getAttribute("cartDTOs");
						double subTotal = 0;
						double tax = 10;
						for (CartDTO cartDTO : cartDTOs) {
							Integer productId = cartDTO.getProductId();
							ProductDTO productDTO = productService.findProductByID(productId);
							double totalPrice = cartDTO.getQuntity() * productDTO.getPrice();
							subTotal = totalPrice + subTotal;
				%>
					<tr>
						<td><img src="<%=productDTO.getImg()%>" alt="Product Image"
							style="width: 80px; height: 80px;"></td>
						<td><%=productDTO.getName()%></td>
						<td><%=cartDTO.getQuntity()%><!-- <input type="number" class="form-control" value="1"
							style="width: 70px;"> --></td>
						<td>Rs.<%=productDTO.getPrice() %></td>
						<td>Rs.<%=totalPrice%></td>
						<td>
						<form action="cart" method="get">
                    		<input type="hidden" name="task" value="remove">
                			<input type="hidden" name="cartId" value="<%=cartDTO.getId()%>">
                			<input type="hidden" name="userId" value="<%=session.getAttribute("userId")%>">
                    		<button class="btn btn-danger btn-sm">Remove</button>
                    	</form>
						</td>
					</tr>
					<%}
						double taxAmount = (subTotal*10)/100;
						double totalAmount = subTotal + taxAmount;
						
					%>
					
				</tbody>
			</table>
		</div>

		<!-- Cart Summary -->
		<div class="cart-summary mt-4">
			<h4>Cart Summary</h4>
			<div class="d-flex justify-content-between">
				<p>Subtotal</p>
				<p>Rs.<%= subTotal %></p>
			</div>
			<div class="d-flex justify-content-between">
				<p>Tax (<%=tax%>%)</p>
				<p>Rs.<%=taxAmount %></p>
			</div>
			<hr>
			<div class="d-flex justify-content-between">
				<h5>Total</h5>
				<h5>Rs.<%=totalAmount %></h5>
			</div>
			
			
			 <form action="order" method="get">
				<input type="hidden" name="task" value="findAll">
                <input type="hidden" name="userId" value="<%=session.getAttribute("userId") %>">
				<button class="btn btn-primary w-100 mt-3">Proceed to Checkout</button>
			</form>
		</div>
	</div>
	<%
	}
	%>
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