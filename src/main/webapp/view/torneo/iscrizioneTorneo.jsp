<%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 14/01/2021
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.pitchfinder.torneo.entity.Torneo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>IscrizioneTorneo</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="css/torneo/style_dettagliTorneo.css" rel="stylesheet">
    <link href="css/navbar/style_navbar.css" rel="stylesheet">
    <link href="css/footer/style_footer.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<%@include file="../navbar/navbar.jsp"%>

<% Torneo torneo = (Torneo) request.getAttribute("torneo"); %>

<div class="container emp-profile">
    <form action="IscrizioneTorneoController" method="get">
    <div class="row">
        <div class="card-header" style = "background: #fff;">

            <h5 class="title">
                <%=torneo.getNome()%>-Iscrizione Squadra
            </h5>
            <h6>
                Tennis
            </h6>

        </div>
    </div>

    <div class="row">

        <div class = "card-body">
            <div class="row">
                <div class="col-md-12">
                    <label>Nome Squadra</label>
                </div>
                <div class="col-md-12">
                    <input type="text"  id="nomeSquadra" name="nomeSquadra">
                </div>
            </div>
        </div>
        <div class = "card-body">

            <div class="row">
                <div class="col-md-12">
                    <label>Numero Giocatori:</label>
                </div>
                <div class="col-md-12">
                    <input type="hidden" id="maxP" value="<%=torneo.getMaxNumeroPartecipantiPerSquadra()%>">
                    <p><input type="number" name="numeroGiocatori" onclick="Caselle()" id="nGiocatori" min="<%=torneo.getMinNumeroPartecipantiPerSquadra()%>" max="<%=torneo.getMaxNumeroPartecipantiPerSquadra()%>"></p>
                </div>
            </div>
        </div>
        <div class = "card-body">

            <div class="row">
                <div class="col-md-12">
                    <label>Giocatori</label>
                </div>

            </div>

        </div>
        <div class="card-body" >
            <div class="row">
                <div class="col-md-12">
                    <label>Nome</label>
                </div>

                <% for(int i = 0; i < torneo.getMaxNumeroPartecipantiPerSquadra();i++){%>
                <div class="col-md-12" style="padding-top:10px">
                    <input type="text" style="display: none" name="nomePlayer<%=i%>"id="nome<%=i%>">
                </div>
                <%}%>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <label>Cognome</label>
                    <% for(int i = 0; i < torneo.getMaxNumeroPartecipantiPerSquadra();i++){%>
                        <div class="col-md-12" style="padding-top: 10px">
                            <input type="text" style="display: none" name="cognomePlayer<%=i%>" id="cognome<%=i%>">
                        </div>
                    <%}%>
                </div>
            </div>
        </div>
        <div class="card-body">
            <div class="row">
                <div class="col-md-12">
                    <label>Capitano</label>
                </div>
            </div>
            <div class="row">
        </div>
                <div class = "card-body">
                    <div class="row">
                        <div class="col-md-12">
                            <label>Nome</label>
                        </div>
                        <div class="col-md-12">
                           <input type="text" name="nomeCapitano">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label>Cognome</label>
                        </div>
                        <div class="col-md-12">
                            <input type="text" name="cognomeCapitano">
                        </div>
                    </div>

                </div>

            </div>
        <div class="card-body buttons">
            <form action="torneoServlet" method="get">
                <input type="hidden" name="flag" value="3">
                <button class="btn-primary button-indietro" name="indietro">Indietro</button>
            </form>

                <input type="hidden" name="nomeTorneo" value="<%=torneo.getNome()%>">
                <input type="hidden" name="campo" value="<%=torneo.getCampoIdentificativo()%>">
                <input type="hidden" name="dataTorneo" value="<%=torneo.getDataInizio()%>">
                <button type="submit" name="conferma" class="btn-primary button-indietro" value="conferma">Iscrivi</button>

        </div>
    </div>
    </form>
</div>


<%@include file="../footer/footer.jsp"%>

</body>
<script>
    function Caselle(){
        var n = document.getElementById("nGiocatori").value;
        var nMax = document.getElementById("maxP").value;
        for(var i = 0; i < n; i++){
            document.getElementById("nome"+i).style.display="block";
            document.getElementById("cognome"+i).style.display="block";
         }
        if(n<nMax){
        for(var j=n; j<nMax;j++){
            document.getElementById("nome"+j).style.display="none";
            document.getElementById("cognome"+j).style.display="none";
        }}
    }
</script>
<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

</html>
