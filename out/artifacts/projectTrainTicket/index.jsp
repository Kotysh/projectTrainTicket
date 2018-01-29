<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 06.01.2018
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TrainTicket</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style.css">
  </head>
  <body>
  <div id="wrap">
    <div id="header">
      <h1>Train&Ticket</h1>
      <a href="/">На главную</a>
    </div>
    <div id="body">
      <h2>Добро пожаловать!</h2>
      <p>На нашем портале, Вы можете приобрести билет на поезд</p>
      <div id="form">
        <h2>Укажите маршрут и дату поездки</h2>
        <form action="/getroute" method="post">
          <table align="center">
            <tr>
              <td><label for="stationOne"><span class="bold">Откуда:</span> </label></td><td><input type="text" id="stationOne" name="stationOne"></td>
              <td><label for="stationTwo"><span class="bold">Куда:</span> </label></td><td><input type="text" id="stationTwo" name="stationTwo"></td>
            </tr>
            <tr>
              <td><label for="year"><span class="bold">Год:</span> </label></td><td><input type="text" id="year" name="year"></td>
              <td><label for="month"><span class="bold">Месяц:</span> </label></td><td><input type="text" id="month" name="month"></td>
              <td><label for="day"><span class="bold">День:</span> </label></td><td><input type="text" id="day" name="day"></td>
              <td colspan="2"><input type="submit" value="Найти"></td>
            </tr>
          </table>
        </form>
      </div>
    </div>
  </div>
    <div id="footer">
      <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
    </div>
  </body>
</html>
