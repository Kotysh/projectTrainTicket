<%@ page import="ru.dmitriykotyshov.trainticketobjects.Wagon" %>
<%@ page import="java.util.Set" %>
<%@ page import="static ru.dmitriykotyshov.other.PrintWagon.printWagonSit" %><%--
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
    placeToHtml.append("<table>");
    placeToHtml.append("<tr><td>Маршрут:</td><td><span id=\"route\">"+wagon.getTrain().getRoute().getNameRoute()+"</span></td></tr>");
    placeToHtml.append("<tr><td>Поезд №:</td><td><span id=\"numberTrain\">"+wagon.getTrain().getNumberTrain()+"</span></td></tr>");
    placeToHtml.append("<tr><td>Дата и время отправки:</td><td><span id=\"firstRouteStation\">"+wagon.getTrain().getRoute().getTimeDateFirstStation()+"</span></td></tr>");
    placeToHtml.append("<tr><td>Дата и время прибытия:</td><td><span id=\"secondRouteStation\">"+wagon.getTrain().getRoute().getTimeDateSecondStation()+"</span></td></tr>");
    placeToHtml.append("<tr><td>Номер вагона:</td><td><span id=\"orderWagon\">"+wagon.getOrder()+"</span></td></tr>");
    placeToHtml.append("<tr><td>Тип вагона:</td><td>"+wagon.getTypeWagon()+"</td></tr>");
    placeToHtml.append("<tr><td>Туалет:</td><td>"+(wagon.isBioTiolet()?"да":"нет")+"</td></tr>");
    placeToHtml.append("<tr><td>Кондиционер:</td><td>"+(wagon.isAirCondition()?"да":"нет")+"</td></tr>");
    placeToHtml.append("<tr><td>Общее количество мест:</td><td>"+wagon.getCountPlace()+"</td></tr>");
    placeToHtml.append("<tr><td>Свободное количество мест:</td><td>"+(wagon.getCountPlace()-setPlace.size())+"</td></tr>");
    placeToHtml.append("<tr><td>Цена:</td><td><span id=\"price\">"+wagon.getPrice()+"</span>р.</td></tr>");
    placeToHtml.append("</table>");
    placeToHtml.append("<hr>");
    placeToHtml.append("<p>Заполните анкету, выберите место и нажмите купить</p>");
    placeToHtml.append("<br>");

    StringBuilder placeRadioButtons = new StringBuilder();
    if (wagon.getTypeWagon().equals("сидячий")) placeRadioButtons = printWagonSit(wagon, setPlace, wagon.isBioTiolet());

    StringBuilder png = new StringBuilder();

    if (wagon.getTypeWagon().equals("сидячий")){
        if (wagon.isBioTiolet()){
            png.append("../img/sit_tiolet.png");
        }else{
            png.append("../img/sit.png");
        }
    }



%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainTicket</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/styles_place.css">
    <link rel="stylesheet" href="css/new_style.css">
    <link rel="stylesheet" href="css/linkMenu.css">
    <link href="css/datepicker.min.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="js/datepicker.js"></script>
    <script src="js/valid.place.js"></script>
    <script src="js/doget.js" defer></script>
    <style>
        #wagonPlaces{
            border: 1px solid black;
            border-radius: 10px;
            margin: 0 auto 0;
            background-image: url(<%=png%>);
        }
    </style>
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
        <div class="backClick"><a onclick="javascript:history.back(); return false;">Назад к выбору вагона</a></div>
        <%=placeToHtml%>
        <form action="javascript: goBuy();" onsubmit="return validPlace()" method="get">
            <table>
                <tr>
                    <td><label for="firstName"><span class="bold">*Имя:</span> </label></td><td colspan="2"><input type="text" id="firstName" name="firstName"></td>
                </tr>
                <tr>
                    <td><label for="middleName"><span class="bold">*Отчество:</span> </label></td><td colspan="2"><input type="text" id="middleName" name="middleName"></td>
                </tr>
                <tr>
                    <td><label for="lastName"><span class="bold">*Фамилия:</span> </label></td><td colspan="2"><input type="text" id="lastName" name="lastName"></td>
                </tr>
            </table>
            <hr>
            <table>
                <tr>
                    <td><label for="birthday"><span class="bold">Дата рождения: </span></label></td><td><input type="text" class="datepicker-here" id="birthday" name="birthday" readonly></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><span class="bold">*Пол: </span><input name="gender" type="radio" value="1">Мужской</td>
                    <td><input name="gender" type="radio" value="">Женский</td>
                    <td><span id="noGender">&nbsp;</span></td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><span class="bold">*Выберите тип документа: </span></td>
                    <td colspan="2"><span id="noDocument">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></td>
                </tr>
                <tr>
                    <td colspan="3"><input name="document" type="radio" value="1">паспорт</td>
                </tr>
                <tr>
                    <td colspan="3"><input name="document" type="radio" value="2">загранпаспорт</td>
                </tr>
                <tr>
                    <td colspan="3"><input name="document" type="radio" value="3">военный билет</td>
                </tr>
                <tr>
                    <td colspan="3"><input name="document" type="radio" value="4">свидетельство о рождении</td>
                </tr>
                <tr>
                    <td colspan="3"><input name="document" type="radio" value="5">паспорт иностранного гражданина</td>
                </tr>
            </table>
            <table>
                <tr>
                    <td><label for="docNumber"><span class="bold">*Номер документа:</span> </label></td><td colspan="2"><input type="text" id="docNumber" name="docNumber"></td>
                </tr>
                <tr>
                    <td><label for="email"><span class="bold">*email:</span> </label></td><td colspan="2"><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td><label for="telephone"><span class="bold">телефон:</span> </label></td><td colspan="2"><input type="text" id="telephone" name="telephone"></td>
                </tr>
                <tr>
                    <td colspan="3"><input type="submit" value="Купить"></td>
                </tr>
                <tr>
                    <td id="message" colspan="3">Поля отмеченные звездочкой (*) - обязательно должны быть заполнены</td>
                </tr>
                <tr>
                    <td><span id="noPlace">&nbsp;</span></td>
                </tr>
            </table>
            <%=placeRadioButtons%>
        </form>
    </div>
    </div>

    <div id="footer">
        <p>Дмитрий Котяшов 2к18<br>
            kotyshok@yandex.ru</p>
    </div>

</body>
</html>