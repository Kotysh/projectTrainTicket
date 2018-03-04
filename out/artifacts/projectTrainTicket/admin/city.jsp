<%@ page import="ru.dmitriykotyshov.trainticketobjects.City" %>
<%@ page import="java.util.List" %>
<%@ page import="static ru.dmitriykotyshov.other.Message.insufficientRights" %>
<%@ page import="static ru.dmitriykotyshov.other.ValidAdmin.validationRouteAdmin" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 12:34
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
    <script src="../js/admin/valid.city.js"></script>
    <script src="../js/admin/valid.delete.js"></script>
</head>
<body>
<div id="header">
    <h1>Города</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
<div id="bodyAdmin">
    <p><a href="/admin">На главную администратора</a></p>
    <form action="/addCity" onsubmit="return validCity()" method="get">
        <h3>Добавление:</h3>
        <table align="center">
            <tr align="center">
                <td><label for="city"><span class="bold">Город:</span> </label></td><td><input type="text" id="city" name="city"></td>
            </tr>
            <tr>
                <td colspan="2"><span id="message">&nbsp;</span></td>
            </tr>
            <tr align="center">
                <td colspan="2"><input type="submit" value="Добавить"></td>
            </tr>
        </table>
    </form>
    <form action="/delCity" onsubmit="return validDelete('delCity')" method="get">
        <h3>Удаление:</h3>
        <table align="center">
            <tr>
                <td colspan="3" id="mesDel"></td>
            </tr>
            <tr>
                <td><label for="delCity"><span class="bold">ID:</span> </label></td><td><input type="text" id="delCity" name="city"></td>
                <td colspan="2"><input type="submit" value="Удалить"></td>
            </tr>
        </table>
    </form>
    <h3>Таблица CITY</h3>
    <table class="adminTable">
        <tr>
            <td>CITY_ID</td>
            <td>CITY</td>
        </tr>
        <%
            List<City> cities = (List<City>) request.getAttribute("cities");
            StringBuilder writeCities = new StringBuilder();
            for(City c: cities){
                writeCities.append("<tr><td>");
                writeCities.append(c.getId()+"</td>");
                writeCities.append("<td>"+c.getNameCity()+"</td></tr>");
            }
        %>
        <%=writeCities%>
    </table>
</div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>

