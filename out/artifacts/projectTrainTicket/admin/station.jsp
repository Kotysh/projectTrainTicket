<%@ page import="ru.dmitriykotyshov.trainticketobjects.Station" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<div id="header">
    <h1>Станции</h1>
</div>
<div id="wrap">
<div id="bodyAdmin">
    <p><a href="/admin">На главную администратора</a></p>
    <form action="/addStation" method="get">
        <h3>Добавление:</h3>
        <table align="center">
            <tr>
                <td><label for="station"><span class="bold">Станция:</span> </label></td><td><input type="text" id="station" name="station"></td>
                <td><label for="cityID"><span class="bold">ID города:</span> </label></td><td><input type="text" id="cityID" name="cityID"></td>
                <td colspan="2"><input type="submit" value="Добавить"></td>
            </tr>
        </table>
    </form>
    <form action="/delStation" method="get">
        <h3>Удаление:</h3>
        <table align="center">
            <tr>
                <td><label for="delStation"><span class="bold">ID:</span> </label></td><td><input type="text" id="delStation" name="station"></td>
                <td colspan="2"><input type="submit" value="Удалить"></td>
            </tr>
        </table>
    </form>
    <h3>Таблица STATION</h3>
    <table class="adminTable">
        <tr>
            <td>STATION_ID</td>
            <td>STATION</td>
            <td>CITY_ID</td>
        </tr>
        <%
            List<Station> stations = (List<Station>) request.getAttribute("stations");
            StringBuilder writeStations = new StringBuilder();
            for(Station s: stations){
                writeStations.append("<tr><td>");
                writeStations.append(s.getId()+"</td>");
                writeStations.append("<td>"+s.getNameStation()+"</td>");
                writeStations.append("<td>"+s.getCity().getId()+" ("+s.getCity().getNameCity()+")</td></tr>");
            }
        %>
        <%=writeStations%>
    </table>
</div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
