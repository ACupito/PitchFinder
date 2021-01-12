<%--
  Created by IntelliJ IDEA.
  User: memex_99
  Date: 04/01/2021
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div>
    <p> Dati personali </p>
    <p>${admin.username}</p>
    <p>${admin.nome}</p>
    <p>${admin.cognome}</p>
</div>

<div>
    <form action="torneo" method="get">

        <div>
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome">
        </div>

        <div>
            <label for="tipo">Nome</label>
            <input type="text" id="tipo" name="tipo">
        </div>

        <div>
            <label for="giornoPartite">Nome</label>
            <input type="text" id="giornoPartite" name="giornoPartite">
        </div>

        <div>
            <label for="adminUsername"> Admin username </label>
            <input type="text" id="adminUsername" name="adminUsername">
        </div>

        <div>
            <label for="numeroSquadre"></label>
            <input type="text" id="numeroSquadre" name="numeroSquadre">
        </div>

        <div>
            <label for="minPartPerSquadra"></label>
            <input type="text" id="minPartPerSquadra" name="minPart">
        </div>

        <div>
            <label for="maxPartPerSquadra"> Id Campo </label>
            <input type="text" id="maxPartPerSquadra" name="maxPart">
        </div>

        <div>
            <label for="dataInizio"> Id Campo </label>
            <input type="date" id="dataInizio" name="dataInizio">
        </div>

        <div>
            <label for="dataFine"> Id Campo </label>
            <input type="date" id="dataFine" name="dataFine">
        </div>

        <div>
            <label for="campoIdentificativo"> Id Campo </label>
            <input type="text" id="campoIdentificativo" name="campoIdentificativo">
        </div>
    </form>
</div>

<div>
    <form action="evento" method="get">

        <div>

        </div>

    </form>
</div>

</body>
</html>
