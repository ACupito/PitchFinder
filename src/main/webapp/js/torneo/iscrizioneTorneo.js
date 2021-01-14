//Script for creation of text box
function Caselle(){
    var n = document.getElementById("nGiocatori").value;
    var nMax = document.getElementById("maxP").value;

    for (var i = 0; i < n; i++) {
        document.getElementById("nome"+i).style.display="block";
        document.getElementById("cognome"+i).style.display="block";
    }
    if (n < nMax) {
        for(var j = n; j < nMax;j++){
            document.getElementById("nome"+j).style.display="none";
            document.getElementById("cognome"+j).style.display="none";
        }
    }
}

//Validation form

    var borderOk = '#080';
    var borderNo = '#f00';
    var nomeOk = false;
    var nomeCapitanoOk = false;
    var cognomeOk = false;
    var nGiocatori = false;


    function validaNGiocatori() {
        var input = document.forms['iscrizione']['nGiocatori'];
        if (input.value > 0) {
            document.getElementById("numeroGiocatori").style.color = borderOk;
            nGiocatoriOk = true;
        } else {
            document.getElementById("numeroGiocatori").style.color = borderNo;
            nGiocatoriOk = false;
        }
        cambiaStatoIscrizione();
    }

    function validaNome() {
        var input = document.forms['iscrizione']['nomeSquadra'];
        if (input.value.match(/^[a-zA-Z_ ]*$/)) {
            document.getElementById("nSquadra").style.color = borderOk;
            nomeOk = true;
        } else {
            document.getElementById("nSquadra").style.color = borderNo;
            nomeOk = false;
        }
            cambiaStatoIscrizione();
    }

    function validaNomeCapitano() {
        var input = document.forms['iscrizione']['nomeCapitano'];
            if (input.value.trim().length > 0 && input.value.match(/^[a-zA-Z_ ]*$/)) {
                document.getElementById("nomeCapitano").style.color = borderOk;
                nomeCapitanoOk = true;
             } else {
                document.getElementById("nomeCapitano").style.color = borderNo;
                nomeCapitanoOk = false;
            }
    cambiaStatoIscrizione();
}

function validaCognomeCapitano() {
    var input = document.forms['iscrizione']['cognomeCapitano'];
    if (input.value.trim().length > 0
        && input.value.match(/^[a-zA-Z_ ]*$/)) {
        document.getElementById("cognomeCapitano").style.color = borderOk;
        cognomeOk = true;
    } else {
        document.getElementById("cognomeCapitano").style.color = borderNo;
        cognomeOk = false;
    }
    cambiaStatoIscrizione();
}




function cambiaStatoIscrizione() {
        if  (nomeOk&&cognomeOk&&nomeCapitanoOk&&nGiocatori) {
            document.getElementById('conferma').disabled = false;
        } else {
            document.getElementById('conferma').disabled = true;
        }
}
