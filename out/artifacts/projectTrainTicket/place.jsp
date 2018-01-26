<%@ page import="ru.dmitriykotyshov.trainticketobjects.Wagon" %>
<%@ page import="java.util.Set" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 22.01.2018
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%

    Wagon wagon = (Wagon) request.getAttribute("wagon");
    Set<Integer> setPlace = (Set<Integer>) request.getAttribute("listPlaces");

    StringBuilder placeToHtml = new StringBuilder();

    placeToHtml.append("<div class = \"train\">");
    placeToHtml.append("<h3>");
    if (wagon.getTrain().getRoute().getFirstStation().getCity().getNameCity() != null && !wagon.getTrain().getRoute().getFirstStation().getCity().getNameCity().equals("null"))
        placeToHtml.append("("+wagon.getTrain().getRoute().getFirstStation().getCity().getNameCity()+") ");
    placeToHtml.append(wagon.getTrain().getRoute().getFirstStation().getNameStation()+ " - ");
    if (wagon.getTrain().getRoute().getSecondStation().getCity().getNameCity() != null && !wagon.getTrain().getRoute().getSecondStation().getCity().getNameCity().equals("null"))
        placeToHtml.append("("+wagon.getTrain().getRoute().getSecondStation().getCity().getNameCity()+") ");
    placeToHtml.append(wagon.getTrain().getRoute().getSecondStation().getNameStation()+"</h3>");
    placeToHtml.append("<hr>");
    placeToHtml.append("<p>Маршрут: "+wagon.getTrain().getRoute().getNameRoute()+"</p>");
    placeToHtml.append("<p>Поезд №: "+wagon.getTrain().getNumberTrain()+"</p>");
    placeToHtml.append("<p>Дата и время отправки: "+wagon.getTrain().getRoute().getTimeDateFirstStation()+"</p>");
    placeToHtml.append("<p>Дата и время прибытия: "+wagon.getTrain().getRoute().getTimeDateSecondStation()+"</p>");
    placeToHtml.append("<p>Номер вагона: "+wagon.getOrder()+"</p>");
    placeToHtml.append("<p>Тип вагона: "+wagon.getTypeWagon()+"</p>");
    placeToHtml.append("<p>Туалет: "+wagon.isBioTiolet()+"</p>");
    placeToHtml.append("<p>Кондиционер: "+wagon.isBioTiolet()+"</p>");
    placeToHtml.append("<p>Общее количество мест: "+wagon.getCountPlace()+"</p>");
    placeToHtml.append("<p>Свободное количество мест: "+(wagon.getCountPlace()-setPlace.size())+"</p>");
    placeToHtml.append("<div><a href=\"/getbuy\">Купить</a></div></div>");
    placeToHtml.append("<hr>");
    placeToHtml.append("<p>Заполните анкету, выберите место и нажмите купить</p>");
    placeToHtml.append("\n");


/*    for (int i=1; i<=wagon.getCountPlace(); i++){
        if (setPlace.contains(i)){
            placeToHtml.append("<div class=\"noPlace\">Место №"+i+" занято</div>");
        }else{
            placeToHtml.append("<div class=\"yesPlace\">Место №"+i+" свободно</div>");
        }
    }*/


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
        <div class="backClick"><a onclick="javascript:history.back(); return false;">Назад к выбору вагона</a></div>
        <%=placeToHtml%>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
