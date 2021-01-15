<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
            integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"
            integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s"
            crossorigin="anonymous">
    </script>
    <script src="./js/registration/check.js"></script>

    <link rel="stylesheet" type="text/css" href="./css/navbar/style_navbar.css">
    <link rel="stylesheet" type="text/css" href="./css/registration/style_registration.css">
</head>

<body>

<%@ include file="../navbar/navbar.jsp"%>

<div class="principal">
    <div class="registration">
        <div class="final">
            <form class="form-container" action="autentication" method="post">
                <input type="hidden" name="flag" value="1">

                <div class="form-group">
                    <label class="label_class" for="email">Email address</label>
                    <input type="text" class="form-control" id="email" name="email" oninput="validaEmail()">
                </div>
                <div id = "div_email" class="message"> Inserisci la tua mail in modo che segua il formato
                    indicato: cicciarampa@gmail.com
                </div>

                <div class="form-group">
                    <label class="label_class" for="username_"> Username </label>
                    <input type="text" class="form-control" id="username_" name="username_" oninput="validaUsername()">
                </div>
                <div id = "div_username_" class="message"> Inserisci la username che abbia almeno una lettera maiuscola
                    e un numero e che sia lunga massimo 50 caratteri
                </div>

                <div class="form-group">
                    <label class="label_class" for="nome"> Nome </label>
                    <input type="text" class="form-control" id="nome" name="nome" oninput="validaNome()">
                </div>
                <div id = "div_nome" class="message"> Inserisci il tuo nome </div>

                <div class="form-group">
                    <label class="label_class" for="cognome"> Cognome </label>
                    <input type="text" class="form-control" id="cognome" name="cognome" oninput="validaCognome()">
                </div>
                <div id = "div_cognome" class="message"> Inserisci il tuo cognome </div>

                <div class="form-group">
                    <label class="label_class" for="password_">Password</label>
                    <input type="password" class="form-control" id="password_" name="password_" oninput="validaPassword()">
                </div>
                <div id = "div_password_" class="message"> Inserisci una password con almeno un
                    numero e una lettera maiuscola e che sia lunga massimo 50 caratteri </div>

                <div class="form-group">
                    <label class="label_class" for="data"> Data di nascita </label>
                    <input type="date" class="form-control" id="data" name="data" min="1950-01-01" max="2010-01-01"
                           value="2010-01-01" oninput="validaData()">
                </div>
                <div id = "div_data" class="message"> Data di nascita non selezionata </div>

                <button type="submit" class="btn btn-primary btn-block" id="register" disabled> Register </button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
