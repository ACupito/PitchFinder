//Validation var
var isDateValid=false;
var isTimeValid=false;
var isNameValid=false;
var isSurnameValid=false;
var isPlayerNumberValid=false;
var numberOfCheckedItems = 0;
var nPlayer=0;
var hhStr;
var mmStr;
var hhEnd;
var mmEnd;

//Setting min of #creation-data
function minDate() {
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();

    currentDate = yyyy + '-' + mm + '-' + dd;
    $('#creation-data').attr("min",currentDate);
}
//Setting max of #creation-data
function maxDate(){
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();
    currentDate = (yyyy+1) + '-' + mm + '-' + dd;

    $('#creation-data').attr("max",currentDate);
}
function minMaxTime(){
    $('.timepicker').timepicker({
        timeFormat: 'HH:mm',
        minTime: '09:00', //
        maxTime: '23:00',
        startTime: '09:00',
        interval: 60, // 60 minutes
        dynamic: false,
        dropdown: true,
        scrollbar: true,

    });

}
//Data validation
function valiDate(){
    if(!document.getElementById("creation-data").value.match("^(.*[-])[0-9]*$")){
        $("#creation-label-data").css("color","#FF0000");
        $("#small-creation-data").text("La data non è selezionata");
        $("#small-creation-data").css("color","#FF0000");
        isDateValid=false;
    }else if(document.getElementById("creation-data").value.match("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$")){
        $("#creation-label-data").css("color","#4CAF50");
        $("#small-creation-data").text("La data è valida, rispetta il formato");
        $("#small-creation-data").css("color","#4CAF50");
        isDateValid=true;
    }else{
        $("#creation-label-data").css("color","#FF0000");
        $("#small-creation-data").text("La data non rispetta il formato");
        $("#small-creation-data").css("color","#FF0000");
        isDateValid=false;
    }
    var father = document.getElementById("form-Availability");
    if(father.hasChildNodes()){
        for(j=0;j<father.childElementCount;j++){
            father.removeChild(father.lastChild)
            j--;
        }
    }
}
//Start validation
function validateStart(){
    var father = document.getElementById("form-Availability");
    if(father.hasChildNodes()){
        for(j=0;j<father.childElementCount;j++){
            father.removeChild(father.lastChild)
            j--;
        }
    }
    if(document.getElementById("creation-timestr").value.match("^[0-2]{1}[0-9]{1}\\:[0-6]{1}[0-9]{1}$")){
        $("#creation-label-str").css("color","#4CAF50");
        hhStr = document.getElementById("creation-timestr").value.substring(0,2);
        mmStr = document.getElementById("creation-timestr").value.substring(3);
        $("#small-creation-timestr").text("Orario di inizio valido, rispetta il formato");
        $("#small-creation-timestr").css("color","#4CAF50");
        return true;
    }else{
        $("#creation-label-str").css("color","#FF0000");
        $("#small-creation-timestr").text("L'orario di inizio non rispetta il formato");
        $("#small-creation-timestr").css("color","#FF0000");
        return false;
    }
}

