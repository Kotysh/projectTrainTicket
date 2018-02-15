<%@ page import="ru.dmitriykotyshov.trainticketobjects.TypeWagon" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 25.01.2018
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%

    String login = (String) request.getSession().getAttribute("login");
    String password = (String) request.getSession().getAttribute("password");

    if (login == null || password == null){
        request.getRequestDispatcher("inputAdmin.jsp").forward(request, response);
    }



%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/new_style.css">
</head>
<body>
<div id="header">
    <h1>Типы вагонов</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addTypeWagon" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td><label for="typeWagon"><span class="bold">Тип вагона:</span> </label></td><td><input type="text" id="typeWagon" name="typeWagon"></td>
                </tr>
                <tr>
                    <td><label for="bioTiolet"><span class="bold">Биотуалет:</span> </label></td><td><input type="text" id="bioTiolet" name="bioTiolet"></td>
                </tr>
                <tr>
                    <td><label for="airCondition"><span class="bold">Кондиционер:</span> </label></td><td><input type="text" id="airCondition" name="airCondition"></td>
                </tr>
                <tr>
                    <td><label for="countPlace"><span class="bold">Количество мест:</span> </label></td><td><input type="text" id="countPlace" name="countPlace"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                </tr>
            </table>
        </form>
        <form action="/delTypeWagon" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td><label for="delTypeWagon"><span class="bold">ID:</span> </label></td><td><input type="text" id="delTypeWagon" name="typeWagon"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица TYPE_WAGON</h3>
        <table class="adminTable">
            <tr>
                <td>TYPE_WAGON_ID</td>
                <td>TYPE_NAME</td>
                <td>BIO_TIOLET</td>
                <td>AIR_CONDITION</td>
                <td>COUNT</td>
            </tr>
            <%
                List<TypeWagon> typeWagons = (List<TypeWagon>) request.getAttribute("typeWagons");
                StringBuilder writeTypeWagons = new StringBuilder();
                for(TypeWagon t: typeWagons){
                    writeTypeWagons.append("<tr>");
                    writeTypeWagons.append("<td>"+t.getTypeWagonId()+"</td>");
                    writeTypeWagons.append("<td>"+t.getTypeName()+"</td>");
                    writeTypeWagons.append("<td>"+t.getBioTiolet()+"</td>");
                    writeTypeWagons.append("<td>"+t.getAirCondition()+"</td>");
                    writeTypeWagons.append("<td>"+t.getCountPlace()+"</td>");
                    writeTypeWagons.append("</tr>");
                }
            %>
            <%=writeTypeWagons%>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
