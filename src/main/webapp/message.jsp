<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>message1</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
<%if(request.getAttribute("status")!=null){
		
		String status = (String)request.getAttribute("status");
		String message = (String)request.getAttribute("message");
		String linkName = (String)request.getAttribute("linkName");
		String redirectUrl = (String)request.getAttribute("redirectUrl");
		%>
	

	<section class="message-section">
		<div class="message-div flex">
		
			<h1><%=status%></h1>
			<p><%=message %></p>
			<a href="<%=redirectUrl %>" class="sign-button link-button"><%=linkName %></a>
	<%} %>		
		</div>
	</section>
	
</body>
</html>