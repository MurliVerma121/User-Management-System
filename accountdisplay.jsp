<%@page import="com.nit.mv.dao.UserAccountDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@page import="com.nit.mv.pojo.UserAccount"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 20px;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px 16px;
            border: 1px solid #ccc;
            text-align: left;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        th {
            background-color: #007BFF;
            color: white;
        }
    </style>
</head>
<body>
    <jsp:include page="./home.jsp"/>
    <%
        UserAccount account = (UserAccount)request.getAttribute("account");
        ArrayList<UserAccount> accountsList = (ArrayList)request.getAttribute("accountsList");
    %>

    <% if(account != null) { %>
        <h1><%= account.getUsername() %> details</h1>
        <table>
            <tr>
                <th>UserId</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Mobile</th>
            </tr>
            <tr>
                <td><%= account.getUserId() %></td>
                <td><%= account.getUsername() %></td>
                <td><%= account.getPassword() %></td>
                <td><%= account.getEmail() %></td>
                <td><%= account.getMobile() %></td>
            </tr>
        </table>
    <% } else if(accountsList != null && !accountsList.isEmpty()) { %>
        <h1>All users details</h1>
        <table>
            <tr>
                <th>UserId</th>
                <th>Username</th>
                <th>Password</th>
                <th>Email</th>
                <th>Mobile</th>
            </tr>
            <% for(UserAccount userAccount : accountsList) { %>
                <tr>
                    <td><%= userAccount.getUserId() %></td>
                    <td><%= userAccount.getUsername() %></td>
                    <td><%= userAccount.getPassword() %></td>
                    <td><%= userAccount.getEmail() %></td>
                    <td><%= userAccount.getMobile() %></td>
                </tr>
            <% } %>
        </table>
    <% } %>
</body>
</html>
