<%@ page import="java.util.ArrayList" %>
<%@ page import="com.pitchfinder.torneo.entity.Torneo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pitchfinder.partita.entity.Partita" %>
<%@ page import="com.pitchfinder.evento.entity.Evento" %><%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 04/01/2021
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PitchFinder</title>
    <link href="css/admin_profile/style_adminProfile.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<%
    List<Torneo> tornei = new ArrayList<>();
    List<Evento> eventi = new ArrayList<>();
    List<Partita> partite = new ArrayList<>();
    tornei = (List<Torneo>) application.getAttribute("tornei");
    eventi = (List<Evento>) application.getAttribute("eventi");
    partite = (List<Partita>) application.getAttribute("partite");
%>

<div class="hero">
    <div class="row">

        <div class="col3">
            <div class="list-group">
                <a href="#" class="list-group-item" id="profilo">Profilo</a>
                <a href="#" class="list-group-item" id="crea_torneo">Crea Torneo</a>
                <a href="#" class="list-group-item" id="crea_evento">Crea Evento</a>
                <a href="#" class="list-group-item" id="modifica_campo">Modifica Campo</a>
                <a href="#" class="list-group-item" id="all_tornei">Visualizza Torneo</a>
                <a href="#" class="list-group-item" id="all_evento">Visualizza Evento</a>
                <a href="#" class="list-group-item" id="all_partite">Visualizza Partite</a>
            </div>
        </div>



        <div class="col9">
            <div class="card">



                <div class="card-body" id="profiloAdmin">
                    <div class="row1">
                        <div class="col-md-12">
                            <h4><span><i class="fas fa-cog"></i></span>Profilo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="col-md-12">

                            <div class="data">
                                <div class="offset-4 col-8">

                                    <p>${admin.username}</p>
                                    <p>${admin.nome}</p>
                                    <p>${admin.cognome}</p>
                                    <br>
                                    <br>
                                    <form action = "autentication" method = "get">
                                        <input type="hidden" name="flag" value="4">
                                        <input name="submit_esci" type="submit" class="btn_impostazioni" id = "log_out"
                                               value="Logout"> <!-- button per uscire dall'area dell'amministratore-->
                                    </form>
                                    <br>
                                    <br>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>








                <div class="card-body" id="creaTorneo" style="display: none"> <!-- div utenti presenti nel DB-->
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-database"></i></span> Creazione Torneo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">

                        <!--Div per la creazione-->




                    </div>
                </div>



                <div class="card-body" id="creaEvento" style="display: none;">
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-database"></i></span> Crezione Evento</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">


                        <!--Div per la creazione-->




                    </div>
                </div>


                <div class="card-body" id="modificaCampo" style="display: none;"> <!-- div ordini presenti nel DB-->
                    <div class="row1">
                        <div class="col-md-12">
                            <h4><span><i class="fas fa-database"></i></span> Modifica disponibilità campo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1" id="ModificaDispCampo">

                        <!--Div per la creazione-->


                    </div>
                </div>


                <div class="card-body" id="allTorneo" style="display: none;">
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-database"></i></span> Tornei creati</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" style="width: 100%">

                                <thead>
                                <tr>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Data Inizio</th>
                                    <th scope="col">Data Fine</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_torneo">
                                <% for(Torneo t : tornei) {%>
                                    <tr id = "<%= t.getDataInizio()%>">
                                        <th scope = "row"> <%= t.getNome()%> </th>
                                        <td data-title = "Data Inizio"> <%= t.getDataInizio()%> </td>
                                        <td data-title = "Data Fine"> <%= t.getDataFine()%> </td>
                                        <td data-title = "Remove">
                                            <form action="torneoServlet" method="get">
                                                <input type="hidden" name="flag" value="2">
                                                <input type="hidden" name="idCampo" value="1002">
                                                <input type="hidden" name="nome" value="<%= t.getNome()%>">
                                                <input type="hidden" name="dataInizio" value="<%= t.getDataInizio()%>">
                                                <input type="hidden" name="dataFine" value="<%= t.getDataFine()%>">
                                                <input type="hidden" name="giornoPartite" value="<%= t.getGiornoPartite()%>">
                                                <button class = "remove" name = "<%= t.getDataInizio()%>">
                                                    Remove
                                                </button>

                                            </form>

                                        </td>
                                    </tr>
                                <%}%>

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>


                <div class="card-body" id="allEvento" style="display: none;">
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-database"></i></span> Eventi creati</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" style="width: 100%">

                                <thead>
                                <tr>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Data</th>
                                    <th scope="col">Ospite</th>
                                    <th scope="col">Orario</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_evento">
                                <% for (Evento e : eventi) { %>
                                    <tr>
                                        <th scope = "row"> <%= e.getName()%> </th>
                                        <td data-title = "Data"> <%= e.getDate()%> </td>
                                        <td data-title = "Ospite"> <%= e.getGuest()%> </td>
                                        <td data-title = "Orario"> <%= e.getStartHour()%> - <%= e.getEndHour()%> </td>
                                        <td data-title = "Remove">
                                            <form action="EventoAdminDeleteController" method="get">
                                                <input type="hidden" name="nome" value="<%= e.getName()%>">
                                                <input type="hidden" name="data" value="<%= e.getDate()%>">
                                                <button class = "remove">
                                                    Remove
                                                </button>

                                            </form>
                                        </td>
                                    </tr>
                                <%}%>

                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>



                <div class="card-body" id="allPartite" style="display: none;">
                    <div class="row1">
                        <div class="colo_title">
                            <h4><span><i class="fas fa-database"></i></span> Partite create</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="data">
                            <table class="responsive-table" style="width: 100%">

                                <thead>
                                <tr>
                                    <th scope="col">IdUtente</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Cognome</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_partite">
                                <c:forEach items="${sessionScope.listaClienti}" var = "cliente">
                                    <tr id = "${cliente.id}">
<%--                                        //questo corrisponde alla colonna IdUtente--%>
                                        <th scope = "row"> ${cliente.id} </th>
                                        <td data-title = "Nome"> ${cliente.nome} </td>
                                        <td data-title = "Cognome"> ${cliente.cognome} </td>
                                        <td data-title = "Remove">
                                            <button class = "remove" name = "${cliente.id}"
                                                    onclick = "rimuoviCliente(name)">  <!-- button per rimuovere utente dal DB-->
                                                Remove
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>


                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</div>


<script src="js/autenticazione/js_admin.js"></script>

</body>
</html>
