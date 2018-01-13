<%@ page import="ru.dmitriykotyshov.trainticketobjects.City" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 12:34
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
    <h1>Города</h1>
</div>
<div id="body">
    <p><a href="/admin">На главную администратора</a></p>
    <form action="/addCity" method="get">
        <h3>Добавление:</h3>
        <table align="center">
            <tr>
                <td><label for="addCity"><span class="bold">Город:</span> </label></td><td><input type="text" id="addCity" name="city"></td>
                <td colspan="2"><input type="submit" value="Добавить"></td>
            </tr>
        </table>
    </form>
    <form action="/delCity" method="get">
        <h3>Удаление:</h3>
        <table align="center">
            <tr>
                <td><label for="delCity"><span class="bold">ИД:</span> </label></td><td><input type="text" id="delCity" name="city"></td>
                <td colspan="2"><input type="submit" value="Удалить"></td>
            </tr>
        </table>
    </form>
    <h3>Таблица CITY</h3>
    <table class="adminTable">
        <tr>
            <td>City_id</td><td>City</td>
        </tr>
        <%
            List<City> cities = (List<City>) request.getAttribute("cities");
            StringBuilder stringBuilder = new StringBuilder();
            for(City c: cities){
                stringBuilder.append("<tr><td>");
                stringBuilder.append(c.getId()+"</td>");
                stringBuilder.append("<td>"+c.getNameCity()+"</td></tr>");
            }
        %>
        <%=stringBuilder%>
    </table>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>

