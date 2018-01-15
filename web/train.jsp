<%@ page import="ru.dmitriykotyshov.trainticketobjects.Wagon" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 15.01.2018
  Time: 2:05
  To change this template use File | Settings | File Templates.
--%>
<%
    List<Wagon> wagons = (List<Wagon>) request.getAttribute("wagons");

    StringBuilder train = new StringBuilder();
    StringBuilder wagon = new StringBuilder();

    if (wagons.size() != 0) {
        train.append("<div class=\"train\"><h3>");
        if (wagons.get(0).getTrain().getRoute().getFirstStation().getCity().getNameCity() != null)
            train.append("("+wagons.get(0).getTrain().getRoute().getFirstStation().getCity().getNameCity()+") ");
        train.append(wagons.get(0).getTrain().getRoute().getFirstStation().getNameStation()+ " - ");
        if (wagons.get(0).getTrain().getRoute().getSecondStation().getCity().getNameCity() != null)
            train.append("("+wagons.get(0).getTrain().getRoute().getSecondStation().getCity().getNameCity()+")</h3>");
        train.append("Имя маршрута: "+wagons.get(0).getTrain().getRoute().getNameRoute()+"<br>");
        train.append("Поезд №: "+wagons.get(0).getTrain().getNumberTrain()+"<br></div>");
        train.append("<hr>");
        train.append("Информация о вагонах:");

        for (int i=0; i<wagons.size(); i++){

            wagon.append("<div class=\"wagon\">");
            wagon.append("Номер вагона: "+wagons.get(i).getOrder()+"<br>");
            wagon.append("Тип вагона: "+wagons.get(i).getTypeWagon()+"<br>");
            wagon.append("Биотуалет: "+wagons.get(i).isBioTiolet()+"<br>");
            wagon.append("Кондиционер: "+wagons.get(i).isAirCondition()+"<br>");
            wagon.append("<div><a href=\"javascript: goToTrain(jsonParse["+i+"])\">Выбрать место</a></div>");
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
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div id="wrap">
    <div id="header">
        <h1>Train&Ticket</h1>
    </div>
    <div id="body">
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

