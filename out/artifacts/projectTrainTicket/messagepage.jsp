<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 14.02.2018
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainTicket</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/new_style.css">
    <link rel="stylesheet" href="css/linkMenu.css">
    <link href="css/datepicker.min.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="js/datepicker.js"></script>
    <script src="js/valid.routes.js"></script>
</head>
<body>

<div id="header">
    <h1>Train&Ticket</h1>
    <div id="menu">
        <a href="/">Главная</a>
        <a href="/contacts">Контакты</a>
        <a href="/aboutus">О нас</a>
    </div>
</div>

<div id="body">
    <div><%=request.getAttribute("message")%></div>
</div>

<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>

</body>
</html>

