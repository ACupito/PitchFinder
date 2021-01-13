//Validation var
var isDateValid=false;
var isTimeValid=false;
var isNameValid=false;
var isSurnameValid=false;
var isPlayerNumberValid=false;
var nPlayer;
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
//Data validation
function valiDate(){
    $("#creation-label-data").css("color","#4CAF50");
    isDateValid=true;
}
//Start validation
function validateStart(){
    $("#creation-label-str").css("color","#4CAF50");
    hhStr = $("creation-timestr").val().substring(0,2);
    mmStr = $("creation-timestr").val().substring(3);
}

//End validation
function validateEnd(){
    $("#creation-label-end").css("color","#4CAF50");
    hhEnd = $("creation-timestr").val().substring(0,2);
    mmEnd = $("creation-timestr").val().substring(3);
}
//Time validation legal time for a match
function validateTime(){
    if (hhEnd - hhStr >= 2) {
        if (hhEnd - hhStr == 2 && mmStr - mmEnd < 0) {
            $("#creation-label-str").css("color","#FF0000");
            $("#creation-label-end").css("color","#FF0000");
            isTimeValid=false;
        } else if (hhEnd - hhStr > 2) {
            $("#creation-label-str").css("color","#FF0000");
            $("#creation-label-end").css("color","#FF0000");
            isTimeValid=false;
        }else{
            $("#creation-label-str").css("color","#4CAF50");
            $("#creation-label-end").css("color","#4CAF50");
            isTimeValid=true;
        }
    }else{
        $("#creation-label-str").css("color","#4CAF50");
        $("#creation-label-end").css("color","#4CAF50");
        isTimeValid=true;
    }
}

function validateName1(){

    if( $("#nameG1").val().match("^[a-zA-Z\\s]+$") &&  $("#nameG1").val().length > 2 && $("#nameG1").val().length<12){
        $("#creation-label-nameG1").css({"color":"#4CAF50"});
        isNameValid=true;
    }else{
        $("#creation-label-nameG1").css({"color":"#FF0000"});
        isNameValid=false;
    }
}
function validateName2(){

    if( $("#nameG2").val().match("^[a-zA-Z\\s]+$") &&  $("#nameG2").val().length > 2 && $("#nameG2").val().length<12){
        $("#creation-label-nameG2").css({"color":"#4CAF50"});
        isNameValid=true;
    }else{
        $("#creation-label-nameG2").css({"color":"#FF0000"});
        isNameValid=false;
    }
}
function validateName3(){

    if( $("#nameG3").val().match("^[a-zA-Z\\s]+$") &&  $("#nameG3").val().length > 2 && $("#nameG3").val().length<12){
        $("#creation-label-nameG3").css({"color":"#4CAF50"});
        isNameValid=true;
    }else{
        $("#creation-label-nameG3").css({"color":"#FF0000"});
        isNameValid=false;
    }
}
//Surname validation
function validateSurname1() {

    if(  $("#surnameG1").val().match("^[a-zA-Z\\s]+$") &&  $("#surnameG1").val().length >2 &&   $("#surnameG1").val().length<12){
        $("#creation-label-surnameG1").css("color","#4CAF50");
        isSurnameValid=true;
    }else{
        $("#creation-label-surnameG1").css("color","#FF0000");
        isSurnameValid=false;
    }
}
function validateSurname2() {

    if(  $("#surnameG2").val().match("^[a-zA-Z\\s]+$") &&  $("#surnameG2").val().length >2 &&   $("#surnameG2").val().length<12){
        $("#creation-label-surnameG2").css("color","#4CAF50");
        isSurnameValid=true;
    }else{
        $("#creation-label-surnameG2").css("color","#FF0000");
        isSurnameValid=false;
    }
}
function validateSurname3() {

    if(  $("#surnameG3").val().match("^[a-zA-Z\\s]+$") &&  $("#surnameG3").val().length >2 &&   $("#surnameG3").val().length<12){
        $("#creation-label-surnameG3").css("color","#4CAF50");
        isSurnameValid=true;
    }else{
        $("#creation-label-surnameG3").css("color","#FF0000");
        isSurnameValid=false;
    }
}
//Number of player validation
function validateNPlayer(){
    if($("#creation-player").val().match("^[0-3]$")){
        $("#creation-label-player").css("color","#4CAF50");
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
                document.getElementById("surnameG"+j).style.display='inline';
                document.getElementById("creation-label-surnameG"+j).style.display='inline';
            }else{
                $("#nameG"+j).prop('required',false);
                $("#surnameG"+j).prop('required',false);
                document.getElementById("nameG"+j).style.display='none';
                document.getElementById("creation-label-nameG"+j).style.display='none';
                document.getElementById("surnameG"+j).style.display='none';
                document.getElementById("creation-label-surnameG"+j).style.display='none';
            }
        }

    }else{
        $("#creation-label-player").css("color","#FF0000");
        isPlayerNumberValid=false;
        for(let j=1; j<4; j++){
            document.getElementById("nameG"+j).style.display='none';
            document.getElementById("creation-label-nameG"+j).style.display='none';
            document.getElementById("surnameG"+j).style.display='none';
            document.getElementById("creation-label-surnameG"+j).style.display='none';
        }
    }
}

function validateForm(){
    validateTime();
    if(nPlayer==0){
        isSurnameValid=true;
        isNameValid=true;
    }

    if(isDateValid && isTimeValid && isPlayerNumberValid && isNameValid && isSurnameValid){
        return true;
    }else{
        return false;
    }
}
function addPlayer(){
        var checkboxes = document.getElementsByName("players");
        var numberOfCheckedItems = 0;
        for(var i = 0; i < checkboxes.length; i++)
        {
            if(checkboxes[i].checked)
                numberOfCheckedItems++;
        }
        /*if(numberOfCheckedItems > )
        {
            alert("Impossibile selezionare altri");
            return false;
        } */
}

function showAvailability(){
    if(!(nPlayer==3)){
        document.getElementById("div-Availability").style.display='block';
        var inviare = document.getElementById("creation-data").value;
        inviare += "," +document.getElementById("creation-timestr").value;
        inviare += "," +document.getElementById("creation-timeend").value;
        validateTime();

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
                    alert("Nomi:"+nomi +"\n Cognomi"+cognomi);

                    var father = document.getElementById("form-Availability");
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
            xmlHttpReq.open("GET", "AvailabilityAj?dataTime="+encodeURIComponent(inviare) , true);
            xmlHttpReq.send();

        }else{
            alert(isTimeValid+""+isDateValid+"Inserire correttamente data e/o orari");
        }
    }else{
        alert("Impossibile aggiungere altri giocatori");
        return;
    }
}

$(document).ready( function(){
        minDate()
        maxDate();
    }
);