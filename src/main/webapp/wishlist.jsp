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

    <!-- Wishlist Section -->
    <div class="container wishlist-container">
        <h2 class="text-center mb-4">Your Wishlist</h2>

        <!-- Example Wishlist Item -->
        <div class="wishlist-card p-3">
            <div class="d-flex align-items-center">
                <img src="img/bag2.avif" alt="Product Image">
                <div class="ms-3">
                    <h5>Stylish Bag</h5>
                    <p class="mb-1">Price: $50</p>
                </div>
                <div class="ms-auto">
                    <button class="btn btn-primary btn-sm">Move to Cart</button>
                    <button class="btn btn-danger btn-sm">Remove</button>
                </div>
            </div>
        </div>

        <div class="wishlist-card p-3">
            <div class="d-flex align-items-center">
                <img src="img/bag1.avif" alt="Product Image">
                <div class="ms-3">
                    <h5>Running Shoes</h5>
                    <p class="mb-1">Price: $40</p>
                </div>
                <div class="ms-auto">
                    <button class="btn btn-primary btn-sm">Move to Cart</button>
                    <button class="btn btn-danger btn-sm">Remove</button>
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