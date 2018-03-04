<%@ page import="ru.dmitriykotyshov.trainticketobjects.City" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.dmitriykotyshov.trainticketobjects.RouteDB" %>
<%@ page import="static ru.dmitriykotyshov.other.ValidAdmin.validationRouteAdmin" %>
<%@ page import="static ru.dmitriykotyshov.other.Message.insufficientRights" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 16.01.2018
  Time: 0:04
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
    <script src="../js/admin/valid.route.js"></script>
    <script src="../js/admin/valid.delete.js"></script>
</head>
<body>
<div id="header">
    <h1>Маршруты</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addRoute" onsubmit="return validRoute()" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td colspan="2"><span id="message"></span></td>
                </tr>
                <tr>
                    <td><label for="addRoute"><span class="bold">Маршрут:</span> </label></td><td><input type="text" id="addRoute" name="route"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                </tr>
            </table>
        </form>
        <form action="/delRoute" onsubmit="return validDelete('delRoute')" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td colspan="3" id="mesDel"></td>
                </tr>
                <tr>
                    <td><label for="delRoute"><span class="bold">ID:</span> </label></td><td><input type="text" id="delRoute" name="route"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица ROUTE</h3>
        <table class="adminTable">
            <tr>
                <td>ROUTE_ID</td>
                <td>ROUTE</td>
            </tr>
            <%
                List<RouteDB> routes = (List<RouteDB>) request.getAttribute("routes");
                StringBuilder writeRoutes = new StringBuilder();
                for(RouteDB r: routes){
                    writeRoutes.append("<tr><td>");
                    writeRoutes.append(r.getRouteId()+"</td>");
                    writeRoutes.append("<td>"+r.getNameRoute()+"</td></tr>");
                }
            %>
            <%=writeRoutes%>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
