<%@ page import="ru.dmitriykotyshov.trainticketobjects.Train" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 09.01.2018
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>

<%

    ObjectMapper objectMapper = new ObjectMapper();
    String str = (String) request.getAttribute("json");
    List<Train> trainList = objectMapper.readValue(str, new TypeReference<ArrayList<Train>>(){});

    StringBuilder listToHtml = new StringBuilder();
    if (trainList.size() == 0){
        listToHtml.append("<div class=\"noRoute\">Маршрутов не найдено...</div>");
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
            listToHtml.append("<table>");
            listToHtml.append("<tr><td>Маршрут:</td><td>"+trainList.get(i).getRoute().getNameRoute()+"</td></tr>");
            listToHtml.append("<tr><td>Поезд №:</td><td>"+trainList.get(i).getNumberTrain()+"</td></tr>");
            listToHtml.append("<tr><td>Дата и время отправки:</td><td>"+trainList.get(i).getRoute().getTimeDateFirstStation()+"</td></tr>");
            listToHtml.append("<tr><td>Дата и время прибытия:</td><td>"+trainList.get(i).getRoute().getTimeDateSecondStation()+"</td></tr>");
            listToHtml.append("<tr><td>Расстояние:</td><td>"+trainList.get(i).getRoute().getDistance()+" км</td></tr>");
            listToHtml.append("<tr><td>Цена от:</td><td>"+trainList.get(i).getRoute().getPrice()+" р.</td></tr>");
            listToHtml.append("</table>");
            listToHtml.append("<div><a href=\"javascript: goToTrain(jsonParse["+i+"])\">Купить билет</a></div></div>");
            listToHtml.append("\n");
        }
    }

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainTicket</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/style_index.css">
    <link rel="stylesheet" href="css/styles.css">
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
            <a href="/">Контакты</a>
            <a href="/">О нас</a>
        </div>
    </div>

    <div id="body">
        <h2>Укажите маршрут и дату поездки</h2>
        <div id="form">
            <form action="/getroute" onsubmit="return validRoute()" method="post">
                <table align="center">
                    <tr>
                        <td><label for="stationOne"><span class="bold">Откуда:</span> </label></td><td><input type="text" id="stationOne" name="stationOne"></td>
                        <td><label for="stationTwo"><span class="bold">Куда:</span> </label></td><td><input type="text" id="stationTwo" name="stationTwo"></td>
                    </tr>
                    <tr>
                        <td><label for="date"><span class="bold">Дата: </span></label></td><td><input type="text" class="datepicker-here" id="date" name="date" readonly></td>
                        <td colspan="4" align="center"><input type="submit" value="Найти"></td>
                    </tr>
                    <tr>
                        <td colspan="4" align="left" id="message">&nbsp;</td>
                    </tr>
                </table>
            </form>
        </div>
        <%=listToHtml%>
    </div>


    <div id="footer">
        <p>Дмитрий Котяшов 2к18<br>
            kotyshok@yandex.ru</p>
    </div>
</body>
</html>
<script src="js/doget.js" defer>
</script>
<script defer>
    var jsonParse = JSON.parse('<%=str%>');
</script>