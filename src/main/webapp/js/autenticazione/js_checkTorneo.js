var isNomeValid = false;
var isSquadreValid = false;
var isMinPartecValid = false;
var isMaxPartecValid = false;
var isDataInizioValid = false;
var isDataFineValid = false;
var isAllDateValid = false;
var isSportValid = false;
var isTipoValid = false;
var isStrutturaValid = false;
var isGiornoPartiteValid = false;
var dayValid = [];

function validateSport() {
    var sport = $("#sport").val();
    if(sport.localeCompare("null") == 0) {
        $("#valid_sport").css("color", "#FF0000");
        $("#valid_sport").text("Lo sport non è stato selezionato!");
        isSportValid = false;
    } else {
        $("#valid_sport").text("Lo sport è stato selezionato.");
        $("#valid_sport").css("color", "#4CAF50");
        isSportValid = true;
    }
}

function validateTipo() {
    var tipo = $("#tipo").val();
    if(tipo.localeCompare("null") == 0) {
        $("#valid_tipo").css("color", "#FF0000");
        $("#valid_tipo").text("Il tipo non è stato selezionato!");
        isTipoValid = false;
    } else {
        $("#valid_tipo").text("Il tipo è stato selezionato.");
        $("#valid_tipo").css("color", "#4CAF50");
        isTipoValid = true;
    }
}

function validateStruttura() {
    var struttura = $("#struttura").val();
    if(struttura.localeCompare("null") == 0) {
        $("#valid_struttura").css("color", "#FF0000");
        $("#valid_struttura").text("La struttura non è stata selezionata!");
        isStrutturaValid = false;
    } else {
        $("#valid_struttura").text("La struttura è stata selezionata.");
        $("#valid_struttura").css("color", "#4CAF50");
        isStrutturaValid = true;
    }
}

function validateGiornoPartite() {
    var giornoPartite = $("#giornoPartite").val();
    var validate = false;

    if(giornoPartite.length > 1 && giornoPartite.length < 20) {

        if(giornoPartite.match("^[ a-zA-Z\\u00C0-\\u00ff']+$")) {
            switch (giornoPartite) {
                case "Domenica":
                    validate = true;
                    break;
                case "Lunedì":
                    validate = true;
                    break;
                case "Martedì":
                    validate = true;
                    break;
                case "Mercoledì":
                    validate = true;
                    break;
                case "Giovedì":
                    validate = true;
                    break;
                case "Venerdì":
                    validate = true;
                    break;
                case "Sabato":
                    validate = true;
                    break;
                default:
                    validate = false;
                    break;
            }
            if (!validate) {
                $("#valid_giornoPartite").css("color", "#FF0000");
                $("#valid_giornoPartite").text("Formato Errato! Giorno non valido! (Se è un giorno inserisci lettera maiuscola all'inizio)");
                isGiornoPartiteValid = false;
            } else {
                $("#valid_giornoPartite").text("La lunghezza del giorno delle partite ed il formato sono validi.");
                $("#valid_giornoPartite").css("color", "#4CAF50");
                isGiornoPartiteValid = true;
            }
        }
            else {
                $("#valid_giornoPartite").css("color", "#FF0000");
                $("#valid_giornoPartite").text("Il formato del giorno delle partite non è valido!");
                isGiornoPartiteValid = false;
            }
    } else {
        $("#valid_giornoPartite").css("color", "#FF0000");
        $("#valid_giornoPartite").text("La lunghezza del giorno delle partite non è valida!");
        isGiornoPartiteValid = false;
    }

}


function validateName() {
    if( $("#uname").val().match("^[ a-zA-Z\u00C0-\u00ff']+$")) {
        if ($("#uname").val().length > 1 && $("#uname").val().length < 50) {
            $("#valid_nome").text("La lunghezza del nome ed il formato sono validi.");
            $("#valid_nome").css("color", "#4CAF50");
            isNomeValid = true;
        } else {
            $("#valid_nome").css("color", "#FF0000");
            $("#valid_nome").text("La lunghezza del nome non è valida!");
            isNomeValid = false;
        }
    } else {
        $("#valid_nome").css("color", "#FF0000");
        $("#valid_nome").text("Il formato del nome non è valido!");
        isNomeValid = false;
    }
}

function validateSquadre() {
        var value = $("#maxSquadre").val();
        if(!value.match("^[0-9]")) {
            $("#valid_squadra").css("color", "#FF0000");
            $("#valid_squadra").text("Il formato del numero di squadre non è valido!");
            isSquadreValid = false;
        } else {
                if (parseInt(value) >= 1 && parseInt(value) <= 20) {
                $("#valid_squadra").text("Il numero delle squadre è valido.");
                $("#valid_squadra").css("color", "#4CAF50");
                isSquadreValid = true;
            } else {
                $("#valid_squadra").css("color", "#FF0000");
                $("#valid_squadra").text("Numero di squadre non valido!");
                isSquadreValid = false;
            }
        }
}


