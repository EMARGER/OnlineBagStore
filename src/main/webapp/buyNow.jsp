<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BuyNow</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
     <link rel="stylesheet" type="text/css" href="style.css">
    <style>
       
        .container {
            margin-top: 15px;
        }

        .product-details {
            display: flex;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            overflow: hidden;
            height: 500px; /* Reduced height */
        }

        .product-details img {
            width: 50%;
            object-fit: contain;
            padding: 20px;
        }

        .details {
            padding: 20px;
            width: 50%;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .details h3 {
            margin-bottom: 15px;
        }

        .details p {
            margin-bottom: 10px;
        }

        .details .price {
            font-size: 18px;
            font-weight: bold;
            color: #28a745;
        }

        .btn {
            padding: 8px 12px;
            font-size: 14px;
            margin-right: 5px;
        }

        .buy-now-btn {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
        }

        .buy-now-btn:hover {
            background-color: #0056b3;
        }

        @media (max-width: 768px) {
            .product-details {
                flex-direction: column;
                height: auto;
            }

            .product-details img, .details {
                width: 100%;
            }
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

    <!-- Buy Now Page Content -->
    <div class="container d-flex justify-content-center">
        <div class="product-details">
            <img src="img/bag2.avif" alt="Product Image">
            <div class="details">
                <h3>Product Name</h3>
                <p>This is a high-quality product with excellent features that enhance your daily life.</p>
                <p class="price">Price: ₹1,499</p>
                <div>
                 <a href="cart.jsp"><button class="btn btn-success">Add to Cart</button></a> 
                 <a href="wishlist.jsp"><button class="btn btn-warning">Wishlist</button></a>
                 <a href="orderNow.jsp"><button class="buy-now-btn btn">Order Now</button></a>                 

                    
                </div>
            </div>
        </div>
    </div>

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