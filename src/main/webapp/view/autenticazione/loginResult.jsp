<%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 12/01/2021
  Time: 21:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Login Fallito</title>
    <link rel="stylesheet" type="text/css" href="./css/navbar/style_navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/user_profile/login_failed.css">
    <link href = "https://fonts.googleapis.com/css?family=Poppins" rel = "stylesheet">
    <script src = "https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>

<%@ include file="../navbar/navbar.jsp"%>

<div class="card">
    <h1>${messaggio}</h1>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Contact form JS-->
<script src="assets/mail/jqBootstrapValidation.js"></script>
<script src="assets/mail/contact_me.js"></script>

<script src="./js/homepage/js_homepage.js"></script>
<script src = "./js/homepage/check_login.js"></script>

</body>
</html>
