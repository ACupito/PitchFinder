<%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 16/01/2021
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.pitchfinder.evento.entity.Evento" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />

    <title>Error</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/evento/style_evento.css" rel="stylesheet" type="text/css"/>
    <link href="css/error/error.css" rel="stylesheet">
    <link href="css/navbar/style_navbar.css" rel="stylesheet" type="text/css"/>
    <link href="css/footer/style_footer.css" rel="stylesheet">
    <style>
        input[type=button], input[type=submit], input[type=reset] {
            background-color: #4CAF50;
            border: none;
            color: white;
            padding: 16px 32px;
            text-decoration: none;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body id="page-top">
<!-- Navigation-->
<%@ include file="/view/navbar/navbar.jsp"%>
<!-- Services-->

<div class="page-wrap d-flex flex-row align-items-center" style="margin-top: 10%">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 text-center">
                <span class="display-1 d-block">${requestScope['javax.servlet.error.status_code']}</span>

                <div class="mb-4 lead">${requestScope['javax.servlet.error.message']}</div>
                <a href="home" class="btn btn-link">Back to Home</a>
            </div>
        </div>
    </div>
</div>



<!-- Footer-->
<footer class="footer py-4">
    <%@include file="/view/footer/footer.jsp"%>
</footer>
<!-- Bootstrap core JS-->
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
