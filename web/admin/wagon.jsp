<%@ page import="ru.dmitriykotyshov.trainticketobjects.WagonDB" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 25.01.2018
  Time: 23:33
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
    <h1>Вагоны</h1>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addWagon" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td><label for="trainID"><span class="bold">ID поезда:</span> </label></td><td><input type="text" id="trainID" name="trainID"></td>
                </tr>
                <tr>
                    <td><label for="typeWagonID"><span class="bold">ID типа вагона:</span> </label></td><td><input type="text" id="typeWagonID" name="typeWagonID"></td>
                </tr>
                <tr>
                    <td><label for="orderWagon"><span class="bold">Порядковый номер вагона:</span> </label></td><td><input type="text" id="orderWagon" name="orderWagon"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                </tr>
            </table>
        </form>
        <form action="/delWagon" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td><label for="delWagon"><span class="bold">ID:</span> </label></td><td><input type="text" id="delWagon" name="wagon"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица WAGON</h3>
        <table class="adminTable">
            <tr>
                <td>WAGON_ID</td>
                <td>TRAIN_ID</td>
                <td>TYPE_WAGON_ID</td>
                <td>ORDER_WAGON</td>
            </tr>
            <%
                List<WagonDB> wagons = (List<WagonDB>) request.getAttribute("wagons");
                StringBuilder writeWagons = new StringBuilder();
                for(WagonDB w: wagons){
                    writeWagons.append("<tr>");
                    writeWagons.append("<td>"+w.getWagonId()+"</td>");
                    writeWagons.append("<td>"+w.getTrain().getId()+"("+w.getTrain().getNumberTrain()+")</td>");
                    writeWagons.append("<td>"+w.getTypeWagon().getTypeWagonId()+"("+w.getTypeWagon().getTypeName()+")</td>");
                    writeWagons.append("<td>"+w.getOrderWagon()+"</td>");
                    writeWagons.append("</tr>");
                }
            %>
            <%=writeWagons%>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
