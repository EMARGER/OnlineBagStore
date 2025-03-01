
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Edit</title>
<link rel="stylesheet" type="text/css" href="Style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
	integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
	<link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
	<%
	if (session.getAttribute("userId") == null) {
		response.sendRedirect("login.jsp");
	}
	%>

	
	<div class="container flex">
		<div class="header">
			<h1>Edit Profile</h1>
		</div>
		<div>
			<form action="mainHome" method="post">
				<input type="hidden" name = "task" value = "updateDataById">
				<input type="hidden" name = "userId" value = "<%=session.getAttribute("userId")%>">
				<div class="flex form-div">
					<div class="flex names-div">
						<div class="flex input-div">
							<label>FullName</label>

							<div class="name-icon-div">
								<i class="fa-regular fa-user"></i>
								<input type="text" name="fullName" placeholder="Full Name" value="<%=session.getAttribute("userName")%>" class="name-input">
							</div>

						</div>
						<div class="flex input-div">
							<label>Address</label>
							<div class="name-icon-div">
								<i class="fa-regular fa-user"></i>
								<input type="text" name="address" class="name-input" value="<%=session.getAttribute("userAddress")%>" placeholder="Address">
							</div>
						</div>
					</div>

					<div class="flex input-div">
						<label>City</label>
						<div class="remaining-icon-div">
							<input type="text" name="city" class="remaining-input" value="<%=session.getAttribute("userCity")%>" placeholder="City">
						</div>
					</div>

					<div class="flex input-div">
						<label>Email</label>
						<div class="remaining-icon-div">
							<i class="fa-regular fa-envelope"></i>
							<input type="text" name="email" class="remaining-input" value="<%=session.getAttribute("userEmail")%>" placeholder="Email">
						</div>
					</div>

					<div class="flex input-div">
						<label>Pin-Code</label>
						<div class="remaining-icon-div">
							<i class="fa-solid fa-phone"></i>
							<input type="text" name="pincode" class="remaining-input" value="<%=session.getAttribute("userPincode")%>" placeholder="PinCode">
						</div>
					</div>
					
					<div class="flex input-div">
						<label>Phone Number</label>
						<div class="remaining-icon-div">
							<i class="fa-solid fa-phone"></i>
							<input type="text" name="phoneNumber" class="remaining-input" value="<%=session.getAttribute("userPhoneNumber")%>" placeholder="PhoneNumber">
						</div>
					</div>

					<div class="flex button-div">
						<input  type ="submit" name = "submit" value = "Submit" class="sign-button">
						<input type ="reset" name = "reset" value = "Reset" class="reset-button">
					</div>
				</div>	
			</form>
		</div>
	</div>	

	
</body>
</html>
