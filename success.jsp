<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            margin: 30px;
        }

        .message {
            color: blue;
            font-size: 18px;
            margin-bottom: 20px;
        }

        .link {
            display: block;
            margin-top: 10px;
            font-size: 14px;
        }

        a {
            color: #007BFF;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <%
        String message = (String) request.getAttribute("success");
    %>

    <% if (message != null && message.toLowerCase().contains("created")) { %>
        <p class="message"><%= message %></p>
        <jsp:include page="./login.jsp"/>

    <% } else if (message != null && message.toLowerCase().contains("updated")) { %>
        <jsp:include page="./home.jsp"/>
        <p class="message"><%= message %></p>

    <% } else if (message != null && message.toLowerCase().contains("deleted")) { %>
        <jsp:include page="./home.jsp"/>
        <p class="message"><%= message %></p>
        <a class="link" href="./login.jsp">Login with other user details</a>
    <% } %>
    
</body>
</html>
