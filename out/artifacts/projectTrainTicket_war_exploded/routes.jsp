<%@ page import="ru.dmitriykotyshov.trainticketobjects.Train" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 09.01.2018
  Time: 23:26
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
<div id="header">
    <h1>Train&Ticket</h1>
</div>
<div id="body">
    <div id="form">
        <h2>Укажите маршрут и дату поездки</h2>
        <form action="/getroute" method="post">
            <table align="center">
                <tr>
                    <td><label for="stationOne"><span class="bold">Откуда:</span> </label></td><td><input type="text" id="stationOne" name="stationOne"></td>
                    <td><label for="stationTwo"><span class="bold">Куда:</span> </label></td><td><input type="text" id="stationTwo" name="stationTwo"></td>
                    <td colspan="2"><input type="submit" value="Найти"></td>
                </tr>
            </table>
        </form>
    </div>

        <%

            List<Train> trainList = (List<Train>) request.getAttribute("listTrain");
            StringBuilder listToHtml = new StringBuilder();
            if (trainList.size() == 0){
                listToHtml.append("<div class=\"noRoute\">Маршрутов между данными станциями не найдено...</div>");
            }else {
                for (int i = 0; i < trainList.size(); i++) {

                    listToHtml.append("<div class = \"train\">");
                    listToHtml.append("<h3>");
                    if (trainList.get(i).getRoute().getFirstStation().getCity().getNameCity() != null)
                        listToHtml.append("("+trainList.get(i).getRoute().getFirstStation().getCity().getNameCity()+") ");
                    listToHtml.append(trainList.get(i).getRoute().getFirstStation().getNameStation()+ " - ");
                    if (trainList.get(i).getRoute().getSecondStation().getCity().getNameCity() != null)
                        listToHtml.append("("+trainList.get(i).getRoute().getSecondStation().getCity().getNameCity()+") ");
                    listToHtml.append(trainList.get(i).getRoute().getSecondStation().getNameStation()+"</h3>");
                    listToHtml.append("<hr>");
                    listToHtml.append("<p>Маршрут: "+trainList.get(i).getRoute().getNameRoute()+"</p>");
                    listToHtml.append("<p>Поезд №: "+trainList.get(i).getNumberTrain()+"</p>");
                    listToHtml.append("<p>Дата и время отправка: "+trainList.get(i).getDateOut());
                    listToHtml.append("<div>Купить билет</div></div>");


                }
            }

        %>
        <%=listToHtml%>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
