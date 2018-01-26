<%@ page import="ru.dmitriykotyshov.trainticketobjects.Train" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 26.01.2018
  Time: 0:34
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
    <h1>Поезда</h1>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addTrain" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td><label for="numberTrain"><span class="bold">Номер поезда:</span> </label></td><td><input type="text" id="numberTrain" name="numberTrain"></td>
                </tr>
                <tr>
                    <td><label for="routeID"><span class="bold">ID маршрута:</span> </label></td><td><input type="text" id="routeID" name="routeID"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                </tr>
            </table>
        </form>
        <form action="/delTrain" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td><label for="delTrain"><span class="bold">ID:</span> </label></td><td><input type="text" id="delTrain" name="train"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица TRAIN</h3>
        <table class="adminTable">
            <tr>
                <td>TRAIN_ID</td>
                <td>NUMBER_TRAIN</td>
                <td>ROUTE_ID</td>
            </tr>
            <%
                List<Train> trains = (List<Train>) request.getAttribute("trains");
                StringBuilder writeTrains = new StringBuilder();
                for(Train t: trains){
                    writeTrains.append("<tr>");
                    writeTrains.append("<td>"+t.getId()+"</td>");
                    writeTrains.append("<td>"+t.getNumberTrain()+"</td>");
                    writeTrains.append("<td>"+t.getRoute().getId()+"("+t.getRoute().getNameRoute()+"</td></tr>");
                }
            %>
            <%=writeTrains%>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
