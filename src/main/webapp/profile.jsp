<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="login.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .profile-container {
            max-width: 500px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
            position: relative;
        }
        .profile-pic {
            width: 110px;
            height: 110px;
            border-radius: 50%;
            object-fit: cover;
            border: 0px solid #007bff;
            margin-bottom: 15px;
        }
        .info {

            font-size: 18px;
            color: #333;
            text-align: left;
        }
        .info .info-box {
            background: #f1f1f1;
            padding: 10px;
            border-radius: 5px;
            margin: 10px 0;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }
      
       
    </style>
</head>
<body>


	<%
	if (session.getAttribute("userId") == null) {
		response.sendRedirect("login.jsp");
	}
	%>
    
    <div class="container">
        <div class="profile-container shadow-lg">
            
            <img src="img/<%=session.getAttribute("userImg") %>" alt="Profile Picture" class="profile-pic"></a>
            <h3 class="text-secondary"><%=session.getAttribute("userName") %></h3>
            <div class="info mt-5">
                <div class="info-box"><strong>Phone Number:</strong><%=session.getAttribute("userPhoneNumber") %></div>
                <div class="info-box"><strong>Email:</strong> <%=session.getAttribute("userEmail") %></div>
                <div class="info-box"><strong>Address:</strong><%=session.getAttribute("userAddress") %></div>
                <div class="info-box"><strong>City:</strong> <%=session.getAttribute("userCity") %></div>
                <div class="info-box"><strong>Pincode:</strong> <%=session.getAttribute("userPincode") %></div>
                <div class="flex button-div">
                
                	<a href="Edit.jsp" >
						<input type="submit" name="edit" value = "Edit" class="sign-button edit-button">
					</a>
					
                
                </div>
            </div>
        </div>
    </div>
   
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>