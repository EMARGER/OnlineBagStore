<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Sign Up</title>
	<link rel="stylesheet" type="text/css" href="login.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>
<section class="sign-up-section">
	<div class="container flex">
		<div class="header">
			<h1>SIGN UP</h1>
		</div>
		<div>
			<form action="firstHome" method="post">
				<input type="hidden" name = "task" value = "signup">
				<div class="flex form-div">
					<div class="flex names-div">
						<div class="flex input-div">
							<label>FullName</label>

							<div class="name-icon-div">
								<i class="fa-regular fa-user"></i>
								<input type="text" name="fullName" placeholder="Full Name" class="name-input">
							</div>

						</div>
						<div class="flex input-div">
							<label>Address</label>
							<div class="name-icon-div">
								<i class="fa-regular fa-user"></i>
								<input type="text" name="address" class="name-input"  placeholder="Address">
							</div>
						</div>
					</div>

					<div class="flex input-div">
						<label>City</label>
						<div class="remaining-icon-div">
							<input type="text" name="city" class="remaining-input"  placeholder="City">
						</div>
					</div>

					<div class="flex input-div">
						<label>Email</label>
						<div class="remaining-icon-div">
							<i class="fa-regular fa-envelope"></i>
							<input type="text" name="email" class="remaining-input"  placeholder="Email">
						</div>
					</div>

					<div class="flex input-div">
						<label>Pin-Code</label>
						<div class="remaining-icon-div">
							<i class="fa-solid fa-phone"></i>
							<input type="text" name="pincode" class="remaining-input"  placeholder="PinCode">
						</div>
					</div>
					
					<div class="flex input-div">
						<label>Phone Number</label>
						<div class="remaining-icon-div">
							<i class="fa-solid fa-phone"></i>
							<input type="text" name="phoneNumber" class="remaining-input"  placeholder="PhoneNumber">
						</div>
					</div>

					<div class="flex input-div">
						<label>Password</label>
						<div class="remaining-icon-div">
							<i class="fa-solid fa-lock"></i>
							<input type="password" name="password" class="remaining-input"  placeholder="Password">
						</div>
					</div>

					<div class="flex button-div">
						<input  type ="submit" name = "submit" value = "Sign Up" class="sign-button">
						<input type ="reset" name = "reset" value = "Reset" class="reset-button">
					</div>

					
					<div class="text-div">
						<p>Do have an account? <span><a href="login.jsp">Login</a> </span></p>
					</div>
				</div>	
			</form>
		</div>
	</div>	
</section>
</body>
</html>
