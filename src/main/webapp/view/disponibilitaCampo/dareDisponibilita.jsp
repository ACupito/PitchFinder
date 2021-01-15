<%--
  Created by IntelliJ IDEA.
  User: andre
  Date: 13/01/2021
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<html>
<%@ page contentType="text/html;charset=UTF-8" %>

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>PitchFinder</title>
    <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/navbar/style_navbar.css" rel="stylesheet" type="text/css"/>
    <link href="css/footer/style_footer.css" rel="stylesheet">
    <link href="css/disponibilita/disponibilita.css" rel="stylesheet" />

    <title>RegistrationForm_v9 by Colorlib</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- DATE-PICKER -->
    <link rel="stylesheet" href="css/disponibilita/datepicker.min.css">


</head>
<!------ Include the above in your HEAD tag ---------->



<body>
<!-- Navigation-->
<%@ include file="../navbar/navbar.jsp"%>

<div class="wrapper">
    <div class="inner">
        <form action="DareDispCampoController">

            <h3>Modifica la tua disponibilità per un campo</h3>

            <div class="form-row">
                <div class="form-wrapper">
                    <label for="data">Data</label>
                    <span class="lnr lnr-calendar-full"></span>
                    <input type="date" class="form-control datepicker-here" name="data" id="data">
                </div>
                <div class="form-wrapper">
                    <label for="inizio">Orario Inizio</label>
                    <span class="lnr lnr-calendar-full"></span>
                    <input type="time" class="form-control datepicker-here" name="inizio" id="inizio">
                </div>
                <div class="form-wrapper">
                    <label for="fine">Orario Fine</label>
                    <span class="lnr lnr-calendar-full"></span>
                    <input type="time" class="form-control datepicker-here" name="fine" id="fine">
                </div>
            </div>
            <div class="form-row last">
                <div class="form-wrapper">
                    <label for="idcampo">Sport</label>
                    <select name="idcampo" id="idcampo" class="form-control">
                        <option value="1002">Tennis</option>

                    </select>
                    <i class="zmdi zmdi-chevron-down"></i>
                </div>
            </div>
            <% if(session.getAttribute("utente")!=null){ %>
            <button type="submit" name="Conferma" value="Conferma" data-text="Conferma" id="Conferma">
                <span>Conferma</span>
            </button>

            <%}else{ %>
            <label  id="creation-error"> Effettuare il login per modificare la disponibilità!</label>
            <script>
                setTimeout(() => {alert("Effettuare il login per modificare la disponibilità!");}, 2000)
            </script>
            <%} %>
        </form>
    </div>
</div>
<!-- Footer-->
<footer class="footer py-4">
    <%@include file="../footer/footer.jsp"%>
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


<script src="js/jquery-3.3.1.min.js"></script>
<!-- DATE-PICKER -->
<script src="js/disponibilita/datepicker.js"></script>
<script src="js/disponibilita/datepicker.en.js"></script>

<script src="js/disponibilita/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>

