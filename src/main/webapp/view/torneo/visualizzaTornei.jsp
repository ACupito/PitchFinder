<%@ page import="java.sql.Date" %>
<%@ page import="com.pitchfinder.torneo.entity.Torneo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
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
    <link href="css/torneo/style_visualizzaTornei.css" rel="stylesheet" type="text/css" />


</head>

<body>
<!-- NAVIGATION -->
<%@include file="../navbar/navbar.jsp"%>
<% Date current = new Date(System.currentTimeMillis());
    ArrayList<Torneo> tornei = new ArrayList<Torneo>();
    tornei = (ArrayList<Torneo>) application.getAttribute("tornei");%>

<div class="containerCards">
    <div class="row">

        <%

            if(tornei.isEmpty()){ %>
            <h4>NON CI SONO TORNEI DISPONIBILI</h4>

        <%}else{
            for (Torneo t: tornei) {
                if(current.before(t.getDataFine())) {
        %>

     <div class="col-lg-4">
            <div class="card card-margin">
                <div class="card-header no-border">
                    <h5 class="card-title">Torneo - Tennis</h5>
                </div>
                <div class="card-body pt-0">
                    <div class="widget-49">
                        <div class="widget-49-title-wrapper">
                            <div class="widget-49-date-primary">
                                <span class="widget-49-date-day"><%= t.getStringDay(t.getDataInizio())%></span>
                                <span class="widget-49-date-month"><%= t.getStringMonth(t.getDataInizio().getMonth())%></span>
                            </div>
                            <div class="widget-49-meeting-info">
                                <span class="widget-49-pro-title"><%= t.getNome()%></span>
                                <span class="widget-49-meeting-time">Dal giorno <%= t.getDataInizio()%> al <%= t.getDataFine()%></span>
                            </div>
                        </div>
                        <div class="widget-49-meeting-action">
                            <form action="torneoServlet" method="get">
                                <input type="hidden" name="flag" value="4">
                                <input type="hidden" name="idCampo" value="1002">
                                <input type="hidden" name="nome" value="<%= t.getNome()%>">
                                <input type="hidden" name="dataInizio" value="<%= t.getDataInizio()%>">
                                <button id="info" class="btn-display btn-sm btn-flash-border-primary">INFO <i class="fas fa-arrow-right"></i></button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%}%>
        <%}%>
        <%}%>


    </div>
</div>








</body>
<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script src="js/homepage/js_homepage.js"></script>
<script src = "js/homepage/check_login.js"></script>

</html>
