<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="icon" href="images/Logo/logo_c08.png" type="image/icon type">
    <title>PitchFinder</title>
    <link rel="stylesheet" type="text/css" href="./css/user_profile/style_user_profile.css">
    <link rel="stylesheet" type="text/css" href="./css/navbar/style_navbar.css">
</head>
<body>

<%@ include file="../navbar/navbar.jsp"%>

<div class="card">
    <h1>${utente.username}</h1>
    <p class="title">${utente.nome}</p>
    <p class="title">${utente.email}</p>
    <p class="title">${utente.dataDiNascita}</p>
</div>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<!-- Contact form JS-->
<script src="assets/mail/jqBootstrapValidation.js"></script>
<script src="assets/mail/contact_me.js"></script>

<script src="js/homepage/js_homepage.js"></script>
<script src = "js/homepage/check_login.js"></script>

</body>
</html>
