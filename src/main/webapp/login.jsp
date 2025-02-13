<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Login</title>
	<link rel="stylesheet" type="text/css" href="login.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer"/>
</head>
<body>

	<section class="sign-up-section">
	<div class="container flex">
		<div class="header">
			<h1>Login</h1>
		</div>
		<div>
			<form action="firstHome" method="post">
				<input type="hidden" name = "task" value = "login">
				<div class="flex form-div">

					<div class="flex input-div">
						<label>User Name</label>
						<div class="remaining-icon-div">
							<i class="fa-regular fa-user"></i>
							<input type="text" name="userName" class="remaining-input"  placeholder="Email or PhoneNumber">
						</div>
					</div>

					<div class="flex input-div">
						<label>Password</label>
						<div class="remaining-icon-div">
							<i class="fa-solid fa-lock"></i>
							<input type="password" name="password" class="remaining-input"  placeholder="Password">
						</div>
					</div>

					<div>
						<input type="checkbox">
  						<label for="">Remeber Me</label>
					</div>

					<div class="flex button-div">
						<input  type ="submit" name = "submit" value = "Login" class="sign-button">
						<input type ="reset" name = "reset" value = "Reset" class="reset-button">
					</div>

					<div class="text-div">
						<p>Do not have an account? <span><a href="signUp.jsp">Sign Up</a> </span></p>
					</div>
				</div>	
			</form>
		</div>
	</div>	
</section>

</body>
</html>