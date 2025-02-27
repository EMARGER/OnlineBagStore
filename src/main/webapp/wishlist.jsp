<%@page import="com.bagstore.dto.ProductDTO"%>
<%@page import="com.bagstore.service.ProductService"%>
<%@page import="com.bagstore.util.DBUtil"%>
<%@page import="com.bagstore.dao.ProductDAO"%>
<%@page import="com.bagstore.dto.WishListDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wishlist</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
       

        .wishlist-container {
            margin-top: 50px;
        }

        .wishlist-card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 10px;
            overflow: hidden;
            background-color: white;
            margin-bottom: 20px;
        }

        .wishlist-card img {
            width: 100px;
            height: 100px;
            object-fit: contain;
            border-radius: 5px;
        }

        .wishlist-card .card-body {
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-danger {
            background-color: #dc3545;
            border: none;
        }

        .btn-danger:hover {
            background-color: #a71d2a;
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

    <!-- Navbar -->
   
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
					<input type="submit" name="wishList" value="WishList" class="link"style="color: lightgreen;">
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

    <!-- Wishlist Section -->
    <div class="container wishlist-container">
        <h2 class="text-center mb-4">Your Wishlist</h2>
        
        <%
        DBUtil dbUtil = new DBUtil();
        ProductDAO productDAO=new ProductDAO(dbUtil);
    	ProductService productService=new ProductService(productDAO);
        
        
        if(request.getAttribute("wishListDTOs") != null) {
        	List<WishListDTO> wishListDTOs = (List)request.getAttribute("wishListDTOs");
        	for (WishListDTO wishListDTO : wishListDTOs) {
				Integer productId = wishListDTO.getProductId();
				ProductDTO productDTO = productService.findProductByID(productId);
			
        %>
		
        <!-- Example Wishlist Item -->
        <div class="wishlist-card p-3">
            <div class="d-flex align-items-center">
                <img src="<%=productDTO.getImg() %>" alt="Product Image">
                <div class="ms-3">
                    <h5><%=productDTO.getName() %></h5>
                    <p class="mb-1"><%=productDTO.getPrice() %></p>
                </div>
                <div class="ms-auto d-flex gap-2">
                	<form action="wishList" method="post">
                		<input type="hidden" name="task" value="addToCart">
                		<input type="hidden" name="id" value="<%=session.getAttribute("userId")%>">
                		<input type="hidden" name="productId" value="<%=productDTO.getId()%>">
                    	<button class="btn btn-primary btn-sm">Move to Cart</button>
                    </form>
                    <form action="wishList" method="get">
                    	<input type="hidden" name="task" value="remove">
                    	<input type="hidden" name="id" value="<%=session.getAttribute("userId")%>">
                		<input type="hidden" name="wishlistId" value="<%=wishListDTO.getId()%>">
                    	<button class="btn btn-danger btn-sm ">Remove</button>
                    </form>
                </div>
            </div>
        </div>
		 <%} }%>
        

    </div>
   
     <footer>
        <p>&copy; 2025 MyShop. All rights reserved.</p>
        <div>
            <a href="#">Privacy Policy</a>
            <a href="#">Terms of Service</a>
            <a href="#">Contact Us</a>
        </div>
    </footer>


</body>
</html>