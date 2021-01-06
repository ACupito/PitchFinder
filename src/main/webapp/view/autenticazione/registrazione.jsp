<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
          crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../../css/global.css">
</head>

<body>

<%@ include file="../navbar/navbar.jsp"%>
<section class="container-fluid bg">
    <section class="row justify-content-center">
        <section class="col-12 col-sm-6 col-md-3">

            <form class="form-container" action="autentication" method="post">

                <input type="hidden" name="flag" value="1">
                <div class="form-group">
                    <label for="email">Email address</label>
                    <input type="text" class="form-control" id="email" name="email" oninput="validaEmail()">
                </div>
                <div class="form-group">
                    <label for="username"> Username </label>
                    <input type="text" class="form-control" id="username" name="username" oninput="validaUsername()">
                </div>
                <div class="form-group">
                    <label for="nome"> Nome </label>
                    <input type="text" class="form-control" id="nome" name="nome" oninput="validaNome()">
                </div>
                <div class="form-group">
                    <label for="cognome"> Cognome </label>
                    <input type="text" class="form-control" id="cognome" name="cognome" oninput="validaCognome()">
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" id="password" name="password" oninput="validaPassword()">
                </div>
                <div class="form-group">
                    <label for="data"> Data di nascita </label>
                    <input type="date" class="form-control" id="data" name="data" min="1950-01-01" max="2010-01-01"
                           value="2010-01-01" oninput="validaData()">
                </div>
                <button type="submit" class="btn btn-primary btn-block" id="register" disabled> Register </button>
            </form>

        </section>
    </section>
</section>

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
<script src="js/check.js">
</script>
</body>
</html>
