<%--
  Created by IntelliJ IDEA.
  User: eugen
  Date: 11/01/2021
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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
    <link href="css/navbar/style_navbar.css" rel="stylesheet" />
    <link href="css/partita/style_matchCreation.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <script language="JavaScript" type="text/javascript" src="./js/partita/js_matchCreation.js"></script>
</head>
<body id="page-top">
<!-- Navigation-->
<%@ include file="/view/navbar/navbar.jsp"%>
<div class="login-page" id="match-section">
    <div class="div-form" id="form1">
        <form class="register-form" action="Creation-Servlet" method="post" id="form-creation" onsubmit="return validateForm()">
                <h4 align="center"> Prenota un campo </h4>
                <input type="hidden" class="code" name="idCampo" value="1002">
                <label for="creation-data" id="creation-label-data">Data partita:</label>
                <input type="text" id="creation-data" name="date"  onblur="valiDate()"><br>
                <small id="small-creation-data"> Inserire una data valida(DD/MM/YYYY)</small><br>
                <label for="creation-timestr" id="creation-label-str">Orario Inizio:</label>
                <input type="text" id="creation-timestr" class="timepicker" name="start" minTime="09:00" maxTime="23:00"  size="5" required  onclick="validateStart()" onchange="validateStart()" ><br>
                <small id="small-creation-timestr"> Inserire un orario di inizio valido(HH:MM)</small><br>
                <label for="creation-timeend" id="creation-label-end">Orario Fine:</label>
                <input type="text" id="creation-timeend" class="timepicker" name="end" minTime="09:00" maxTime="23:00"  size="5" required  onclick="clickTimeValidate()" onchange="clickTimeValidate()"><br>
                <small id="small-creation-timeend"> Inserire un orario di fine valido(HH:MM), max 2 ore di partita</small><br>
                <label for="creation-player" id="creation-label-player">Numero giocatori:</label>
                <input type="number" id="creation-player" name="maxGiocatori" min="0" max="3" value="0" required onchange="validateNPlayer()"><br>
                <small id="small-creation-player"> Inserire un numero di giocatori da invitare(max 3 totali)</small><br>

                <label for="nameG1" id="creation-label-nameG1" style="display: none">Nome:</label>
                <input type="text" id="nameG1" name="nameG1" style="display: none" class="objName" onkeyup="validateName1()" >
                <small id="small-nameG1" style="display: none"> Inserire nome del giocatore (lunghezza nome minima compresa tra 1 e 16)</small><br>
                <label for="surnameG1" id="creation-label-surnameG1" style="display: none"> Cognome: </label>
                <input type="text" id="surnameG1" name="surnameG1" style="display: none" class="objSurname" onkeyup="validateSurname1()" >
                <small id="small-surnameG1" style="display: none"> Inserire cognome del giocatore (lunghezza nome minima compresa tra 1 e 16)</small><br>

                <label for="nameG2" id="creation-label-nameG2" style="display: none">Nome:</label>
                <input type="text" id="nameG2" name="nameG2" class="objName" style="display: none"  onkeyup="validateName2()" >
                <small id="small-nameG2" style="display: none"> Inserire nome del giocatore (lunghezza nome minima compresa tra 1 e 16)</small><br>
                <label for="surnameG2" id="creation-label-surnameG2" style="display: none"> Cognome: </label>
                <input type="text" id="surnameG2" name="surnameG2" class="objSurname" style="display: none" onkeyup="validateSurname2()" >
                <small id="small-surnameG2" style="display: none"> Inserire cognome del giocatore (lunghezza nome minima compresa tra 1 e 16)</small><br>

                <label for="nameG3" id="creation-label-nameG3" style="display: none">Nome:</label>
                <input type="text" id="nameG3" name="nameG3" class="objName" style="display: none"  onkeyup="validateName3()" >
                <small id="small-nameG3" style="display: none"> Inserire nome del giocatore (lunghezza nome minima compresa tra 1 e 16)</small><br>
                <label for="surnameG3" id="creation-label-surnameG3" style="display: none"> Cognome: </label>
                <input type="text" id="surnameG3" name="surnameG3" class="objSurname" style="display: none"  onkeyup="validateSurname3()" >
                <small id="small-surnameG3" style="display: none"> Inserire cognome del giocatore (lunghezza nome minima compresa tra 1 e 16)</small><br>

                <div id="div-Availability" style="display: none">
                    <div id="form-Availability"></div>
                </div>
                <% if(session.getAttribute("utente")!=null){ %>
                <input type="submit" id="creation-submit" name="btnMatchCreation" value="Conferma">
                <input type="button" id="creation-availability" name="btnAvailability" value="Cerca giocatori disponibili" onclick="showAvailability()">
                <%}else{ %>
                <label  id="creation-error" name="creation-error"> Effettuare il login per prenotare una partita!</label>
                <script>
                    setTimeout(() => {alert("Effettuare il login per prenotare una partita!");}, 2000)
                </script>
                <%} %>
        </form>
    </div><!--- div .form --->
    <!--- Esito --->
    <% String esito = (String) request.getAttribute("esito");
        if(esito!= null){
            if(esito.equals("1")){%>
                <script>
                    setTimeout(() => {alert("La prenotazione viene memorizzata con successo");}, 2000)
                </script>
        <% } %>
    <%}%>
</div> <!--- login-page --->

<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<!-- Core theme JS-->
<script src="js/homepage/js_homepage.js"></script>
<script src = "js/homepage/check_login.js"></script>
</body>
</html>