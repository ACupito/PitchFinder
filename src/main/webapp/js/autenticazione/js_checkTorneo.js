var isNomeValid = false;
var isSquadreValid = false;
var isMinPartecValid = false;
var isMaxPartecValid = false;
var isDataInizioValid = false;
var isDataFineValid = false;
var isAllDateValid = false;

function validateName() {
    if( $("#uname").val().match("^[ a-zA-Z\u00C0-\u00ff']+$")) {
        if ($("#uname").val().length > 1 && $("#uname").val().length < 50) {
            $("#valid_nome").text("La lunghezza del nome ed il formato sono validi.");
            $("#valid_nome").css("color", "#4CAF50");
            isNomeValid = true;
        } else {
            $("#valid_nome").css("color", "#FF0000");
            $("#valid_nome").text("La lunghezza nome non è valida!");
            isNomeValid = false;
        }
    } else {
        $("#valid_nome").css("color", "#FF0000");
        $("#valid_nome").text("Il formato del nome non è valido!");
        isNomeValid = false;
    }
}

function validateSquadre() {

            if (parseInt($("#maxSquadre").val()) >= 1 && parseInt($("#maxSquadre").val()) <= 20) {
            $("#valid_squadra").text("Numero di squadre valido.");
            $("#valid_squadra").css("color", "#4CAF50");
            isSquadreValid = true;
        } else {
            $("#valid_squadra").css("color", "#FF0000");
            $("#valid_squadra").text("Numero di squadre non valido!");
            isSquadreValid = false;
        }
}


function validateMinPartecipanti() {

    if (parseInt($("#minPartecipanti").val()) >= 1 && parseInt($("#minPartecipanti").val()) <= 5) {
        $("#valid_minParteci").text("Numero minimo di partecipanti valido.");
        $("#valid_minParteci").css("color", "#4CAF50");
        isMinPartecValid = true;
    } else {
        $("#valid_minParteci").css("color", "#FF0000");
        $("#valid_minParteci").text("Numero minimo di partecipanti non valido!");
        isMinPartecValid = false;
    }
}

function validateMaxPartecipanti() {

    if (parseInt($("#maxPartecipanti").val()) >= 5 && parseInt($("#maxPartecipanti").val()) <= 12) {
        $("#valid_maxParteci").text("Numero massimo di partecipanti valido.");
        $("#valid_maxParteci").css("color", "#4CAF50");
        isMaxPartecValid = true;
    } else {
        $("#valid_maxParteci").css("color", "#FF0000");
        $("#valid_maxParteci").text("Numero massimo di partecipanti non valido!");
        isMaxPartecValid = false;
    }
}



//Setting min of #data_inizio
function minDateInizio() {
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();

    currentDate = yyyy + '-' + mm + '-' + dd;
    $('#data_inizio').attr("min",currentDate);
}
//Setting max of #data_inizio
function maxDateInizio(){
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();
    currentDate = (yyyy+10) + '-' + mm + '-' + dd;

    $('#creation-data').attr("max",currentDate);
}

function activeDataFine() {
    document.getElementById("data_fine").disabled = false;
}

//Setting min of #data_fine
function minDateFine() {
    var data_inizio = $("#data_inizio").val();
    var startDate = new Date(data_inizio);
    var dd = String(startDate.getDate()).padStart(2, '0');
    var mm = String(startDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = startDate.getFullYear();

    startDate = yyyy + '-' + mm + '-' + dd;
    $('#data_fine').attr("min", startDate);
}

//Setting min of #data_fine
function maxDateFine() {
    var data_inizio = $("#data_inizio").val();
    var startDate = new Date(data_inizio);
    var dd = String(startDate.getDate()).padStart(2, '0');
    var mm = String(startDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = startDate.getFullYear();

    startDate = yyyy+10 + '-' + mm + '-' + dd;
    $('#data_fine').attr("max", startDate);
}

function validateDataInizio() {
    if(!document.getElementById("data_inizio").value.match("^(.*[-])[0-9]*$")){
        $("#valid_dataInizio").css("color","#FF0000");
        $("#valid_dataInizio").text("La data di inizio non è selezionata!");
        isDataInizioValid=false;
    } else {
        $("#valid_dataInizio").css("color","#4CAF50");
        $("#valid_dataInizio").text("La data di inizio è valida!");
        isDataInizioValid=true;
    }
}

function validateDataFine() {
    if(!document.getElementById("data_fine").value.match("^(.*[-])[0-9]*$")){
        $("#valid_dataFine").css("color","#FF0000");
        $("#valid_dataFine").text("La data di fine non è selezionata!");
        isDataFineValid=false;
    } else {
        $("#valid_dataFine").css("color","#4CAF50");
        $("#valid_dataFine").text("La data di fine è valida!");
        isDataFineValid=true;
    }
}

function validateAllDate() {
    var data_inizio = $("#data_inizio").val();
    var startDate = new Date(data_inizio);
    var data_fine = $("#data_fine").val();
    var endDate = new Date(data_fine);
    var days =  ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));
    if(days < 0) {
        $("#valid_dataInizio").css("color","#FF0000");
        $("#valid_dataInizio").text("La data di inizio è successiva alla data di fine!");
        $("#valid_dataFine").css("color","#FF0000");
        $("#valid_dataFine").text("La data di inizio è successiva alla data di fine!");
        isDataInizioValid = false;
        isDataFineValid=false;
    }
    else {
        isDataInizioValid=true;
        isDataFineValid=true;
    }
}

function validateButton() {
    if(isNomeValid && isMinPartecValid && isSquadreValid && isMaxPartecValid && isDataFineValid && isDataInizioValid) {
        document.getElementById("creaButton").disabled = false;
    } else {
        document.getElementById("creaButton").disabled = true;
    }
}

function activeGiornoPartite() {
    var data_inizio = $("#data_inizio").val();
    var startDate = new Date(data_inizio);
    var data_fine = $("#data_fine").val();
    var endDate = new Date(data_fine);

    var weekday = new Array(7);
    weekday[0] = "Domenica";
    weekday[1] = "Lunedì";
    weekday[2] = "Martedì";
    weekday[3] = "Mercoledì";
    weekday[4] = "Giovedì";
    weekday[5] = "Venerdì";
    weekday[6] = "Sabato";

    var sel = document.getElementById('giornoPartite');

    while(sel.options.length > 0) {
        sel.remove(0);
    }

    var days =  ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));

    var giorno = weekday[startDate.getDay()];
    var opt = document.createElement('option');
    opt.appendChild(document.createTextNode(giorno));
    opt.value = giorno;
    sel.appendChild(opt);
    if(days > 6) days = days - (days - 6);
    while (days > 0) {
        startDate.setDate(startDate.getDate() +1);
        giorno = weekday[startDate.getDay()];
        opt = document.createElement('option');
        opt.appendChild(document.createTextNode(giorno));
        opt.value = giorno;
        sel.appendChild(opt);
        days--;
    }

}


$(document).ready( function(){
        minDateInizio()
        maxDateInizio();
    }
);

