<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add To Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
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

    <!-- Cart Section -->
    <div class="container cart-container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Your Shopping Cart</h2>
            <button class="btn btn-secondary">View All Cart</button>
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
                    <tr>
                        <td><img src="img/bag2.avif" alt="Product Image" style="width: 80px; height: 80px;"></td>
                        <td>Stylish Bag</td>
                        <td>
                            <input type="number" class="form-control" value="1" style="width: 70px;">
                        </td>
                        <td>$50</td>
                        <td>$50</td>
                        <td>
                            <button class="btn btn-danger btn-sm">Remove</button>
                        </td>
                    </tr>
                    <tr>
                        <td><img src="img/bag3.avif" alt="Product Image" style="width: 80px; height: 80px;"></td>
                        <td>Running Shoes</td>
                        <td>
                            <input type="number" class="form-control" value="2" style="width: 70px;">
                        </td>
                        <td>$40</td>
                        <td>$80</td>
                        <td>
                            <button class="btn btn-danger btn-sm">Remove</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <!-- Cart Summary -->
        <div class="cart-summary mt-4">
            <h4>Cart Summary</h4>
            <div class="d-flex justify-content-between">
                <p>Subtotal</p>
                <p>$130</p>
            </div>
            <div class="d-flex justify-content-between">
                <p>Tax (10%)</p>
                <p>$13</p>
            </div>
            <hr>
            <div class="d-flex justify-content-between">
                <h5>Total</h5>
                <h5>$143</h5>
            </div>
            <button class="btn btn-primary w-100 mt-3">Proceed to Checkout</button>
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