//End validation
function validateEnd(){
    var father = document.getElementById("form-Availability");
    if(father.hasChildNodes()){
        for(j=0;j<father.childElementCount;j++){
            father.removeChild(father.lastChild)
            j--;
        }
    }

    if(document.getElementById("creation-timeend").value.match("^[0-2]{1}[0-9]{1}\\:[0-6]{1}[0-9]{1}$")){
        $("#creation-label-end").css("color","#4CAF50");
        hhEnd = document.getElementById("creation-timeend").value.substring(0,2);
        mmEnd = document.getElementById("creation-timeend").value.substring(3);
        $("#small-creation-timeend").text("Orario di fine valido, rispetta il formato");
        $("#small-creation-timeend").css("color","#4CAF50");
        return true;
    }else{
        $("#creation-label-end").css("color","#FF0000");
        $("#small-creation-timeend").text("L'orario di fine non rispetta il formato");
        $("#small-creation-timeend").css("color","#FF0000");
        return false;
    }

}
//Time validation legal time for a match
function validateTime(){
    //Regex su entrambi i campi
    if (hhEnd - hhStr >= 2) {
        if (hhEnd - hhStr == 2 && mmStr - mmEnd < 0) {
            $("#creation-label-str").css("color","#FF0000");
            $("#creation-label-end").css("color","#FF0000");
            $("#small-creation-timestr").text("Orari non validi, max 2 ore");
            $("#small-creation-timestr").css("color","#FF0000");
            $("#small-creation-timeend").text("Orari non validi, max 2 ore");
            $("#small-creation-timeend").css("color","#FF0000");
            isTimeValid=false;
        }else if (hhEnd - hhStr > 2) {
            $("#creation-label-str").css("color","#FF0000");
            $("#creation-label-end").css("color","#FF0000");
            $("#small-creation-timestr").text("Orari non validi, max 2 ore");
            $("#small-creation-timestr").css("color","#FF0000");
            $("#small-creation-timeend").text("Orari non validi, max 2 ore");
            $("#small-creation-timeend").css("color","#FF0000");
            isTimeValid=false;
        }else{
            $("#creation-label-str").css("color","#4CAF50");
            $("#creation-label-end").css("color","#4CAF50");
            $("#small-creation-timestr").text("Orario di inizio valido, rispetta il formato");
            $("#small-creation-timestr").css("color","#4CAF50");
            $("#small-creation-timeend").text("Orario di fine valido, rispetta il formato");
            $("#small-creation-timeend").css("color","#4CAF50");
            isTimeValid=true;
        }
    }else if(hhEnd - hhStr < 1){
        $("#creation-label-str").css("color","#FF0000");
        $("#creation-label-end").css("color","#FF0000");
        $("#small-creation-timestr").text("Orari non validi, min 1 ora");
        $("#small-creation-timestr").css("color","#FF0000");
        $("#small-creation-timeend").text("Orari non validi, min 1 ora");
        $("#small-creation-timeend").css("color","#FF0000");
        isTimeValid=false;
    }else{
        $("#creation-label-str").css("color","#4CAF50");
        $("#creation-label-end").css("color","#4CAF50");
        $("#small-creation-timestr").text("Orario di inizio valido, rispetta il formato");
        $("#small-creation-timestr").css("color","#4CAF50");
        $("#small-creation-timeend").text("Orario di fine valido, rispetta il formato");
        $("#small-creation-timeend").css("color","#4CAF50");
        isTimeValid=true;
    }
}
function clickTimeValidate(){
    start = validateStart();
    end = validateEnd();
    if( start && end){
        validateTime();
    }
}
function validateName1(){

    if( $("#nameG1").val().match("^[a-zA-Z\\s]+$")){
        if($("#nameG1").val().length <= 1 || $("#nameG1").val().length>=16) {
            $("#creation-label-nameG1").css({"color": "#FF0000"});
            $("#small-nameG1").text("La lunghezza del giocatore non è valida");
            $("#small-nameG1").css("color", "#FF0000");
            isNameValid = false;
        }else{
            $("#creation-label-nameG1").css({"color":"#4CAF50"});
            $("#small-nameG1").text("Nome Valido");
            $("#small-nameG1").css("color","#4CAF50");
            isNameValid=true;
        }
    }else{
        $("#creation-label-nameG1").css({"color":"#FF0000"});
        $("#small-nameG1").text("Il formato del giocatore non è valido. ");
        $("#small-nameG1").css("color","#FF0000");
        isNameValid=false;
    }
}
function validateName2(){
    if( $("#nameG2").val().match("^[a-zA-Z\\s]+$")){
        if($("#nameG2").val().length <= 1 || $("#nameG2").val().length>=16) {
            $("#creation-label-nameG2").css({"color": "#FF0000"});
            $("#small-nameG2").text("La lunghezza del giocatore non è valida");
            $("#small-nameG2").css("color", "#FF0000");
            isNameValid = false;
        }else{
            $("#creation-label-nameG2").css({"color":"#4CAF50"});
            $("#small-nameG2").text("Nome Valido");
            $("#small-nameG2").css("color","#4CAF50");
            isNameValid=true;
        }
    }else{
        $("#creation-label-nameG2").css({"color":"#FF0000"});
        $("#small-nameG2").text("Il formato del giocatore non è valido. ");
        $("#small-nameG2").css("color","#FF0000");
        isNameValid=false;
    }
}
function validateName3(){
    if( $("#nameG3").val().match("^[a-zA-Z\\s]+$")){
        if($("#nameG3").val().length <= 1 || $("#nameG3").val().length>=16) {
            $("#creation-label-nameG3").css({"color": "#FF0000"});
            $("#small-nameG3").text("La lunghezza del giocatore non è valida");
            $("#small-nameG3").css("color", "#FF0000");
            isNameValid = false;
        }else{
            $("#creation-label-nameG3").css({"color":"#4CAF50"});
            $("#small-nameG3").text("Nome Valido");
            $("#small-nameG3").css("color","#4CAF50");
            isNameValid=true;
        }
    }else{
        $("#creation-label-nameG3").css({"color":"#FF0000"});
        $("#small-nameG3").text("Il formato del giocatore non è valido. ");
        $("#small-nameG3").css("color","#FF0000");
        isNameValid=false;
    }
}
//Surname validation
function validateSurname1() {
    if( $("#surnameG1").val().match("^[a-zA-Z\\s]+$")){
        if($("#surnameG1").val().length <= 1 || $("#surnameG1").val().length>=16) {
            $("#creation-label-surnameG1").css({"color": "#FF0000"});
            $("#small-surnameG1").text("La lunghezza del giocatore non è valida");
            $("#small-surnameG1").css("color", "#FF0000");
            isNameValid = false;
        }else{
            $("#creation-label-surnameG1").css({"color":"#4CAF50"});
            $("#small-surnameG1").text("Nome Valido");
            $("#small-surnameG1").css("color","#4CAF50");
            isNameValid=true;
        }
    }else{
        $("#creation-label-surnameG1").css({"color":"#FF0000"});
        $("#small-surnameG1").text("Il formato del giocatore non è valido. ");
        $("#small-surnameG1").css("color","#FF0000");
        isNameValid=false;
    }
}
function validateSurname2() {
    if( $("#surnameG2").val().match("^[a-zA-Z\\s]+$")){
        if($("#surnameG2").val().length <= 1 || $("#surnameG2").val().length>=16) {
            $("#creation-label-surnameG2").css({"color": "#FF0000"});
            $("#small-surnameG2").text("La lunghezza del giocatore non è valida");
            $("#small-surnameG2").css("color", "#FF0000");
            isNameValid = false;
        }else{
            $("#creation-label-surnameG2").css({"color":"#4CAF50"});
            $("#small-surnameG2").text("Nome Valido");
            $("#small-surnameG2").css("color","#4CAF50");
            isNameValid=true;
        }
    }else{
        $("#creation-label-surnameG2").css({"color":"#FF0000"});
        $("#small-surnameG2").text("Il formato del giocatore non è valido. ");
        $("#small-surnameG2").css("color","#FF0000");
        isNameValid=false;
    }
}
function validateSurname3() {
    if( $("#surnameG3").val().match("^[a-zA-Z\\s]+$")){
        if($("#surnameG3").val().length <= 1 || $("#surnameG3").val().length>=16) {
            $("#creation-label-surnameG3").css({"color": "#FF0000"});
            $("#small-surnameG3").text("La lunghezza del giocatore non è valida");
            $("#small-surnameG3").css("color", "#FF0000");
            isNameValid = false;
        }else{
            $("#creation-label-surnameG3").css({"color":"#4CAF50"});
            $("#small-surnameG3").text("Nome Valido");
            $("#small-surnameG3").css("color","#4CAF50");
            isNameValid=true;
        }
    }else{
        $("#creation-label-surnameG3").css({"color":"#FF0000"});
        $("#small-surnameG3").text("Il formato del giocatore non è valido. ");
        $("#small-surnameG3").css("color","#FF0000");
        isNameValid=false;
    }
}
//Number of player validation
function validateNPlayer(){
    var valoreN = $("#creation-player").val();
    if(parseInt(valoreN) < 0 || parseInt(valoreN) > 3){
        $("#creation-label-player").css("color","#FF0000");
        $("#small-creation-player").text(" Valore di Numero Giocatori non valido. ");
        $("#small-creation-player").css("color","#FF0000");
        isPlayerNumberValid=false;
        for(let j=1; j<4; j++){
            document.getElementById("nameG"+j).style.display='none';
            document.getElementById("creation-label-nameG"+j).style.display='none';
            document.getElementById("small-nameG"+j).style.display='none';
            document.getElementById("surnameG"+j).style.display='none';
            document.getElementById("creation-label-surnameG"+j).style.display='none';
            document.getElementById("small-surnameG"+j).style.display='none';
        }
    }else if($("#creation-player").val().match("^[0-3]$")){
        $("#creation-label-player").css("color","#4CAF50");
        $("#small-creation-player").text("Formato di Numero Giocatori valido. ");
        $("#small-creation-player").css("color","#4CAF50");
        isPlayerNumberValid=true;
        nPlayer = $("#creation-player").val();
        if(nPlayer==3) {
            document.getElementById("div-Availability").style.display='none';
        }
        for(let j=1; j<4;j++){
            if(j<=nPlayer){
                document.getElementById("nameG"+j).style.display='inline';
                $("#nameG"+j).prop('required',true);
                $("#surnameG"+j).prop('required',true);
                document.getElementById("creation-label-nameG"+j).style.display='inline';
                document.getElementById("small-nameG"+j).style.display='inline';
                document.getElementById("surnameG"+j).style.display='inline';
                document.getElementById("creation-label-surnameG"+j).style.display='inline';
                document.getElementById("small-surnameG"+j).style.display='inline';
            }else{
                $("#nameG"+j).prop('required',false);
                $("#surnameG"+j).prop('required',false);
                document.getElementById("nameG"+j).style.display='none';
                document.getElementById("creation-label-nameG"+j).style.display='none';
                document.getElementById("small-nameG"+j).style.display='none';
                document.getElementById("surnameG"+j).style.display='none';
                document.getElementById("creation-label-surnameG"+j).style.display='none';
                document.getElementById("small-surnameG"+j).style.display='none';
            }
        }

    }else{
        $("#creation-label-player").css("color","#FF0000");
        $("#small-creation-player").text("Formato di Numero Giocatori non valido. ");
        $("#small-creation-player").css("color","#FF0000");
        isPlayerNumberValid=false;
        for(let j=1; j<4; j++){
            document.getElementById("nameG"+j).style.display='none';
            document.getElementById("creation-label-nameG"+j).style.display='none';
            document.getElementById("small-nameG"+j).style.display='none';
            document.getElementById("surnameG"+j).style.display='none';
            document.getElementById("creation-label-surnameG"+j).style.display='none';
            document.getElementById("small-surnameG"+j).style.display='none';
        }
    }
}

