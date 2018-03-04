<%@ page import="ru.dmitriykotyshov.trainticketobjects.Station" %>
<%@ page import="java.util.List" %>
<%@ page import="static ru.dmitriykotyshov.other.ValidAdmin.validationRouteAdmin" %>
<%@ page import="static ru.dmitriykotyshov.other.Message.insufficientRights" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%

    String login = (String) request.getSession().getAttribute("login");
    String password = (String) request.getSession().getAttribute("password");

    if (login == null || password == null){
        request.getRequestDispatcher("inputAdmin.jsp").forward(request, response);
    }
    Integer typeAdmin = Integer.valueOf((String) request.getSession().getAttribute("typeAdmin"));
    if (!validationRouteAdmin(typeAdmin))
        insufficientRights(request, response);



%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/new_style.css">
    <script src="../js/admin/valid.station.js"></script>
    <script src="../js/admin/valid.delete.js"></script>
</head>
<body>
<div id="header">
    <h1>Станции</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
<div id="bodyAdmin">
    <p><a href="/admin">На главную администратора</a></p>
    <form action="/addStation" onsubmit="return validStation()" method="get">
        <h3>Добавление:</h3>
        <table align="center">
            <tr>
                <td colspan="3"><span id="message">&nbsp;</span></td>
            </tr>
            <tr>
                <td colspan="3"><span id="messageCity">&nbsp;</span></td>
            </tr>
            <tr>
                <td><label for="station"><span class="bold">Станция:</span> </label></td><td><input type="text" id="station" name="station"></td>
                <td><label for="cityId"><span class="bold">ID города:</span> </label></td><td><input type="text" id="cityId" name="cityId"></td>
                <td colspan="2"><input type="submit" value="Добавить"></td>
            </tr>
        </table>
    </form>
    <form action="/delStation" onsubmit="return validDelete('delStation')" method="get">
        <h3>Удаление:</h3>
        <table align="center">
            <tr>
                <td colspan="3" id="mesDel"></td>
            </tr>
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
