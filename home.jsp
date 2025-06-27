<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            margin-top: 50px;
        }

        h1 {
            color: #333;
            margin-bottom: 30px;
        }

        form {
            display: inline-block;
        }

        input[type="submit"] {
            padding: 10px 20px;
            margin: 5px;
            border: 2px solid transparent;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            cursor: pointer;
        }

        input[value="Get User Details"] {
            background-color: cyan;
            border-color: cyan;
        }

        input[value="Get All Users"] {
            background-color: pink;
            border-color: pink;
        }

        input[value="Update"] {
            background-color: yellow;
            border-color: yellow;
        }

        input[value="Delete"] {
            background-color: red;
            border-color: red;
            color: white;
        }
    </style>
</head>
<body>
    <h1>User Actions</h1>
    <form action="./UserAccountServlet" method="post">
        <input type="submit" name="button" value="Get User Details">
        <input type="submit" name="button" value="Get All Users">
        <input type="submit" name="button" value="Update">
        <input type="submit" name="button" value="Delete">
    </form>
</body>
</html>
