<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String message = (String)request.getAttribute("error"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=message%></title>
</head>
<body>
 	<jsp:include page="./home.jsp"/>
 	
  	<h3 style="color: red">
		<%=message%> <BR><BR>
  	</h3>
	
		<% if(message.contains("not found")){ %>
			<a href='./login.jsp'>Login with other user details</a>
			
		<% } else if(message.contains("No Users found")){ %>
			<a href='./register.html'>Register New user</a>
		<% } %>
</body>
</html>