function validateForm(){
    valiDate();
    validateNPlayer();
    validateStart();
    clickTimeValidate();

    if(nPlayer==0){
        isSurnameValid=true;
        isNameValid=true;
    }

    if(isDateValid && isTimeValid && isPlayerNumberValid && isNameValid && isSurnameValid){
        document.getElementById("creation-player").setAttribute("value",nPlayer+numberOfCheckedItems);
        var checkPlayer = document.getElementsByName("players");
        var nplayerFInal = nPlayer;
        document.getElementById("creation-player").removeAttribute("disabled");
        document.getElementById("creation-player").value = parseInt(nPlayer) + parseInt(numberOfCheckedItems);
        if(nplayerFInal<3){
            for(var valor of checkPlayer.values()){
                if(valor.checked){ //Updating textbox for players name & surname
                    nplayerFInal++;
                    var splitted= valor.value.split(",");
                    document.getElementById("nameG"+nplayerFInal).value = splitted[0];
                    document.getElementById("surnameG"+nplayerFInal).value = splitted[1];
                }
            }
        }
        return true;
    }else{
        return false;
    }
}
function addPlayer(){
    var legalCheck = 3 - nPlayer;
    var checkboxes = document.getElementsByName("players");
    numberOfCheckedItems = 0;

    for(var i = 0; i < checkboxes.length; i++) {
        if(checkboxes[i].checked){
            document.getElementById("creation-player").setAttribute("disabled","true");
            numberOfCheckedItems++;
        }
    }
    if(numberOfCheckedItems==0){
        document.getElementById("creation-player").removeAttribute("disabled");
    }
    if(numberOfCheckedItems==legalCheck) {
        for(var i = 0; i < checkboxes.length; i++) {
            if(document.getElementById("player"+i).checked){
            }else{
                document.getElementById("player"+i).disabled = true;
            }
        }
    }else{
        for(var i = 0; i < checkboxes.length; i++) {
            document.getElementById("player"+i).disabled = false;
        }
    }


}

