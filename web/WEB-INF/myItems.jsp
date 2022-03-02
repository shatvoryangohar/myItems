<%@ page import="model.User" %>
<%@ page import="model.Category" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Item" %>
<%@ page import="model.Picture" %><%--
  Created by IntelliJ IDEA.
  User: Gohar
  Date: 28.02.2022
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>

    </title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    List<Item> items = (List<Item>) request.getAttribute("items");
    List<Picture> pictures = (List<Picture>) request.getAttribute("pictures");
%>

<div style="margin: 0 auto;width: 1000px;height: 1000px;border: 1px solid black">


    <div>WELCOME <%=user.getName()%>| <a href="/myItems">Իմ Հայտարարությունները</a>|<a href="/addItem">Ավելացնել</a>|
        <a href="/logout">Logout</a>

        <% for (Item item : items) {%>
        <a href="/singleItem?id=<%=item.getId()%>">
            <div style="width: 105px;float: left">

                <div>
                    <%if (item.getPictureList()!= null && !item.getPictureList().equals(" ")) {%>
                    <img src="/getImage?pic_url=<%=item.getPictureList()%>" width="100"/>

                    <%} else {%>
                    <img src="/image/img.png" width="100">
                    <%}%>
                </div>
                <div>
                    <span><%=item.getTitle()%>|<%=item.getPrice()%></span>
                </div>
            </div>
        </a>
        <%}%>
    </div>
</div>
</body>
</html>
