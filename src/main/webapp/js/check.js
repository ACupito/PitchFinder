var borderOK = '1px solid green';
var borderError = '1px solid red';
var emailOk = false;
var usernameOk = false;
var nomeOk = false;
var cognomeOk = false;
var passwordOk = false;
var dataOk = false;


$(document).ready(function() {

    $("#email").on("input", function() {

        var input = $("#email");
        var email = input.val();

        if (email.length >= 1
            && email.length <= 50
            && email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/)) {

            $("#email").css("border-bottom", borderOK);
            emailOk = true;

        } else {

            $("#email").css("border-bottom", borderError);
            emailOk = false;
        }

        cambiaStatoRegistrami();
    });

    $("#username").on("input", function() {

        var input = $("#username");
        var username = input.val();

        if (username.length >= 1
            && username.length <= 50
            && username.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)
            && username.substring(0, 5).toLowerCase().localeCompare("admin") != 0) {

            $("#username").css("border-bottom", borderOK);
            usernameOk = true;

        } else {

            $("#username").css("border-bottom", borderError);
            usernameOk = false;
        }

        cambiaStatoRegistrami();
    });

    $("#nome").on("input", function() {

        var input = $("#nome");
        var nome = input.val();

        if (nome.length >= 1
            && nome.length <= 50
            && nome.match(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/)) {

            $("#nome").css("border-bottom", borderOK);
            nomeOk = true;

        } else {

            $("#nome").css("border-bottom", borderError);
            nomeOk = false;
        }

        cambiaStatoRegistrami();
    });

    $("#cognome").on("input", function() {

        var input = $("#cognome");
        var cognome = input.val();

        if (cognome.length >= 1
            && cognome.length <= 50
            && cognome.match(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/)) {

            $("#cognome").css("border-bottom", borderOK);
            cognomeOk = true;

        } else {

            $("#cognome").css("border-bottom", borderError);
            cognomeOk = false;
        }

        cambiaStatoRegistrami();

    });

    $("#password").on("input", function() {

        var input = $("#password");
        var password = input.val();

        if (password.length >= 1
            && password.length <= 50
            && password.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)) {

            $("#password").css("border-bottom", borderOK);
            passwordOk = true;

        } else {

            $("#password").css("border-bottom", borderError);
            passwordOk = false;
        }

        cambiaStatoRegistrami();
    });

    $("#data").on("input", function() {

        $("#data").css("border-bottom", borderOK);
        dataOk = true;

        cambiaStatoRegistrami();
    });
});

function cambiaStatoRegistrami() {

    if (emailOk && usernameOk && nomeOk && cognomeOk && passwordOk && dataOk) {
        document.getElementById('register').disabled = false;
    } else {
        document.getElementById('register').disabled = true;
    }
}