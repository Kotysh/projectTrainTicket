<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 11:36
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
    <h1>Раздел администратора</h1>
</div>
<div id="wrap">
<div id="body">
        <h2>Выберите таблицу</h2>
        <ol>
            <li><a href="#">Клиент(CUSTOMER)</a></li>
            <li><a href="/city">Город(CITY)</a></li>
            <li><a href="/station">Станция(STATION)</a></li>
            <li><a href="/document">Документ(DOCUMENT)</a></li>
            <li><a href="/route">Маршрут(ROUTE)</a></li>
            <li><a href="#">Маршруты и станции(ROUTE_STATION)</a></li>
            <li><a href="#">Поезд(TRAIN)</a></li>
            <li><a href="#">Вагон(WAGON)</a></li>
            <li><a href="#">Тип вагона(TYPE_WAGON)</a></li>
        </ol>
</div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