function showAvailability(){
    if(!(nPlayer==3)){
        document.getElementById("div-Availability").style.display='block';
        var inviare = document.getElementById("creation-data").value;
        inviare += "," +document.getElementById("creation-timestr").value;
        inviare += "," +document.getElementById("creation-timeend").value;
        validateTime();
        validateNPlayer();

        if(isTimeValid && isDateValid){
            var xmlHttpReq = new XMLHttpRequest();
            var data;
            var nomi;
            var cognomi;
            xmlHttpReq.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    data = JSON.parse(this.response);
                    nomi = data.nomi.toString().split(',');
                    cognomi = data.cognomi.toString().split(',');
                    var father = document.getElementById("form-Availability");
                    if(father.hasChildNodes()){
                        for(j=0;j<father.childElementCount;j++){
                            father.removeChild(father.lastChild)
                            j--;
                        }
                    }
                    if(nomi[0].length<=2 || cognomi[0].length<=2){
                        var title = document.createElement("h4");
                        var titleText = document.createTextNode("Non ci sono giocatori disponibili per la data e fascia oraria selezionata");
                        title.appendChild(titleText);
                        father.appendChild(title);
                    }else{
                        var title = document.createElement("h4");
                        var titleText = document.createTextNode("Lista dei giocatori disponibili per questa partita");
                        title.appendChild(titleText);
                        father.appendChild(title);

                        for(i=0;i<=nomi.length-1;i++){
                            var child = document.createElement("input");

                            child.setAttribute("type", "checkbox");
                            child.setAttribute("value", nomi[i] +","+cognomi[i]);
                            child.setAttribute("id","player"+i);
                            child.setAttribute("name","players");
                            father.appendChild(child);
                            var label = document.createElement("label");
                            label.setAttribute("for","player"+i);
                            var testoLabel = document.createTextNode(nomi[i] +","+cognomi[i]);
                            label.appendChild(testoLabel);

                            father.appendChild(label);
                            var br = document.createElement("br");
                            father.appendChild(br);
                            document.getElementById("player"+i).addEventListener("click", addPlayer)
                        }
                    }
                }
            }
            xmlHttpReq.open("GET", "AvailabilityAj?dataTime="+encodeURIComponent(inviare) , true);
            xmlHttpReq.send();

        }else{
            alert("Inserire correttamente data e/o orari");
        }
    }else{
        alert("Impossibile aggiungere altri giocatori");
        return;
    }
}

$(document).ready( function(){
        minDate()
        maxDate();
        minMaxTime();
    }
);