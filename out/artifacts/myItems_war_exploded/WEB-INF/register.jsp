<%--
  Created by IntelliJ IDEA.
  User: Gohar
  Date: 28.02.2022
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<a href="/home">BACK</a>
<form action="/register" method="post">
    <input type="text" name="name" placeholder="name"><br>
    <input type="text" name="surname" placeholder="surname"><br>
    <input type="email" name="email" placeholder="email"><br>
    <input type="password" name="password" placeholder="password"><br>
    <input type="text" name="phone" placeholder="phone"><br>
    <input type="submit" value="Register">
</form>
</body>
</html>
