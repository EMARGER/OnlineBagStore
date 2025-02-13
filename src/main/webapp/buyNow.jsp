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
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="#">🛍 MyShop</a>
             <div>
                
                <a href="MainHome.jsp">Home</a>
                <a href="cart.jsp">cart</a>
                <a href="wishlist.jsp">wishlist</a>
                <a href="orderNow.jsp" style="color: lightgreen;green;green;" >order Now</a>
                <a href="FirstHome.jsp">Logout</a>
            </div>
        </div>
    </nav>

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
            <a href="#">Privacy Policy</a>
            <a href="#">Terms of Service</a>
            <a href="#">Contact Us</a>
        </div>
    </footer>
</body>
</html>