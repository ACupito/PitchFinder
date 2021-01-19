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



    <script language="JavaScript" type="text/javascript" src="./js/torneo/iscrizioneTorneo.js"></script>
</head>
<body>

<%@include file="../navbar/navbar.jsp"%>

<% Torneo torneo = (Torneo) request.getAttribute("torneo"); %>
<div class="container emp-profile">
    <form action="IscrizioneTorneoController" onsubmit="return cambiaStatoIscrizione()" id="iscrizione" name="iscrizione" method="get">
        <input type="hidden" name="nomeTorneo" value="<%=torneo.getNome()%>">
        <input type="hidden" name="dataTorneo" value="<%=torneo.getDataInizio()%>">
        <input type="hidden" name="campoTorneo" value="<%=torneo.getCampoIdentificativo()%>">
        <div class="row">
            <div class="card-header" style = "background: #ffffff;">

                <h5 class="title">
                    <%=torneo.getNome()%>-<span style="color:#079e3e">Iscrizione Squadra</span>
                </h5>
                <h6 style="color: #079e3e">
                    Tennis
                </h6>

            </div>
        </div>

        <div class="row">

            <div class = "card-body">
                <div class="row" >
                    <div class="col-md-12">
                        <table>
                            <tr>
                                <td>
                                    <label id="nSquadra" > Nome Squadra</label>
                                <td/>
                                <td>
                                    <i id="alertNome" style="display: none; position: relative" data-placement="top" data-toggle="tooltip" title="Inserire stringhe senza caratteri speciali lunga al massimo 50 caratteri" class="fas fa-fw fa-exclamation-circle mr-3 align-self-center"></i>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <div class="col-md-12">
                        <input  id="nomeSquadra" class="form-control" name="nomeSquadra" type="text" style="max-width: max-content"  onkeyup="validaNome()" >
                    </div>
                </div>
            </div>
            <div class = "card-body">

                <div class="row">
                    <div class="col-md-12">
                        <table>
                            <tr>
                                <td>
                                    <label  id="numeroGiocatori">Numero Giocatori</label>
                                <td/>
                                <td>
                                    <i id="alertNGiocatori" style="display: none; position: relative" data-placement="top" data-toggle="tooltip" title="Inserire numero compreso tra <%=torneo.getMinNumeroPartecipantiPerSquadra()%> e <%=torneo.getMaxNumeroPartecipantiPerSquadra()%>" class="fas fa-fw fa-exclamation-circle mr-3 align-self-center"></i>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <div class="col-md-12">
                        <input type="hidden" id="minP" name="minP" value="<%=torneo.getMinNumeroPartecipantiPerSquadra()%>">
                        <input type="hidden" id="maxP" name="maxP" value="<%=torneo.getMaxNumeroPartecipantiPerSquadra()%>">
                        <p><input type="number" id="nGiocatori"  class="form-control"   onchange="validaNGiocatori()" style="max-width: max-content" class="form-control"  name="nGiocatori" onclick="Caselle()"  min="<%=torneo.getMinNumeroPartecipantiPerSquadra()%>" max="<%=torneo.getMaxNumeroPartecipantiPerSquadra()%>"></p>
                    </div>
                </div>
            </div>
            <div class = "card-body">
                <div class="row">
                    <div class="col-md-12">
                        <table>
                            <tr>
                                <td>
                                    <label id="nomecognome">Giocatori</label>
                                <td/>
                                <td>
                                    <i id="alertNomeGiocatori" style="display: none; position: relative" data-placement="top" data-toggle="tooltip" title="Compilare tutte le caselle con caratteri alfabetici. Il NOME deve essere formato al massimo da 10 caratteri. Il COGNOME deve essere formato al massimo da 20 caratteri" class="fas fa-fw fa-exclamation-circle mr-3 align-self-center"></i>
                                </td>
                            </tr>
                        </table>

                    </div>
                </div>
            </div>
            <div class="col-md-12" style="height:150px;border:1px solid grey; max-width:80%;overflow-x: hidden; margin-left:12%;overflow-y: scroll">
                <div class="card-body">
                    <div class="row" id="divGiocatori" style="max-width: 50%">
                        <div class="col-md-12">
                            <label id="nomeG">Nome</label>
                        </div>
                        <% for(int i = 0; i < torneo.getMaxNumeroPartecipantiPerSquadra();i++){%>
                        <div class="col-md-12"  style="width:50%;padding-top:10px">
                            <input type="text"  onkeyup="validaGiocatori()"  style=" display: none" name="nome<%=i%>"id="nome<%=i%>">
                        </div>
                        <%}%>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <label id="cognomeG">Cognome</label>
                            <% for(int i = 0; i < torneo.getMaxNumeroPartecipantiPerSquadra();i++){%>
                            <div class="col-md-12" style="width:50%;padding-top: 10px">
                                <input  onkeyup="validaGiocatori()"  style="display: none" name="cognome<%=i%>" id="cognome<%=i%>">
                            </div>
                            <%}%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <table>
                            <tr>
                                <td>
                                    <label id="nomeCapitano">Nome Capitano</label>
                                <td/>
                                <td>
                                    <i id="alertNomeCapitano" style="display: none; position: relative" data-placement="top" data-toggle="tooltip" title="Inserire stringhe senza caratteri speciali e senza numeri lunghezza massima 10" class="fas fa-fw fa-exclamation-circle mr-3 align-self-center"></i>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <div class="col-md-12">
                        <input type="text" style="max-width:max-content" onkeyup="validaNomeCapitano()" class="form-control" name="nomeCapitano">
                    </div>
                </div>
            </div>
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <table>
                            <tr>
                                <td>
                                    <label id="cognomeCapitano">Cognome Capitano</label>
                                <td/>
                                <td>
                                    <i id="alertCognomeCapitano" style="display: none; position: relative" data-placement="top" data-toggle="tooltip" title="Inserire stringhe senza caratteri speciali e senza numeri lunghezza massima 20" class="fas fa-fw fa-exclamation-circle mr-3 align-self-center"></i>
                                </td>
                            </tr>
                        </table>

                    </div>
                    <div class="col-md-12">
                        <input type="text" style="max-width:max-content" onkeyup="validaCognomeCapitano()" class="form-control" name="cognomeCapitano">
                    </div>
                </div>
            </div>



        </div>
        <div class="card-body buttons">
            <input type="hidden" name="nomeTorneo" value="<%=torneo.getNome()%>">
            <input type="hidden" name="campo" value="<%=torneo.getCampoIdentificativo()%>">
            <input type="hidden" name="dataTorneo" value="<%=torneo.getDataInizio()%>">
            <button type="submit" name="conferma" id="conferma" class="btn-primary button-indietro" value="conferma">Iscrivi</button>
        </div>
    </form>

</div>


<%@include file="../footer/footer.jsp"%>

</body>

<!-- Bootstrap core JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script>
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
    });
</script>
</html>