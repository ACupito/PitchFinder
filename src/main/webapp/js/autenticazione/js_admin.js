// Script per la visualizzazione degli ordini dell'utente
$(document).ready(function() {
    $("#cerca").click(function() {
        var id = $("#idCliente").val();
        $.getJSON("OrdiniCliente", {idCliente: id}, function(data) {
            visualizzaOrdini(data);
        });
    });
});

function visualizzaOrdini(data) {

    if (data.length == 0)
        $("#responsive-table-ordini").css("visibility", "hidden");

    else {
        var elements = "<thead> <tr> <th scope='col'>Ordine N°</th>" +
            "<th scope='col'>IdUtente</th>" +
            "<th scope='col'>Nome auto</th>" +
            "<th scope='col'>Prezzo</th>" +
            "</tr></thead>" +
            "<tbody id='table_ordini'>";
        for (var i = 0; i < data.length; i++) {
            elements += "<tr>" +
                "<th scope='row'>" + data[i].idOrdine + "</th>" +
                "<td data-title = 'IdCustomer'>" + data[i].idCustomer + "</td>" +
                "<td data-title = 'Nome auto'>" + data[i].nomeAuto + "</td>" +
                "<td data-title = 'Prezzo'>" + data[i].prezzo + "</td>" +
                "</tr>";
        }

        elements += "</tbody>";
        $("#responsive-table-ordini").html(elements);
        $("#responsive-table-ordini").css("visibility", "visible");
    }
}


// Script per la rimozione di un utente
function rimuoviCliente(id) {

    $(document).ready(function () {
        $.getJSON("RimuoviCliente", {idCliente: id}, function (data) {
            myFunction(data);
        });
    });
}

function myFunction(data) {

    var table = null;
    for (var i = 0; i < data.length; i++) {
        table += "<tr id = '" + data[i].idCustomer + "'>" +
            "<th scope = 'row'>" + data[i].idCustomer + "</th>" +
            "<td data-title = 'Nome'>" + data[i].nome + "</td>" +
            "<td data-title = 'Cognome'>" + data[i].cognome + "</td>" +
            "<td data-title = 'Remove'>" +
            "<button id = 'remove' name = '" + data[i].idCustomer +"' onclick = 'rimuoviCliente(name)'>"
            + "Remove" +
            "</button>" +
            "</td>" +
            "</tr>";
    }

    $("#tbody_utenti").html(table);
}

/* jquery per cambiare l'elemento da mostrare (utenti in DB, auto in DB, ordini effettuati, impostazioni */
$(document).ready(function(){

    $(document).ready(function(){
        $("#profilo").click(function(){
            $("#profiloAdmin").show();
            $("#creaTorneo").hide();
            $("#creaEvento").hide();
            $("#modificaCampo").hide();
            $("#allTorneo").hide();
            $("#allEvento").hide();
            $("#allPartite").hide();
        });
        $("#crea_torneo").click(function(){
            $("#profiloAdmin").hide();
            $("#creaTorneo").show();
            $("#creaEvento").hide();
            $("#modificaCampo").hide();
            $("#allTorneo").hide();
            $("#allEvento").hide();
            $("#allPartite").hide();
        });
        $("#crea_evento").click(function(){
            $("#profiloAdmin").hide();
            $("#creaTorneo").hide();
            $("#creaEvento").show();
            $("#modificaCampo").hide();
            $("#allTorneo").hide();
            $("#allEvento").hide();
            $("#allPartite").hide();
        });
        $("#modifica_campo").click(function(){
            $("#profiloAdmin").hide();
            $("#creaTorneo").hide();
            $("#creaEvento").hide();
            $("#modificaCampo").show();
            $("#allTorneo").hide();
            $("#allEvento").hide();
            $("#allPartite").hide();
        });
        $("#all_tornei").click(function(){
            $("#profiloAdmin").hide();
            $("#creaTorneo").hide();
            $("#creaEvento").hide();
            $("#modificaCampo").hide();
            $("#allTorneo").show();
            $("#allEvento").hide();
            $("#allPartite").hide();
        });
        $("#all_evento").click(function(){
            $("#profiloAdmin").hide();
            $("#creaTorneo").hide();
            $("#creaEvento").hide();
            $("#modificaCampo").hide();
            $("#allTorneo").hide();
            $("#allEvento").show();
            $("#allPartite").hide();
        });
        $("#all_partite").click(function(){
            $("#profiloAdmin").hide();
            $("#creaTorneo").hide();
            $("#creaEvento").hide();
            $("#modificaCampo").hide();
            $("#allTorneo").hide();
            $("#allEvento").hide();
            $("#allPartite").show();
        });
    });
});