<%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 12/01/2021
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./css/navbar/style_navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/user_profile/login_failed.css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>

<%@ include file="../navbar/navbar.jsp"%>

<div class="principal">
    <div class="message">
        <span class="text"><i class="fas fas">${messaggio}</i></span>
    </div>
</div>

</body>
</html>
