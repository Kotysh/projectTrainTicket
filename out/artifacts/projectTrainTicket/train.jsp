<%@ page import="ru.dmitriykotyshov.trainticketobjects.Wagon" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="ru.dmitriykotyshov.other.Price" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 15.01.2018
  Time: 2:05
  To change this template use File | Settings | File Templates.
--%>
<%
    ObjectMapper objectMapper = new ObjectMapper();
    String str = (String) request.getAttribute("json");
    List<Wagon> wagons = objectMapper.readValue(str, new TypeReference<ArrayList<Wagon>>(){});

    StringBuilder train = new StringBuilder();
    StringBuilder wagon = new StringBuilder();

    if (wagons.size() != 0) {
        train.append("<div class=\"train\"><h3>");
        if (wagons.get(0).getTrain().getRoute().getFirstStation().getCity().getNameCity() != null && !wagons.get(0).getTrain().getRoute().getFirstStation().getCity().getNameCity().equals("null"))
            train.append("("+wagons.get(0).getTrain().getRoute().getFirstStation().getCity().getNameCity()+") ");
        train.append(wagons.get(0).getTrain().getRoute().getFirstStation().getNameStation()+ " - ");
        if (wagons.get(0).getTrain().getRoute().getSecondStation().getCity().getNameCity() != null && !wagons.get(0).getTrain().getRoute().getSecondStation().getCity().getNameCity().equals("null"))
            train.append("("+wagons.get(0).getTrain().getRoute().getSecondStation().getCity().getNameCity()+") ");
        train.append(wagons.get(0).getTrain().getRoute().getSecondStation().getNameStation()+"</h3>");
        train.append("Маршрута: "+wagons.get(0).getTrain().getRoute().getNameRoute()+"<br>");
        train.append("Поезд №: "+wagons.get(0).getTrain().getNumberTrain()+"<br>");
        train.append("Время убытия: "+wagons.get(0).getTrain().getRoute().getTimeDateFirstStation()+"<br>");
        train.append("Время прибытия: "+wagons.get(0).getTrain().getRoute().getTimeDateSecondStation()+"<br>");
        train.append("Расстояние: "+wagons.get(0).getTrain().getRoute().getDistance()+"км<br></div>");
        train.append("<hr>");
        train.append("Информация о вагонах:");

        for (int i=0; i<wagons.size(); i++){

            wagon.append("<div class=\"wagon\">");
            wagon.append("Номер вагона: "+wagons.get(i).getOrder()+"<br>");
            wagon.append("Тип вагона: "+wagons.get(i).getTypeWagon()+"<br>");
            wagon.append("Биотуалет: "+(wagons.get(i).isBioTiolet()?"да":"нет")+"<br>");
            wagon.append("Кондиционер: "+(wagons.get(i).isAirCondition()?"да":"нет")+"<br>");
            wagon.append("Количество мест: "+wagons.get(i).getCountPlace()+"<br>");
            wagon.append("Цена: "+wagons.get(i).getPrice()+"р.<br>");
            wagon.append("<div><a href=\"javascript: goToPlace(jsonParse[" + i + "])\">Выбрать место</a></div>");
            wagon.append("</div>");

        }

    }else{
        train.append("Вагонов нет!");
    }


%>
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
    </div>
    <div id="body">
        <div class="backClick"><a onclick="javascript:history.back(); return false;">Назад к маршрутам</a></div>
        <%=train%>
        <%=wagon%>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
<script src="js/script.js" defer>
</script>
<script defer>
    var jsonParse = JSON.parse('<%=str%>');
</script>

