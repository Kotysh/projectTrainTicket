<%@ page import="ru.dmitriykotyshov.trainticketobjects.Train" %>
<%@ page import="java.util.List" %>
<%@ page import="static ru.dmitriykotyshov.other.MyDate.get5Minute" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 09.01.2018
  Time: 23:26
  To change this template use File | Settings | File Templates.
--%>

<%

    List<Train> trainList = (List<Train>) request.getSession().getAttribute("trains");

    StringBuilder listToHtml = new StringBuilder();
    if (trainList.size() == 0){
        listToHtml.append("<hr>\n<div class=\"noRoute\">Маршрутов не найдено...</div>\n<hr>");
    }else {
        for (int i = 0; i < trainList.size(); i++) {

            if (get5Minute(trainList.get(i).getRoute().getTimeDateFirstStation())) {
                listToHtml.append("<div class = \"train\">\n");
                listToHtml.append("<form id=\"form"+i+"\" action=\"/gettrain\" method=\"get\" style = \"display:none;\">\n"+
                                    "<input type=\"text\" name=\"selectRoute\" value=\""+i+"\">\n"+
                                    "</form>\n");
            }else{
                listToHtml.append("<div class = \"notrain\">\n");
            }
            listToHtml.append("<h3>");
            if (trainList.get(i).getRoute().getFirstStation().getCity().getNameCity() != null)
                listToHtml.append("("+trainList.get(i).getRoute().getFirstStation().getCity().getNameCity()+") ");
            listToHtml.append(trainList.get(i).getRoute().getFirstStation().getNameStation()+ " - ");
            if (trainList.get(i).getRoute().getSecondStation().getCity().getNameCity() != null)
                listToHtml.append("("+trainList.get(i).getRoute().getSecondStation().getCity().getNameCity()+") ");
            listToHtml.append(trainList.get(i).getRoute().getSecondStation().getNameStation()+"</h3>\n");
            listToHtml.append("<hr>\n"
                            +"<table>\n"
                            +"<tr><td>Маршрут:</td><td>"+trainList.get(i).getRoute().getNameRoute()+"</td></tr>\n"
                            +"<tr><td>Поезд №:</td><td>"+trainList.get(i).getNumberTrain()+"</td></tr>\n"
                            +"<tr><td>Дата и время отправки:</td><td>"+trainList.get(i).getRoute().getTimeDateFirstStation()+"</td></tr>\n"
                            +"<tr><td>Дата и время прибытия:</td><td>"+trainList.get(i).getRoute().getTimeDateSecondStation()+"</td></tr>\n"
                            +"<tr><td>Расстояние:</td><td>"+trainList.get(i).getRoute().getDistance()+" км</td></tr>\n"
                            +"<tr><td>Цена от:</td><td>"+trainList.get(i).getRoute().getPrice()+" р.</td></tr>\n"
                            +"</table>\n");
            if (get5Minute(trainList.get(i).getRoute().getTimeDateFirstStation()))
                listToHtml.append("<div onclick = 'document.getElementById(\"form" + i + "\").submit();'>Купить билет</div></div>\n");
            else
                listToHtml.append("<div><a>Билеты не продаются</a></div></div>\n");
        }
    }

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainTicket</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/new_style.css">
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="css/linkMenu.css">
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
            <a href="/contacts">Контакты</a>
            <a href="/aboutus">О нас</a>
        </div>
    </div>

    <div id="body">
        <h2>Укажите маршрут и дату поездки</h2>
        <div id="form">
            <form action="/getroute" onsubmit="return validRoute()" method="get">
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
