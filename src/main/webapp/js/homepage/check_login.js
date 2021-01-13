var borderOK = '2px solid green';
var borderError = '2px solid red';
var usernameOk = false;

$(document).ready(function() {

    $("#username").on("input", function() {

        var input = $("#username");
        var username = input.val();

        if (username.length >= 1
            && username.length <= 50
            && username.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)) {

            $("#username").css("border-bottom", borderOK);
            usernameOk = true;

        } else {

            $("#username").css("border-bottom", borderError);
            usernameOk = false;
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