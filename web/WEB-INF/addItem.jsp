<%@ page import="java.util.List" %>
<%@ page import="model.Category" %><%--
  Created by IntelliJ IDEA.
  User: Gohar
  Date: 28.02.2022
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%List<Category> categories = (List<Category>) request.getAttribute("categories");%>
<a href="/home">BACK</a>
<form action="/addItem" method="post" enctype="multipart/form-data">
    <input type="text" name="title" placeholder="title"/><br>
    <input type="number" name="price" placeholder="price"/><br>
    <textarea name="description" placeholder="description"></textarea><br>
    <select name="cat_id">
        <%
            for (Category category : categories) {%>
        <option value="<%=category.getId()%>"><%=category.getName()%>
        </option>
        <% }
        %>
    </select>
    <input type="file" name="picture"multiple><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
