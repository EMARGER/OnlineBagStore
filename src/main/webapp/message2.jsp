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
		String servalet = (String)request.getAttribute("servalet");
		String task = (String)request.getAttribute("task");
		Integer id = (Integer)request.getAttribute("id");
		%>
	

	<section class="message-section">
		<div class="message-div flex">
		
			<h1><%=status%></h1>
			<p><%=message %></p>
			<form action="<%=servalet%>" method="get">
				<input type="hidden" name="task" value="<%=task%>">
				<input type="hidden" name="id" value="<%=id%>">
				<input type="submit"  value="<%=linkName %>" class="sign-button link-button">	
			</form>
			<%-- <a href="<%=redirectUrl %>" class="sign-button link-button"><%=linkName %></a> --%>
	<%} %>		
		</div>
	</section>
	
</body>
</html>