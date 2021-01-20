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
    <link rel="icon" href="images/Logo/logo_c08.png" type="image/icon type">
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.1/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->

    <link href="css/navbar/style_navbar.css" rel="stylesheet" type="text/css"/>
    <link href="css/footer/style_footer.css" rel="stylesheet">
    <link href="css/disponibilita/style-disponibilita.css" rel="stylesheet" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script language="JavaScript" type="text/javascript" src="js/disponibilita/js_availability.js"></script>




</head>
<!------ Include the above in your HEAD tag ---------->



<body>
<!-- Navigation-->
<%@ include file="../navbar/navbar.jsp"%>

<div class="wrapper">
    <div class="inner">
        <form action="DareDispCampoController" onsubmit="return validateFormDisp() ">

            <h3>Indica la tua disponibilità per un campo</h3>

            <div class="form-row">
                <div class="form-wrapper">
                    <label for="data">Data</label>

                    <input type="date" class="form-control datepicker-here" name="data" id="data" onkeydown="return false" onblur="valiDateDisp()"><br>
                    <small id="small-creation-data"> Inserire una data valida(DD/MM/YYYY)</small><br>

                </div>
                <div class="form-wrapper">
                    <label for="inizio">Orario Inizio</label>

                    <input type="text" class="timepicker" name="inizio" id="inizio" minTime="09:00" maxTime="23:00" onkeydown="return false"  size="5" required  onmousemove="clickTimeValidateDispIn()"><br>
                    <small id="small-creation-timestr"> Inserire un orario di inizio valido(HH:MM)</small><br>

                </div>
                <div class="form-wrapper">
                    <label for="fine">Orario Fine</label>

                    <input type="text" name="fine" id="fine" class="timepicker" minTime="09:00" maxTime="23:00"  size="5" onkeydown="return false" required  onmousemove="clickTimeValidateDispFi()"><br>
                    <small id="small-creation-timeend"> Inserire un orario di fine valido(HH:MM)</small><br>

                </div>
            </div>
            <div class="form-row last">
                <div class="form-wrapper">
                    <label for="idcampo">Sport</label>
                    <select name="idcampo" id="idcampo" class="form-control">
                        <option value="1002">Tennis</option>

                    </select>
                </div>
            </div>


            <% if(session.getAttribute("utente")!=null){ %>
            <button type="submit" name="Conferma" value="Conferma" data-text="Conferma" id="Conferma">
                <span>Conferma</span>
            </button>

            <%}else{ %>
            <label  id="creation-error"> Effettuare il login per indicare la disponibilità!</label>
            <script>
                setTimeout(() => {alert("Effettuare il login per indicare la disponibilità!");}, 2000)
            </script>
            <%} %>
        </form>
    </div>
    <!--- Esito --->
    <% String esito = (String) request.getAttribute("risultato");
        if(esito!= null){
            if(esito.equals("1")){
    request.setAttribute("risultato",null); %>
    <script>
        alert("La disponibilità viene memorizzata con successo.");
    </script>
    <% } %>
    <% }%>
</div>


<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<!-- Core theme JS-->
<script src="js/homepage/js_homepage.js"></script>
<script src = "js/homepage/check_login.js"></script>

</html>

