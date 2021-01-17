<%--
  Created by IntelliJ IDEA.
  User: 23888
  Date: 2021/1/17
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        request.setAttribute("APP_PATH", request.getContextPath());
    %>
</head>
<body>
<h1>Servlet</h1>

<a href="demo2?action=add">Hello</a>
</body>
</html>
