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
    List<Category> categories = (List<Category>) request.getAttribute("categories");
    Item item = (Item) request.getAttribute("item");
%>

<div style="margin: 0 auto;width: 1000px;height: 1000px;border: 1px solid black">
    <%if (user == null) {%>
    <div style="float: right"><a href="/login">Լոգին</a>|<a href="/register">Գրանցում</a></div>
    <%} else {%>
    <div>WELCOME <%=user.getName()%>| <a href="/myItems">Իմ Հայտարարությունները</a>|<a href="/addItem">Ավելացնել</a>|<a
            href="/logout">Logout</a>
    </div>
    <%}%>
    <div>
        <ul style="overflow: hidden">
            <li style="display: inline;margin-left: 40px"><a href="/home">Գլխավոր</a></li>
            <%
                for (Category category : categories) {%>
            <li style="display: inline;margin-left: 40px"><a
                    href="/home?cat_id=<%=category.getId()%>"><%=category.getName()%>
            </a>
            </li>
            <% }%>
        </ul>
    </div>
    <div>

        <a href="/singleItem?id=<%=item.getId()%>">
            <div style="width: 500px;float: left">
                <%
                    for (Picture picture : item.getPictureList()) {%>

                <div>
                    <%if (picture.getPicUrl() != null && !picture.getPicUrl().equals("")) {%>
                    <img src="/getImage?pic_url=<%=picture.getPicUrl()%>" width="500"/>

                    <%} else {%>
                    <img src="/image/img.png" width="500">
                    <%}%>
                </div>
                <% }%>

                <div>
                    <span><%=item.getTitle()%>|<%=item.getPrice()%></span>
                </div>
                <span><%=item.getDescription()%></span>
                <div>
                    User
                    Info:<%=item.getUser().getName()%><%=item.getUser().getSurname()%><%=item.getUser().getPhone()%>
                </div>
            </div>
        </a>

    </div>
</div>
</body>
</html>
