<%@ page import="ru.dmitriykotyshov.entity.Admin" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 12.02.2018
  Time: 19:21
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
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/new_style.css">
</head>
<body>
<div id="header">
    <h1>Администраторы</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addAdmin" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td><label for="name"><span class="bold">Имя:</span> </label></td><td colspan="3"><input type="text" id="name" name="name"></td>
                </tr>
                <tr>
                    <td><label for="password"><span class="bold">Пароль:</span> </label></td><td colspan="3"><input type="text" id="password" name="password"></td>
                </tr>
                <tr>
                    <td><label for="typeAdmin"><span class="bold">Тип администратора:</span> </label></td><td colspan="3"><input type="text" id="typeAdmin" name="typeAdmin"></td>
                </tr>
                <tr>
                    <td><label for="boss"><span class="bold">Начальник:</span> </label></td><td colspan="3"><input type="text" id="boss" name="boss"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                    <td></td>
                </tr>
            </table>
        </form>
        <form action="/delAdmin" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td><label for="delAdmin"><span class="bold">ID:</span> </label></td><td><input type="text" id="delAdmin" name="delAdmin"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица ADMIN</h3>
        <table class="adminTable">
            <tr>
                <td>ADMIN_ID</td>
                <td>NAME</td>
                <td>PASS</td>
                <td>TYPE_ADMIN_ID</td>
                <td>BOSS</td>
            </tr>
            <%
                List<Admin> admins = (List<Admin>) request.getAttribute("admins");
                StringBuilder writeAdmins = new StringBuilder();
                for(Admin a: admins){
                    writeAdmins.append("<tr>");
                    writeAdmins.append("<td>"+a.getId()+"</td>");
                    writeAdmins.append("<td>"+a.getName()+"</td>");
                    writeAdmins.append("<td>"+a.getPass()+"</td>");
                    writeAdmins.append("<td>"+a.getTypeAdmin().getId()+" ("+a.getTypeAdmin().getType()+")</td>");
                    writeAdmins.append("<td>"+a.getBoss()+"</td></tr>");
                }
            %>
            <%=writeAdmins%>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
