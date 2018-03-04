<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 28.01.2018
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainTicket</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/linkMenu.css">
    <link rel="stylesheet" href="css/new_style.css">
    <link href="css/datepicker.min.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="js/valid.routes.js"></script>
    <script src="js/datepicker.js"></script>
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
        <h2>Добро пожаловать!</h2>
        <p>На нашем портале, Вы можете приобрести билет на поезд</p>
        <hr>
        <div id="form">
            <h2>Укажите маршрут и дату поездки</h2>
            <form action="/getroute" onsubmit="return validRoute()" method="get">
                <table align="center">
                    <tr>
                        <td><label for="stationOne"><span class="bold">Откуда:</span> </label></td><td><input type="text" id="stationOne" name="stationOne"></td>
                        <td><label for="stationTwo"><span class="bold">Куда:</span> </label></td><td><input type="text" id="stationTwo" name="stationTwo"></td>
                    </tr>
                    <tr>
                        <td><label for="date"><span class="bold">Дата: </span></label></td><td><input type="text" class="datepicker-here" id="date" name="date" readonly></td>
                        <td colspan="2" align="center"><input type="submit" value="Найти"></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left" id="message">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4"><span id="mess"></span></td>
                    </tr>
                </table>
            </form>
        </div>
        <hr>
        <div>
            Уважаемый, <%=request.getAttribute("firstName")%> <%=request.getAttribute("middleName")%>, благодарим Вас за покупку билета,
            копия билета, реквизиты об оплате высланы Вам на почту. При посадке на поезд не забудте при себе иметь документ указанный при
            покупке билета, счастливого пути!!!
        </div>
        <div align="right">
            С уважением Ваш Train&Ticket.
        </div>
    </div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
