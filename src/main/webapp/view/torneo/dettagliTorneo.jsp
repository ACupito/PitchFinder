<%@ page import="com.pitchfinder.torneo.entity.Torneo" %><%--
  Created by IntelliJ IDEA.
  User: Pollax
  Date: 13/01/2021
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% Torneo torneo = (Torneo) application.getAttribute("torneo"); %>
<p><%=torneo.getNome()%></p>
<p><%=torneo.getDataInizio()%></p>
<p><%=torneo.getDataFine()%></p>

</body>
</html>
