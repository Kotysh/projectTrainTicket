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
            listToHtml.append("<p>Маршрут: "+trainList.get(i).getRoute().getNameRoute()+"</p>");
            listToHtml.append("<p>Поезд №: "+trainList.get(i).getNumberTrain()+"</p>");
            listToHtml.append("<p>Дата и время отправки: "+trainList.get(i).getRoute().getTimeDateFirstStation()+"</p>");
            listToHtml.append("<p>Дата и время прибытия: "+trainList.get(i).getRoute().getTimeDateSecondStation()+"</p>");
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
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div id="wrap">
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
        <%=listToHtml%>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
<script src="js/scripts.js" defer>
</script>
<script defer>
    var jsonParse = JSON.parse('<%=str%>');
</script>