<%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 04/01/2021
  Time: 10:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../../css/user_profile/style_user_profile.css">
    <link rel="stylesheet" type="text/css" href="../../css/homepage/style_homepage.css">
</head>
<body>

<%@ include file="../navbar/navbar.jsp"%>

<div class="row1">
    <fieldset>
        <legend> Dati dell'utente: </legend>
        <label class="label">
            <span> ${utente.email} </span><br>
        </label>
        <label class="label">
            <span> ${utente.username} </span><br>
        </label>
        <label class="label">
            <span> ${utente.nome} </span><br>
        </label>

        <label class="label">
            <span> ${utente.cognome} </span><br>
        </label>

        <label class="label">
            <span> ${utente.dataDiNascita} </span><br>
        </label>
    </fieldset>
</div>

</body>
</html>
