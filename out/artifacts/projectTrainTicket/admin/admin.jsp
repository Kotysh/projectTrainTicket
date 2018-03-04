<%@ page import="static ru.dmitriykotyshov.other.ValidAdmin.menuAdmin" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%

    String login = (String) request.getSession().getAttribute("login");
    String password = (String) request.getSession().getAttribute("password");

    if (login == null || password == null){
        request.getRequestDispatcher("admin/inputAdmin.jsp").forward(request, response);
    }

    Integer typeAdmin = Integer.valueOf((String) request.getSession().getAttribute("typeAdmin"));

    String menu = menuAdmin(typeAdmin);


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
    <h1>Раздел администратора</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="body">
        <h2>Выберите таблицу</h2>
        <ol>
            <%=menu%>
        </ol>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
