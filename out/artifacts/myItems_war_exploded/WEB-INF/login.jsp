<%--
  Created by IntelliJ IDEA.
  User: Gohar
  Date: 28.02.2022
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<a href="/home">BACK</a>
<form action="/login" method="post">
    <input type="email" name="email" placeholder="input your email" required/><br>
    <input type="password" name="password" placeholder="input your password" required/><br>
    <input type="submit" value="Login">
</form>
</body>
</html>
