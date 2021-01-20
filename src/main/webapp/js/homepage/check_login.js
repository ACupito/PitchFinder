var borderOK = '3px solid green';
var borderError = '3px solid red';
var usernameOk = false;

$(document).ready(function() {

    $("#username").on("input", function() {

        var input = $("#username");
        var username = input.val();

        if (username.length < 1 || username.length > 50) {
            $("#username").css("border-bottom", borderError);
            usernameOk = false;
            $("#div_username").text("La username inserita non rispetta la lunghezza corretta");
            $("#div_username").css("color", "red");
        }

        if (!username.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)) {
            $("#username").css("border-bottom", borderError);
            usernameOk = false;
            $("#div_username").text("La username inserita non rispetta il formato corretto");
            $("#div_username").css("color", "red");
        }

        if (username.length >= 1
            && username.length <= 50
            && username.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)) {

            $("#username").css("border-bottom", borderOK);
            usernameOk = true;
            $("#div_username").text("La username rispetta formato e lunghezza corretti");
            $("#div_username").css("color", "green");
        }

        cambiaStatoLogin();
    });
});

function cambiaStatoLogin() {

    if (usernameOk) {
        document.getElementById('login').disabled = false;
    } else {
        document.getElementById('login').disabled = true;
    }
}