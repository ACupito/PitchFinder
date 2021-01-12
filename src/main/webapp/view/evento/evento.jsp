<%@ page import="com.pitchfinder.evento.entity.Evento" %>
<%@ page import="java.util.ArrayList" %>
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
    <link href="css/evento/style_evento.css" rel="stylesheet" type="text/css"/>
    <link href="css/navbar/style_navbar.css" rel="stylesheet" type="text/css"/>
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
<%@ include file="../navbar/navbar.jsp"%>
<!-- Services-->

<section class="page-section" id="services">
        <div class="row text-center">





    </div>
</section>
<div class="container">
    <div class="row">
        <%
            ArrayList<Evento> eventi= new ArrayList<Evento>();
            eventi = (ArrayList<Evento>) application.getAttribute("eventiContext");
            if(eventi.isEmpty()){ %>
        <div class="col-md-4">
                                <span class="fa-stack fa-4x">
                                    <i class="fas fa-circle fa-stack-2x text-primary"></i>
                                    <i class="fas fa-users fa-stack-1x fa-inverse"></i>
                                </span>
            <p class="text-muted">Non ci sono eventi disponibili.</p>
        </div>

        <%}else{
            for (Evento evento: eventi) {%>

            <div class="col-lg-4">
              <div class="card card-margin">
                <div class="card-header no-border">

                    <h5 class="card-title">Evento - Tennis</h5>
                </div>
                <div class="card-body pt-0">
                    <div class="widget-49">
                        <div class="widget-49-title-wrapper">
                            <div class="widget-49-date-primary">
                                <%if(evento.getImage().equalsIgnoreCase("default")){ %>
                                <span class="widget-49-date-day"><%= evento.getStringDay(evento.getDate().getDay())%></span>
                                <span class="widget-49-date-month"><%= evento.getStringMonth(evento.getDate().getMonth())%></span>
                                <%}else{ %>
                                <img style="border-radius: 50%;" width="64px" height="64px" src="<%= evento.getImage().replace("src/main/webapp/","")%>"/>
                                <%}%>
                            </div>
                            <div class="widget-49-meeting-info">
                                <span class="widget-49-pro-title"><%= evento.getName()%></span>
                                <span class="widget-49-meeting-time"> <%= evento.getDate()%> Dalle <%= evento.getStartHour()%> alle <%= evento.getEndHour()%></span>
                            </div>

                        </div>
                        <ol class="widget-49-meeting-points">
                            <li class="widget-49-meeting-item"><span><Strong>Posti Disponibili</Strong> - <%= evento.getAvailableSits()%></span></li>
                            <li class="widget-49-meeting-item"><span><strong>Il nostro ospite</strong> - <%= evento.getGuest()%></span></li>
                            <li class="widget-49-meeting-item"><span><strong>Descrizione:</strong> - <%= evento.getDescription()%></span></li>
                        </ol>
                        <div class="widget-49-meeting-action">
                            <form method="post" action="Prenotazione"> <!-- Prenotazione va sostituita con la servlet di Lucia -->
                                <input type="hidden" class="eventDate" name="eventDate" value="<%=evento.getDate()%>">
                                <input type="hidden" class="eventName" name="eventName" value="<%=evento.getName()%>">
                                <input type="submit" class="btn btn-sm btn-flash-border-primary" name="eventDetailsButton" value="Prenotati!">
                            </form>
                        </div>
                    </div>
                </div>
             </div>
            </div>
            <%}%>
        <%}%>
    </div>
</div>
<!-- Footer-->
<footer class="footer py-4">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-4 text-lg-left">Copyright © PitchFinder 2020</div>
            <div class="col-lg-4 my-3 my-lg-0">
                <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-twitter"></i></a>
                <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-facebook-f"></i></a>
                <a class="btn btn-dark btn-social mx-2" href="#!"><i class="fab fa-linkedin-in"></i></a>
            </div>
            <div class="col-lg-4 text-lg-right">
                <a class="mr-3" href="#!">Privacy Policy</a>
                <a href="#!">Terms of Use</a>
            </div>
        </div>
    </div>
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
<script src="../../js/homepage/js_homepage.js"></script>

</body>
</html>
