<%@page import="com.bagstore.dto.CategoryDTO"%>
<%@page import="com.bagstore.service.CategoryService"%>
<%@page import="com.bagstore.dao.CategoryDAO"%>
<%@page import="com.bagstore.dto.ProductDTO"%>
<%@page import="com.bagstore.dto.OrderItemDTO"%>
<%@page import="com.bagstore.dto.OrderDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.bagstore.service.OrderItemService"%>
<%@page import="com.bagstore.dao.OrderItemDAO"%>
<%@page import="com.bagstore.service.CartService"%>
<%@page import="com.bagstore.dao.CartDAO"%>
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
    <title>Cart Order History</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
	    
		
		.link{
			background: none;
			border: none;
			color: white
		}
        .card {
            height: auto;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s ease-in-out;
        }
        .card:hover {
            transform: scale(1.02);
        }
        .card-img-top {
            height: 100px;
            width: auto;
            object-fit: contain;
            margin: auto;
        }
        .details-card {
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .badge {
            font-size: 0.9rem;
            padding: 5px 10px;
        }
        .order-history-section {
            margin-bottom: 40px; /* Increased spacing between sections */
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
					<input type="submit" name="orderNow" value="Orders" class="link"style="color: lightgreen;">
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
	

    <div class="container mt-5" style="min-height: 500px">
    <h2 class="text-center mb-4" style="margin-top: 100px">Order History</h2>
    <%
	    DBUtil dbUtil = new DBUtil();
	
		ProductDAO productDAO = new ProductDAO(dbUtil);
		ProductService productService = new ProductService(productDAO);
	
		OrderItemDAO orderItemDAO= new OrderItemDAO(dbUtil);
		OrderItemService orderItemService= new OrderItemService(orderItemDAO); 
		
		CategoryDAO categoryDAO = new CategoryDAO(dbUtil);
		CategoryService categoryService = new CategoryService(categoryDAO);
		
		
		if(request.getAttribute("orderDTOs") != null){
			List<OrderDTO> orderDTOs = (List)request.getAttribute("orderDTOs");
			for(OrderDTO orderDTO : orderDTOs){
	
	
	
	%>
        <section class="order-history-section" >
            <div class="card p-4 shadow-lg" >
                
                
                <div class="row mb-4">
                    <!-- User Details -->
                    <div class="col-md-6">
                        <div class="details-card">
                            <h4>User Details</h4>
                            <p><strong>Name:</strong><%=session.getAttribute("userName")%></p>
                            <p><strong>Email:</strong><%=session.getAttribute("userEmail")%></p>
                            <p><strong>Phone:</strong><%=session.getAttribute("userPhoneNumber")%></p>
                        </div>
                    </div>
                    
                    <!-- Shipment Details -->
                    <div class="col-md-6">
                        <div class="details-card">
                            <h4>Shipment Details</h4>
                            <p><strong>Address:</strong> <%=orderDTO.getAddress()%>, <%=orderDTO.getCity()%>, <%=orderDTO.getPincode() %></p>
                            <p><strong>Payment Method:</strong> <%=orderDTO.getPaymentMode()%></p>
                            <p><strong>Estimated Delivery:</strong> 5-7 Business Days</p>
                        </div>
                    </div>
                </div>
                
                <div class="row">
                
                	<% 
	                		List<OrderItemDTO> orderItemDTOs = (List)orderItemService.findOrderItemByOrderId(orderDTO.getId());
	                		if(orderItemDTOs != null){
	                			for(OrderItemDTO orderItemDTO : orderItemDTOs){
	                				ProductDTO productDTO = productService.findProductByID(orderItemDTO.getProductId());
	                				CategoryDTO categoryDTO = categoryService.findById(productDTO.getCategoryId());
                		
                	%>
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <img src="<%=productDTO.getImg()%>" class="card-img-top" alt="Wireless Headphones">
                            <div class="card-body">
                                <h5 class="card-title"><%=productDTO.getName()%></h5>
                                <p class="card-text"><%=productDTO.getDescription()%></p>
                                <p><strong>Category:</strong> <%=categoryDTO.getName() %></p>
                                <p><strong>Quantity:</strong> <%=orderItemDTO.getQuantity() %></p>
                                <p><strong>Price:</strong> Rs.<%=productDTO.getPrice() %></p>
                                <p><strong>Total Price:</strong> Rs.<%=orderItemDTO.getTotalPrice() %></p>
                                <span class="badge bg-success">Delivered</span>
                            </div>
                        </div>
                    </div>
                    <%
                				}
                			}
                    %>
                    
                </div>
            </div>
        </section>
        <%		
				}
			}
		%>
    </div>
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

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
