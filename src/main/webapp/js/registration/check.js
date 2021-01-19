var borderOK = '3px solid green';
var borderError = '3px solid red';
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

        if (email.length < 1 || email.length > 50) {
            $("#email").css("border-bottom", borderError);
            emailOk = false;
            $("#div_email").text("L'email inserita non rispetta la lunghezza corretta");
            $("#div_email").css("color", "red");
        }

        if (!email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/)) {
            $("#email").css("border-bottom", borderError);
            emailOk = false;
            $("#div_email").text("L'email inserita non rispetta il formato richiesto");
            $("#div_email").css("color", "red");
        }

        if (email.length >= 1
            && email.length <= 50
            && email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/)) {

            $("#email").css("border-bottom", borderOK);
            emailOk = true;
            $("#div_email").text("L'email inserita rispetta lunghezza e formato corretti");
            $("#div_email").css("color", "green");
        }

        cambiaStatoRegistrami();
    });

    $("#username_").on("input", function() {

        var input = $("#username_");
        var username = input.val();

        if (username.length < 1 || username.length > 50) {
            $("#username_").css("border-bottom", borderError);
            usernameOk = false;
            $("#div_username_").text("La username inserita non rispetta la lunghezza corretta");
            $("#div_username_").css("color", "red");
        }

        if (!username.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/) ||
             username.substring(0, 5).toLowerCase().localeCompare("admin") == 0) {
            $("#username_").css("border-bottom", borderError);
            usernameOk = false;
            $("#div_username_").text("La username inserita non rispetta il formato richiesto");
            $("#div_username_").css("color", "red");
        }

        if (username.length >= 1
            && username.length <= 50
            && username.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)
            && username.substring(0, 5).toLowerCase().localeCompare("admin") != 0) {

            $("#username_").css("border-bottom", borderOK);
            usernameOk = true;
            $("#div_username_").text("La username inserita rispetta lunghezza e formato corretti");
            $("#div_username_").css("color", "green");
        }

        cambiaStatoRegistrami();
    });

    $("#nome").on("input", function() {

        var input = $("#nome");
        var nome = input.val();

        if (nome.length < 1 || nome.length > 50) {
            $("#nome").css("border-bottom", borderError);
            nomeOk = false;
            $("#div_nome").text("Il nome inserito non rispetta la lunghezza corretta");
            $("#div_nome").css("color", "red");
        }

        if (!nome.match(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/)) {
            $("#nome").css("border-bottom", borderError);
            nomeOk = false;
            $("#div_nome").text("Il nome inserito non rispetta il formato richiesto");
            $("#div_nome").css("color", "red");
        }

        if (nome.length >= 1
            && nome.length <= 50
            && nome.match(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/)) {

            $("#nome").css("border-bottom", borderOK);
            nomeOk = true;
            $("#div_nome").text("Il nome inserito rispetta lunghezza e formato corretti");
            $("#div_nome").css("color", "green");
        }

        cambiaStatoRegistrami();
    });

    $("#cognome").on("input", function() {

        var input = $("#cognome");
        var cognome = input.val();

        if (cognome.length < 1 || cognome.length > 50) {
            $("#cognome").css("border-bottom", borderError);
            cognomeOk = false;
            $("#div_cognome").text("Il cognome inserito non rispetta la lunghezza corretta");
            $("#div_cognome").css("color", "red");
        }

        if (!cognome.match(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/)) {
            $("#cognome").css("border-bottom", borderError);
            cognomeOk = false;
            $("#div_cognome").text("Il cognome inserito non rispetta il formato richiesto");
            $("#div_cognome").css("color", "red");
        }

        if (cognome.length >= 1
            && cognome.length <= 50
            && cognome.match(/^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$/)) {

            $("#cognome").css("border-bottom", borderOK);
            cognomeOk = true;
            $("#div_cognome").text("Il cognome inserito rispetta lunghezza e formato corretti");
            $("#div_cognome").css("color", "green");

        }

        cambiaStatoRegistrami();

    });

    $("#password_").on("input", function() {

        var input = $("#password_");
        var password = input.val();

        if (password.length < 1 || password.length > 50) {
            $("#password_").css("border-bottom", borderError);
            passwordOk = false;
            $("#div_password_").text("La password inserita non rispetta la lunghezza corretta");
            $("#div_password_").css("color", "red");
        }

        if (!password.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)) {
            $("#password_").css("border-bottom", borderError);
            passwordOk = false;
            $("#div_password_").text("La password inserita non rispetta il formato richiesto");
            $("#div_password_").css("color", "red");
        }

        if (password.length >= 1
            && password.length <= 50
            && password.match(/^((?!.*[\s])(?=.*[A-Z])(?=.*\d).{1,50})/)) {

            $("#password_").css("border-bottom", borderOK);
            passwordOk = true;
            $("#div_password_").text("La password inserita ha formato e lunghezza corretti");
            $("#div_password_").css("color", "green");
        }

        cambiaStatoRegistrami();
    });

    $("#data").on("input", function() {

        var input = $("#data");
        var data = input.val();



        if (!data.match(/^[0-9]{4}\-[0-9]{2}\-[0-9]{2}/)) {

            $("#data").css("border-bottom", borderError);
            dataOk = false;
            $("#div_data").text("La data di nascita non rispetta il formato corretto");
            $("#div_data").css("color", "red");

        }

        if (data.match(/^[0-9]{4}\-[0-9]{2}\-[0-9]{2}/)) {

            $("#data").css("border-bottom", borderOK);
            dataOk = true;
            $("#div_data").text("La data di nascita inserita rispetta il formato corretto");
            $("#div_data").css("color", "green");
        }

        cambiaStatoRegistrami();
    });
});

function cambiaStatoRegistrami() {

    if (emailOk && usernameOk && nomeOk && cognomeOk && passwordOk && dataOk) {
        document.getElementById('register').disabled = false;
        $("#register").text("Registrati");
    } else {
        document.getElementById('register').disabled = true;
        $("#register").text("Non puoi registrarti")
    }
}