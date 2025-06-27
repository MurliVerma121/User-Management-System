<%@page import="com.nit.mv.pojo.UserAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Update User</title>
	</head>
	<body>
		<jsp:include page="./home.jsp"/>
		
		<% UserAccount account = (UserAccount)request.getAttribute("update"); %>
		<h1> 
			Update User <%= account.getUsername() %> details 
		</h1>
		<form action="./UserAccountServlet" method="post">
			<table>
				<tr>
				  	<td>User ID</td>
				  	<td><input type="number" disabled="disabled" name="userid" value="<%= account.getUserId() %>" /></td>
				</tr>
				<tr>
					<td>Username</td>
				  	<td><input type="text" disabled="disabled" name="username" value="<%= account.getUsername() %>" /></td>
				</tr>
				<tr>
					<td>Password</td> 
					<td><input type="password" name="password" value="<%=account.getPassword()%>"/></td> 
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="email" name="email" value="<%= account.getEmail() %>"/></td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td><input type="tel" name="mobile" value="<%=account.getMobile()%>"/></td>
				</tr>
				<tr>
				<td colspan="2"><input type="submit" name="button" value="Save"></td>
				</tr>
	        </table>
			<BR><BR>
		</form>
		
	</body>
</html>