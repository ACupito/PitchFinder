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
    var nGiocatoriOk = false;
    var giocatoriOk = false;
    var borderNoNome = '2px solid #f00';
    var borderOkNome = '2px solid #080';

    function validaNGiocatori() {
        var input = document.forms['iscrizione']['nGiocatori'];
        var max = document.forms['iscrizione']['maxP'] ;
        var min = document.forms['iscrizione']['minP'] ;
        if (input.value > 0 && input.value<99&&input.value>= min.value &&input.value <= max.value) {
            document.getElementById("numeroGiocatori").style.color = borderOk;
            nGiocatoriOk = true;
        } else {
            document.getElementById("numeroGiocatori").style.color = borderNo;
            nGiocatoriOk = false;
        }
        cambiaStatoIscrizione();
    }

    function validaGiocatori(){
        var nGiocatori = document.forms['iscrizione']['nGiocatori'];
        var nome = false;
        var cognome = false;
        for(var i = 0; i < nGiocatori.value; i++) {

            var nome = document.forms['iscrizione']['nome'+i];
            var cognome = document.forms['iscrizione']['cognome'+i];

            if (nome.value.length>0&&nome.value.length<10&&nome.value.match(/^[a-zA-Z\\\\s]+$/)) {
                   nome = true;
                   document.getElementById('nome'+i).style.border = borderOkNome;
            }
            else {
                nome = false;
                document.getElementById('nome'+i).style.border = borderNoNome;
            }

            if (cognome.value.length>0&&cognome.value.length<20&&cognome.value.match(/^[a-zA-Z\\\\s]+$/)) {
                cognome = true;
                document.getElementById('cognome'+i).style.border = borderOkNome;
            }
            else {
                cognome = false;
                document.getElementById('cognome'+i).style.border = borderNoNome;
            }

            if (cognome && nome) {
                giocatoriOk = true;
                document.getElementById('nomecognome').style.color = borderOk;
            } else {
                giocatoriOk = false;
                document.getElementById('nomecognome').style.color = borderNo;
            }


         cambiaStatoIscrizione();   }
    }

    function validaNome() {
        var input = document.forms['iscrizione']['nomeSquadra'];
        if (input.value.match(/^[a-zA-Z_ ]*$/)&&input.value.length>0&&input.value.length<50) {
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
            if (input.value.length > 0 && input.value.match(/^[a-zA-Z_ ]*$/)&&input.value.length < 10) {
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
    if (input.value.length > 0
        && input.value.match(/^[a-zA-Z_ ]*$/)&&input.value.length < 20  ) {
        document.getElementById("cognomeCapitano").style.color = borderOk;
        cognomeOk = true;
    } else {
        document.getElementById("cognomeCapitano").style.color = borderNo;
        cognomeOk = false;
    }
    cambiaStatoIscrizione();
}

function cambiaStatoIscrizione() {
        if  (nomeOk&&cognomeOk&&nomeCapitanoOk&&nGiocatoriOk&&giocatoriOk) {
            document.getElementById('conferma').disabled = false;
        } else {
            document.getElementById('conferma').disabled = true;
        }
}
