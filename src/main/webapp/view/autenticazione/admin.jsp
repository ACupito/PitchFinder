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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
    <div class="rowFirst">

        <div class="col3-admin">
            <div class="list-group-admin">
                <a href="#" class="list-group-item-admin" id="profilo">Profilo</a>
                <a href="#" class="list-group-item-admin" id="crea_torneo">Crea Torneo</a>
                <a href="#" class="list-group-item-admin" id="crea_evento">Crea Evento</a>
                <a href="#" class="list-group-item-admin" id="modifica_campo">Modifica Campo</a>
                <a href="#" class="list-group-item-admin" id="all_tornei">Visualizza Torneo</a>
                <a href="#" class="list-group-item-admin" id="all_evento">Visualizza Evento</a>
                <a href="#" class="list-group-item-admin" id="all_partite">Visualizza Partite</a>
            </div>
        </div>



        <div class="col9-admin">
            <div class="card-body">



                <div class="card-body-admin" id="profiloAdmin">
                    <div class="row1">
                        <div class="col-md-12-admin">
                            <h4><span><i class="fas fa-cog"></i></span>Profilo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="col-md-12-admin">

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








                <div class="card-body-admin" id="creaTorneo" style="display:none"> <!-- div utenti presenti nel DB-->
                    <div class="row1">
                        <div class="colo-title-admin">
                            <h4><span><i class="fas fa-database"></i></span> Creazione Torneo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">

                        <div class="container">
                            <form action="torneoServlet">
                                <input type="hidden" name="flag" value="1">
                                <input type="hidden" name="idCampo" value="1002">
                                <div class="form-group">
                                    <label for="uname">Nome:</label>
                                    <input type="text" class="form-control" id="uname" name="nome" onblur="validateName()" onchange="validateButton()" required>
                                    <div id="valid_nome">Il nome non può superare 50 caratteri.</div>

                                </div>
                                <div class="form-group">
                                    <label for="data_inizio">Data Inizio:</label>
                                    <input type="date" class="form-control" id="data_inizio" name="dataInizio" required onkeydown="return false" onchange="activeDataFine()" onblur="validateDataInizio()">
                                    <div id="valid_dataInizio">Inserisci una data di inizio.</div>
                                </div>
                                <div class="form-group">
                                    <label for="data_fine">Data Fine:</label>
                                    <input type="date" class="form-control" id="data_fine" name="dataFine" required onkeydown="return false" onclick="minDateFine(); maxDateFine()" disabled onblur="validateDataFine()" onchange="validateButton()">
                                    <div id="valid_dataFine">Inserisci una data di fine.</div>
                                </div>
                                <div class="form-group">
                                    <label for="giornoPartite">Giorno Partite:</label>
                                    <select name="giornoPartite" id="giornoPartite">
                                        <option value="Lunedì">Lunedì</option>
                                        <option value="Martedì">Martedì</option>
                                        <option value="Mercoledì">Mercoledì</option>
                                        <option value="Giovedì">Giovedì</option>
                                        <option value="Venerdì">Venerdì</option>
                                        <option value="Sabato">Sabato</option>
                                        <option value="Domenica">Domenica</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="sport">Sport:</label>
                                    <select name="sport" id="sport">
                                        <option value="Tennis">Tennis</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="tipo">Tipo:</label>
                                    <select name="tipo" id="tipo">
                                        <option value="Gironi">Gironi</option>
                                        <option value="Eliminazione diretta">Eliminazione diretta</option>
                                        <option value="Gironi + Eliminazione diretta">Gironi + Eliminazione diretta</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="struttura">Struttura:</label>
                                    <select name="struttura" id="struttura">
                                        <option value="Partite singole">Partite singole</option>
                                        <option value="Partite doppie">Partite doppie</option>
                                    </select>
                                    <div class="valid-feedback">Valid.</div>
                                    <div class="invalid-feedback">Please fill out this field.</div>
                                </div>
                                <div class="form-group">
                                    <label for="maxSquadre">Numero squadre torneo:</label>
                                    <input type="number" class="form-control" id="maxSquadre" name="maxSquadre" min="1" max="20" required onblur="validateSquadre()" onchange="validateButton()">
                                    <div id="valid_squadra">Il numero delle squadre deve essere maggiore di 1 e minore di 20.</div>
                                </div>
                                <div class="form-group">
                                    <label for="minPartecipanti">Numero minimo di partecipanti per squadra:</label>
                                    <input type="number" class="form-control" id="minPartecipanti" name="minPartecipanti" min="1" max="5" required onblur="validateMinPartecipanti()" onchange="validateButton()">
                                    <div id="valid_minParteci">Il numero minimo di partecipanti deve essere maggiore di 1 e minore di 5.</div>
                                </div>
                                <div class="form-group">
                                    <label for="maxPartecipanti">Numero massimo di partecipanti per squadra:</label>
                                    <input type="number" class="form-control" id="maxPartecipanti" name="maxPartecipanti" min="5" max="12" required onblur="validateMaxPartecipanti()" onchange="validateButton()">
                                    <div id="valid_maxParteci">Il numero massimo di partecipanti deve essere maggiore di 5 e minore di 12.</div>
                                </div>

                                <button type="submit" class="btn btn-primary" id="creaButton" disabled>Crea</button>
                            </form>

                        </div>


                    </div>
                </div>



                <div class="card-body-admincreaEvento" id="creaEvento" style="display: none">
                    <div class="row1">
                        <div class="colo-title-admin">
                            <h4><span><i class="fas fa-database"></i></span> Crezione Evento</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1">
                        <div class="eventCreation">
                            <form action="EventoAdminController" method="post" id="form-creation" onsubmit="return validateName()">
                                <fieldset id="field-creation">


                                    <label for="creation-name" id="creation-label-name">Nome dell'evento:</label>
                                    <input type="text" id="creation-name" name="nome" required onkeyup="validateName()"><br>
                                    <label for="creation-data" id="creation-label-data">Data evento:</label>
                                    <input type="date" id="creation-data" name="data" required onblur="valiDate()"><br>
                                    <label for="creation-timestr" id="creation-label-str">Oario Inizio:</label>
                                    <input type="time" id="creation-timestr" name="orarioInizio" min="09:00" max="23:00"><br>
                                    <label for="creation-timeend" id="creation-label-end">Oario Fine:</label>
                                    <input type="time" id="creation-timeend" name="orarioFine" min="09:00" max="23:00"><br>
                                    <label for="creation-player" id="creation-label-player">Posti disponibili:</label>
                                    <input type="number" id="creation-player" name="postiDisponibili" min="0" max="250" value="0"><br>
                                    <label for="creation-guest" id="creation-label-guest">Nome Ospite:</label>
                                    <input type="text" id="creation-guest" name="ospite" required><br>
                                    <label for="creation-description" id="creation-label-description">Descrizione :</label><br>
                                    <textarea rows="4" cols="50" id="creation-description" name="descrizione" form="form-creation"> </textarea><br>
                                    <input type="radio" id="image1" name="immagine" value="1">
                                    <label for="image1">
                                        <img src="images/events/image1.jpg" alt="Trulli" width="120" height="70">
                                    </label><br>
                                    <input type="radio" id="image2" name="immagine" value="2">
                                    <label for="image2">
                                        <img src="images/events/image2.jpg" alt="asd" width="120" height="70">
                                    </label><br>
                                    <input type="radio" id="image3" name="immagine" value="3">
                                    <label for="image3">
                                        <img src="images/events/image3.jpg" alt="dsa" width="120" height="70">
                                    </label><br>
                                    <input type="radio" id="default" name="immagine" value="default">
                                    <label for="default">
                                        senza Immagine
                                    </label><br>

                                    <input type="submit" name="submit" id="submit" value="Crea Evento">

                                </fieldset>
                            </form>

                        </div>
                    </div>
                </div>


                <div class="card-body-adminmodificaCampo" id="modificaCampo" style="display: none;"> <!-- div ordini presenti nel DB-->
                    <div class="row1">
                        <div class="col-md-12-admin">
                            <h4><span><i class="fas fa-database"></i></span> Modifica disponibilità campo</h4>
                            <hr>
                        </div>
                    </div>
                    <div class="row1" id="ModificaDispCampo">

                        <!--Div per la creazione-->


                    </div>
                </div>


                <div class="card-body-adminallTorneo" id="allTorneo" style="display: none;">
                    <div class="row1">
                        <div class="colo-title-admin">
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


                <div class="card-body-adminallEvento" id="allEvento" style="display: none;">
                    <div class="row1">
                        <div class="colo-title-admin">
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



                <div class="card-body-adminallPartite" id="allPartite" style="display: none;">
                    <div class="row1">
                        <div class="colo-title-admin">
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
<script src="js/autenticazione/js_checkTorneo.js"></script>
</body>
</html>
