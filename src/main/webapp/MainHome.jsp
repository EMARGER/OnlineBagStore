<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
           </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <div class="container">
            <a class="navbar-brand" href="#">🛒 MyShop</a>
            <div>
                
                <a href="MainHome.jsp">Home</a>
                <a href="cart.jsp">cart</a>
                <a href="wishlist.jsp">wishlist</a>
                <a href="orderNow.jsp" style="color: lightgreen;green;green;" >order Now</a>
                <a href="FirstHome.jsp">Logout</a>
            </div>
        </div>
    </nav>

    <!-- Banner Slider -->
    <div id="bannerCarousel" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/banner1.png" class="d-block w-100 bannerHeight" alt="Banner 1">
            </div>
            <div class="carousel-item">
                <img src="img/banner2.jpg" class="d-block w-100 bannerHeight" alt="Banner 2">
            </div>
            <div class="carousel-item">
                <img src="img/banner3.jpg" class="d-block w-100 bannerHeight" alt="Banner 3">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#bannerCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#bannerCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
        </button>
    </div>

    <!-- Search by Category -->
    <div class="search-container">
        <select id="categorySelect">
            <option value="all">All Categories</option>
            <option value="Tote Bag">Tote Bag</option>
            <option value="Backpack">Backpack</option>
            <option value="Bucket Bag">Bucket Bag</option>
            <option value="Handbags">Handbags</option>
            <option value="Wallet">Wallet</option>
        </select>
        <button onclick="searchByCategory()">Search</button>
    </div>

    <script>
        function searchByCategory() {
            const category = document.getElementById("categorySelect").value;
            if (category === "bags") {
                alert("Displaying products for Bags category.");
                // Add logic to filter and display bags
            } else {
                alert(Displaying products for ${category} category.);
                // Add logic for other categories
            }
        }
    </script>

    <!-- Product Cards -->
    <div class="container d-flex justify-content-center">
        <div class="product-card">
            <a href="buyNow.html" style="width: 50%; height: 100%; display: block;">
                <img src="img/bag3.avif" alt="Product Image">
            </a>
            <div class="card-body">
                <h5 class="card-title">Product Name</h5>
                <p class="card-text">This is a high-quality product with great features.</p>
                <div>
                    <a href="buyNow.jsp"><button class="btn btn-primary">Buy Now</button></a>
                </div>
            </div>
        </div>
    </div>

    <div class="container d-flex justify-content-center mt-4">
        <div class="product-card">
            <a href="buyNow.jsp" style="width: 50%; height: 100%; display: block;">
                <img src="img/bag2.avif" alt="Product Image">
            </a>
            <div class="card-body">
                <h5 class="card-title">Product Name</h5>
                <p class="card-text">This is a high-quality product with great features.</p>
                <h3 class="card-title text-success">Price</h3>
                <div>
                    <a href="buyNow.jsp"><button class="btn btn-primary">Buy Now</button></a>
                </div>
            </div>
        </div>
    </div>

    <div class="container d-flex justify-content-center mt-4">
        <div class="product-card">
            <a href="buyNow.jsp" style="width: 50%; height: 100%; display: block;">
                <img src="img/bag1.avif" alt="Product Image">
            </a>
            <div class="card-body">
                <h5 class="card-title">Product Name</h5>
                <p class="card-text">This is a high-quality product with great features.</p>
                <div>
                    <a href="buyNow.jsp"><button class="btn btn-primary">Buy Now</button></a>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
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