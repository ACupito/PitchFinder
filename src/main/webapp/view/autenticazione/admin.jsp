<%--
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
                                    <form action = "" method = "get">
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
                                    <th scope="col">IdUtente</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Cognome</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_torneo">
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
                                    <th scope="col">IdUtente</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Cognome</th>
                                    <th scope="col"></th>
                                </tr>
                                </thead>

                                <tbody id="tbody_evento">
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
