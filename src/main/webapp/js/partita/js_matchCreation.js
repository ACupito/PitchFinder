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
        alert("Data:"+ isDateValid+"Time:"+isTimeValid+"PlayerN"+isPlayerNumberValid+"NOMI"+isNameValid+"COGNOMI"+isSurnameValid);
        return false;
    }
}

$(document).ready( function(){
        minDate()
        maxDate();
    }
);