function validateMinPartecipanti() {
    var minPartecipanti = $("#minPartecipanti").val();
    if(!minPartecipanti.match("^[0-9]")) {
        $("#valid_minParteci").css("color", "#FF0000");
        $("#valid_minParteci").text("Il formato del numero minimo di partecipanti non è valido!");
        isMinPartecValid = false;
    } else {
        if (parseInt(minPartecipanti) >= 1 && parseInt(minPartecipanti) <= 5) {
            $("#valid_minParteci").text("Numero minimo di partecipanti valido.");
            $("#valid_minParteci").css("color", "#4CAF50");
            isMinPartecValid = true;
        } else {
            $("#valid_minParteci").css("color", "#FF0000");
            $("#valid_minParteci").text("Numero minimo di partecipanti non valido!");
            isMinPartecValid = false;
        }
    }
}

function validateMaxPartecipanti() {
    var maxPartecipanti = $("#maxPartecipanti").val();
    if(!maxPartecipanti.match("^[0-9]")) {
        $("#valid_maxParteci").css("color", "#FF0000");
        $("#valid_maxParteci").text("Il formato del numero massimo di partecipanti non è valido!");
        isMaxPartecValid = false;
    }
    else {
        if (parseInt(maxPartecipanti) >= 5 && parseInt(maxPartecipanti) <= 12) {
            $("#valid_maxParteci").text("Numero massimo di partecipanti valido.");
            $("#valid_maxParteci").css("color", "#4CAF50");
            isMaxPartecValid = true;
        } else {
            $("#valid_maxParteci").css("color", "#FF0000");
            $("#valid_maxParteci").text("Numero massimo di partecipanti non valido!");
            isMaxPartecValid = false;
        }
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
    }
    else if(!document.getElementById("data_inizio").value.match("^[1-9]{1}[0-9]{3}-[0-9]{2}-[0-9]{2}")){
        $("#valid_dataInizio").css("color","#FF0000");
        $("#valid_dataInizio").text("Il formato della data di inizio è sbagliato!");
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
    } else if(!document.getElementById("data_fine").value.match("^[1-9]{1}[0-9]{3}-[0-9]{2}-[0-9]{2}")){
        $("#valid_dataFine").css("color","#FF0000");
        $("#valid_dataFine").text("Il formato della data di fine è sbagliato!");
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
        $("#valid_AllSquadre").css("color","#FF0000");
        $("#valid_AllSquadre").text("La data di inizio è successiva alla data di fine!");
        isDataInizioValid = false;
        isDataFineValid=false;
    }
    else {
        $("#valid_AllSquadre").css("color","#000000");
        $("#valid_AllSquadre").text("Inserisci una data di fine successiva alla data di inizio.");
        isDataInizioValid=true;
        isDataFineValid=true;
    }
}

function validateButton() {
    validateStruttura();
    validateTipo();
    validateSport();
    validateGiornoPartite();
    validateDayInRangeDate();

    if(isNomeValid && isMinPartecValid && isSquadreValid && isMaxPartecValid && isDataFineValid && isDataInizioValid && isGiornoPartiteValid
    && isStrutturaValid && isTipoValid && isSportValid) {
        return true;
    } else {
        return false;
    }
}

function activeGiornoPartite() {
    document.getElementById('giornoPartite').value = '';

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

    while(dayValid.length > 0) {
        dayValid.pop();
    }
    var days =  ((endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24));

    var giorno = weekday[startDate.getDay()];
    var datalist = null;
    datalist += '<option value="' + giorno + '">' + giorno + '</option>';
    dayValid.push(giorno);

    if(days > 6) days = days - (days - 6);
    while (days > 0) {
        startDate.setDate(startDate.getDate() +1);
        giorno = weekday[startDate.getDay()];
        datalist += '<option value="' + giorno + '">' + giorno + '</option>';
        dayValid.push(giorno);
        days--;
    }
    $("#daysPartite").html(datalist);
}

function validateDayInRangeDate() {
    var i = 0;
    var validate = false;
    var giorno = $("#giornoPartite").val();
    for(i = 0; i < dayValid.length; i++) {
        if(giorno.localeCompare(dayValid[i]) == 0) {
            validate = true;
            break;
        }
    }
    if(!validate) {
        $("#valid_giornoPartite").css("color", "#FF0000");
        $("#valid_giornoPartite").text("Giorno non valido nel periodo di tempo selezionato!");
        isGiornoPartiteValid = false;
    }
}

$(document).ready( function(){
        minDateInizio()
        maxDateInizio();
    }
);

