

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

/**** EVENT Javascript *****/
var isDateValidEvento=false;
var isTimeValid=false;
var isNameValid=false;
var isSurnameValid=false;
var isPlayerNumberValid=false;
var numberOfCheckedItems = 0;
var nPlayer=0;
var hhStrEvento;
var mmStrEvento;
var hhEndEvento;
var mmEndEvento;

    /** Name Evento **/
    //fill


    /** Date Evento **/
    /** min Date **/
    function minDateEvento() {
        var currentDate = new Date();
        var dd = String(currentDate.getDate()).padStart(2, '0');
        var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = currentDate.getFullYear();

        currentDate = yyyy + '-' + mm + '-' + dd;
        $('#creation-data-Evento').attr("min",currentDate);
    }
    /** max Date **/
    function maxDateEvento(){
        var currentDate = new Date();
        var dd = String(currentDate.getDate()).padStart(2, '0');
        var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
        var yyyy = currentDate.getFullYear();
        currentDate = (yyyy+10) + '-' + mm + '-' + dd;

        $('#creation-data-Evento').attr("max",currentDate);
    }
    /** valiDateEvento **/
    function valiDateEvento(){
        if(document.getElementById("creation-data-Evento").value.match("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$")){
            $("#creation-label-data-Evento").css("color","#4CAF50");
            $("#date-evento-valid").text("La data è valida, rispetta il formato");
            $("#date-evento-valid").css("color","#4CAF50");
            isDateValidEvento=true;
        }else{
            $("#creation-label-data-Evento").css("color","#FF0000");
            $("#date-evento-valid").text("La data non rispetta il formato");
            $("#date-evento-valid").css("color","#FF0000");
            isDateValidEvento=false;
        }
    }

function validateEvento(){

}

    $(document).ready(function(){
        minDateEvento();
        maxDateEvento();
    });

/**** END EVENT Javascript *****/

/**** MATCH Javascript *****/


/**** END MATCH Javascript *****/
