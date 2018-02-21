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

    List<Wagon> wagons = (List<Wagon>) request.getSession().getAttribute("wagons");

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
        train.append("<table>\n"
                    +"<tr><td>Маршрута:</td><td>"+wagons.get(0).getTrain().getRoute().getNameRoute()+"</td></tr>\n"
                    +"<tr><td>Поезд №:</td><td>"+wagons.get(0).getTrain().getNumberTrain()+"</td></tr>\n"
                    +"<tr><td>Время убытия:</td><td>"+wagons.get(0).getTrain().getRoute().getTimeDateFirstStation()+"</td></tr>\n"
                    +"<tr><td>Время прибытия:</td><td>"+wagons.get(0).getTrain().getRoute().getTimeDateSecondStation()+"</td></tr>\n"
                    +"<tr><td>Расстояние:</td><td>"+wagons.get(0).getTrain().getRoute().getDistance()+"км</td></tr></table></div>\n"
                    +"<hr>\n"
                    +"Информация о вагонах:");

        for (int i=0; i<wagons.size(); i++){
            wagon.append("<form id=\"form"+i+"\" action=\"/getplace\" method=\"get\" style = \"display:none;\">\n"+
                        "<input type=\"text\" name=\"selectWagon\" value=\""+i+"\">\n"+
                        "</form>\n"
                        +"<div class=\"wagon\">\n"
                        +"<table>\n"
                        +"<tr><td>Номер вагона:</td><td>"+wagons.get(i).getOrder()+"</td></tr>\n"
                        +"<tr><td>Тип вагона:</td><td>"+wagons.get(i).getTypeWagon()+"</td></tr>\n"
                        +"<tr><td>Биотуалет:</td><td>"+(wagons.get(i).isBioTiolet()?"да":"нет")+"</td></tr>\n"
                        +"<tr><td>Кондиционер:</td><td>"+(wagons.get(i).isAirCondition()?"да":"нет")+"</td></tr>\n"
                        +"<tr><td>Количество мест:</td><td>"+wagons.get(i).getCountPlace()+"</td></tr>\n"
                        +"<tr><td>Цена:</td><td>"+wagons.get(i).getPrice()+"р.</td></tr>\n"
                        +"</table>\n"
                        +"<div onclick = 'document.getElementById(\"form" + i + "\").submit();'>Выбрать место</div>\n"
                        +"</div>");
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
    <link rel="stylesheet" href="css/new_style.css">
    <link rel="stylesheet" href="css/linkMenu.css">
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
        <div class="backClick"><a onclick="javascript:history.back(); return false;">Назад к маршрутам</a></div>
        <%=train%>
        <%=wagon%>
    </div>

<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>

</body>
</html>

