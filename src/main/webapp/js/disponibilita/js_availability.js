//Validation var
var isDateValid=false;
var isTimeValid=false;
var hhStr;
var mmStr;
var hhEnd;
var mmEnd;

//Setting min of #creation-data
function minDateDisp() {
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();

    currentDate = yyyy + '-' + mm + '-' + dd;
    $('#data').attr("min",currentDate);
}
//Setting max of #creation-data
function maxDateDisp(){
    var currentDate = new Date();
    var dd = String(currentDate.getDate()).padStart(2, '0');
    var mm = String(currentDate.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = currentDate.getFullYear();
    currentDate = (yyyy+1) + '-' + mm + '-' + dd;

    $('#data').attr("max",currentDate);
}
function minMaxTimeDisp() {
    $('.timepicker').timepicker({
        timeFormat: 'HH:mm',
        minTime: '09:00', //
        maxTime: '23:00',
        startTime: '09:00',
        interval: 60, // 60 minutes
        dynamic: true,
        dropdown: true,
        scrollbar: true,

    });
}

//Data validation DONE
    function valiDateDisp() {
        if (!document.getElementById("data").value.match("^(.*[-])[0-9]*$")) {
            $("#data").css("color", "#FF0000");
            $("#small-creation-data").text("La data non è selezionata");
            $("#small-creation-data").css("color", "#FF0000");
            isDateValid = false;
        } else if (document.getElementById("data").value.match("^[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}$")) {
            $("#data").css("color", "#4CAF50");
            $("#small-creation-data").text("La data è valida, rispetta il formato");
            $("#small-creation-data").css("color", "#4CAF50");
            isDateValid = true;
        } else {
            $("#data").css("color", "#FF0000");
            $("#small-creation-data").text("La data non rispetta il formato");
            $("#small-creation-data").css("color", "#FF0000");
            isDateValid = false;
        }

    }

    //Start validation
    function validateStartDisp() {


        if (document.getElementById("inizio").value.match("^[0-2]{1}[0-9]{1}\\:[0-6]{1}[0-9]{1}$")) {
            $("#inizio").css("color", "#4CAF50");
            hhStr = document.getElementById("inizio").value.substring(0, 2);
            mmStr = document.getElementById("inizio").value.substring(3);
            $("#small-creation-timestr").text("Orario di inizio valido, rispetta il formato");
            $("#small-creation-timestr").css("color", "#4CAF50");
        } else {
            $("#inizio").css("color", "#FF0000");
            $("#small-creation-timestr").text("L'orario di inizio non rispetta il formato");
            $("#small-creation-timestr").css("color", "#FF0000");
        }

    }

//End validation
    function validateEndDisp() {

        if (document.getElementById("fine").value.match("^[0-2]{1}[0-9]{1}\\:[0-6]{1}[0-9]{1}$")) {
            $("#fine").css("color", "#4CAF50");
            hhEnd = document.getElementById("fine").value.substring(0, 2);
            mmEnd = document.getElementById("fine").value.substring(3);
            $("#small-creation-timeend").text("Orario di fine valido, rispetta il formato");
            $("#small-creation-timeend").css("color", "#4CAF50");
        } else {
            $("#fine").css("color", "#FF0000");
            $("#small-creation-timeend").text("Orario di fine non rispetta il formato");
            $("#small-creation-timeend").css("color", "#FF0000");
        }


    }

//Time validation legal time for a match
    function validateTimeDisp() {
        //Regex su entrambi i campi

        if(hhEnd - hhStr == 0 || hhEnd==null || hhStr==null){
            $("#inizio").css("color","#FF0000");
            $("#fine").css("color","#FF0000");
            $("#small-creation-timestr").text("Orari non validi, min 1 ora");
            $("#small-creation-timestr").css("color","#FF0000");
            $("#small-creation-timeend").text("Orari non validi, min 1 ora");
            $("#small-creation-timeend").css("color","#FF0000");
            isTimeValid=false;
        }else if (hhStr - hhEnd > 0) {
                $("#inizio").css("color", "#FF0000");
                $("#fine").css("color", "#FF0000");
                $("#small-creation-timestr").css("color", "#FF0000");
                $("#small-creation-timeend").text("Orario di inizio maggiore ad Orario di fine");
                $("#small-creation-timeend").css("color", "#FF0000");
                isTimeValid = false;
            } else {
                $("#inizio").css("color", "#4CAF50");
                $("#fine").css("color", "#4CAF50");
                $("#small-creation-timestr").text("Orario di inizio valido, rispetta il formato");
                $("#small-creation-timestr").css("color", "#4CAF50");
                $("#small-creation-timeend").text("Orario di fine valido, rispetta il formato");
                $("#small-creation-timeend").css("color", "#4CAF50");
                isTimeValid = true;
            }
    }

function clickTimeValidateDispIn() {

    validateStartDisp();
    validateTimeDisp();

}
    function clickTimeValidateDispFi() {

        validateStartDisp();
        validateEndDisp();
        validateTimeDisp();

    }

    function validateFormDisp(){
        valiDateDisp();
        clickTimeValidateDispFi();

        if(isDateValid && isTimeValid){
            return true;
        }else{
            return false;
        }
    }

    $(document).ready(function () {
            minDateDisp()
            maxDateDisp();
            minMaxTimeDisp();
        }
    );

