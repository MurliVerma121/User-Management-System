<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #ffffff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        table {
            width: 100%;
        }

        td {
            padding: 10px;
            font-size: 16px;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }

        input[type="submit"] {
            background-color: #007BFF;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .error {
            color: red;
            text-align: center;
            font-weight: bold;
        }

        .register-link {
            margin-top: 10px;
            text-align: center;
            display: block;
        }
    </style>
</head>
<body>
    <% 
        String username = (String) request.getAttribute("username");
        String errorMessage = (String) request.getAttribute("failed");

        if (username == null) username = "";
        if (errorMessage == null) errorMessage = "";
    %>

    <form action="./UserAccountServlet" method="post">
        <table>
            <tr>
                <td>Username</td>
                <td><input type="text" name="username" value="<%= username %>"/></td>
            </tr>
            <tr>
                <td>Password</td> 
                <td><input type="password" name="password" maxlength="32"/></td>
            </tr>
            <tr>
                <td colspan="2" class="error"><%= errorMessage %></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;">
                    <input type="submit" name="button" value="Login">
                </td>
            </tr>
        </table>
        <div class="register-link">
            Don't have an account? <a href="register.html">Create An Account</a>
        </div>
    </form>
</body>
</html>
