<%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 04/01/2021
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="./css/user_profile/style_user_profile.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar/style_navbar.css">
</head>
<body>

<%@ include file="../navbar/navbar.jsp"%>

<div class="row1">
    <div class="row2">
        <div>
            <label class="label">
                <span> ${utente.email} </span><br>
            </label>
            <label class="label">
                <span> ${utente.username} </span><br>
            </label>
            <label class="label">
                <span> ${utente.nome} </span><br>
            </label>
            <label class="label">
                <span> ${utente.cognome} </span><br>
            </label>

            <label class="label">
                <span> ${utente.dataDiNascita} </span><br>
            </label>
        </div>
    </div>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Contact form JS-->
<script src="assets/mail/jqBootstrapValidation.js"></script>
<script src="assets/mail/contact_me.js"></script>
<!-- Core theme JS-->
<script src="js/homepage/js_homepage.js"></script>
<script src = "js/homepage/check_login.js"></script>

</body>
</html>
