<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Now</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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
    <!-- Order Section -->
    <div class="container order-container">
        <h2 class="text-center mb-4">Order Now</h2>

        <!-- Product Summary -->
        <h5>Product Details:</h5>
        <div class="product-card">
            <img src="img/bag2.avif" alt="Product Image">
            <div class="details">
                <h6>Stylish Bag</h6>
                <p class="mb-1">Price: $50</p>
                <p>Quantity: 1</p>
            </div>
            <p class="text-muted">$50</p>
        </div>

        <div class="product-card">
            <img src="img/bag1.avif" alt="Product Image">
            <div class="details">
                <h6>Running Shoes</h6>
                <p class="mb-1">Price: $40</p>
                <p>Quantity: 2</p>
            </div>
            <p class="text-muted">$80</p>
        </div>

        <h5 class="text-end">Total: $130</h5>

        <!-- Shipping Details Form -->
        <div class="form-section">
            <h5>Shipping Details:</h5>
            <form>
                <div class="mb-3">
                    <label for="name" class="form-label">Full Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter your full name" required>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email Address</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter your email" required>
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Shipping Address</label>
                    <textarea class="form-control" id="address" rows="3" placeholder="Enter your address" required></textarea>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone Number</label>
                    <input type="tel" class="form-control" id="phone" placeholder="Enter your phone number" required>
                </div>
                <button type="submit" class="btn btn-primary w-100">Place Order</button>
            </form>
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