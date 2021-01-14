<%@ page import="com.pitchfinder.torneo.entity.Torneo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>PitchFinder</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="css/torneo/style_dettagliTorneo.css" rel="stylesheet">
    <link href="css/navbar/style_navbar.css" rel="stylesheet">
    <link href="css/footer/style_footer.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<%@include file="../navbar/navbar.jsp"%>

<% Torneo torneo = (Torneo) application.getAttribute("torneo"); %>

<div class="container emp-profile">

        <div class="row">
            <div class="card-header" style = "background: #fff;">
                <input type="hidden" name="nomeTorneo" value="<%=torneo.getNome()%>">
                <h5 class="title">
                    <%=torneo.getNome()%>
                </h5>
                <h6>
                    Tennis
                </h6>

            </div>
        </div>

        <div class="row">

            <div class = "card-body">
                <div class="row">
                    <input type="hidden" name="campo" value="<%=torneo.getCampoIdentificativo()%>">
                    <div class="col-md-12">
                        <label>Tipo torneo</label>
                    </div>
                    <div class="col-md-12">
                        <p><%= torneo.getTipo()%></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <label>Struttura turni</label>
                    </div>
                    <div class="col-md-12">
                        <p><%= torneo.getStruttura()%></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <label>Giorno partite</label>
                    </div>
                    <div class="col-md-12">
                        <p><%= torneo.getGiornoPartite()%></p>
                    </div>
                </div>
            </div>
            <div class = "card-body">

                <div class="row">
                    <div class="col-md-12">
                        <label>Dal</label>
                    </div>
                    <div class="col-md-12">
                        <input type="hidden" name="dataTorneo" value="<%=torneo.getDataInizio()%>">
                        <p><%= torneo.getDataInizio()%></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <label>Al</label>
                    </div>
                    <div class="col-md-12">
                        <p><%= torneo.getDataFine()%></p>
                    </div>
                </div>
            </div>
            <div class = "card-body">

                <div class="row">
                    <div class="col-md-12">
                        <label>Massimo numero squadre</label>
                    </div>
                    <div class="col-md-12">
                        <p><%= torneo.getNumeroSquadre()%></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <label>Numero squadre prenotate</label>
                    </div>
                    <div class="col-md-12">
                        <p>5</p>
                    </div>
                </div>
            </div>
            <div class="card-body">

                <div class="row">
                    <div class="col-md-12">
                        <label>Minimo giocatori per squadra</label>
                    </div>
                    <div class="col-md-12">
                        <p><%= torneo.getMinNumeroPartecipantiPerSquadra()%></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <label>Massimo giocatori per squadra</label>
                    </div>
                    <div class="col-md-12">
                        <p><%= torneo.getMaxNumeroPartecipantiPerSquadra()%></p>
                    </div>
                </div>
            </div>
            <div class="card-body buttons">
                <form action="torneoServlet" method="get">
                    <input type="hidden" name="flag" value="3">
                     <button class="btn-primary button-indietro">Indietro</button>
                </form>
                <form action="Iscrizione" method="get">
                <button class="btn-primary button-iscrivi">Iscrivi</button>
                </form>
            </div>
        </div>

</div>


<%@include file="../footer/footer.jsp"%>

</body>

<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

</html>

