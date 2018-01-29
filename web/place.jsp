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
    placeToHtml.append("<p>Маршрут: <span id=\"route\">"+wagon.getTrain().getRoute().getNameRoute()+"</span></p>");
    placeToHtml.append("<p>Поезд №: <span id=\"numberTrain\">"+wagon.getTrain().getNumberTrain()+"</span></p>");
    placeToHtml.append("<p>Дата и время отправки: <span id=\"firstRouteStation\">"+wagon.getTrain().getRoute().getTimeDateFirstStation()+"</span></p>");
    placeToHtml.append("<p>Дата и время прибытия: <span id=\"secondRouteStation\">"+wagon.getTrain().getRoute().getTimeDateSecondStation()+"</span></p>");
    placeToHtml.append("<p>Номер вагона: <span id=\"orderWagon\">"+wagon.getOrder()+"</span></p>");
    placeToHtml.append("<p>Тип вагона: "+wagon.getTypeWagon()+"</p>");
    placeToHtml.append("<p>Туалет: "+(wagon.isBioTiolet()?"да":"нет")+"</p>");
    placeToHtml.append("<p>Кондиционер: "+(wagon.isAirCondition()?"да":"нет")+"</p>");
    placeToHtml.append("<p>Общее количество мест: "+wagon.getCountPlace()+"</p>");
    placeToHtml.append("<p>Свободное количество мест: "+(wagon.getCountPlace()-setPlace.size())+"</p>");
    placeToHtml.append("<hr>");
    placeToHtml.append("<p>Заполните анкету, выберите место и нажмите купить</p>");
    placeToHtml.append("<br>");

    StringBuilder placeRadioButtons = new StringBuilder();
    for (int i=0; i<wagon.getCountPlace(); i++){

        if (setPlace.contains(i+1)) {
            placeRadioButtons.append("<span class=\"noPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\" disabled>" + (i + 1) + "</span>");
        }else{
            placeRadioButtons.append("<span class=\"yesPlace\"><input name=\"place\" type=\"radio\" value=\"" + (i + 1) + "\">" + (i + 1) + "</span>");
        }
            if (i%10 == 9)placeRadioButtons.append("<br>");

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
        <div class="backClick"><a onclick="javascript:history.back(); return false;">Назад к выбору вагона</a></div>
        <%=placeToHtml%>
        <form action="javascript: goBuy();" method="get">
            <table>
                <tr>
                    <td><label for="firstName"><span class="bold">Имя:</span> </label></td><td colspan="3"><input type="text" id="firstName" name="firstName"></td>
                </tr>
                <tr>
                    <td><label for="middleName"><span class="bold">Отчество:</span> </label></td><td colspan="3"><input type="text" id="middleName" name="middleName"></td>
                </tr>
                <tr>
                    <td><label for="lastName"><span class="bold">Фамилия:</span> </label></td><td colspan="3"><input type="text" id="lastName" name="lastName"></td>
                </tr>
            </table>
            <hr>
            <table>
                <tr>
                    <td><label><span class="bold">Дата рождения:</span> </label></td>
                    <td>год:<input type="text" id="year" name="year"></td>
                    <td>месяц:<input type="text" id="month" name="month"></td>
                    <td>день:<input type="text" id="day" name="day"></td>
                </tr>
                <tr>
                    <td><span class="bold">Пол:</span><input name="gender" type="radio" value="1">Мужской</td>
                    <td colspan="3"><input name="gender" type="radio" value="">Женский</td>
                </tr>
                <tr>
                    <td colspan="4"><span class="bold">Выберите тип документа:</span></td>
                </tr>
                <tr>
                    <td colspan="4"><input name="document" type="radio" value="1">паспорт</td>
                </tr>
                <tr>
                    <td colspan="4"><input name="document" type="radio" value="2">загранпаспорт</td>
                </tr>
                <tr>
                    <td colspan="4"><input name="document" type="radio" value="3">военный билет</td>
                </tr>
                <tr>
                    <td colspan="4"><input name="document" type="radio" value="4">свидетельство о рождении</td>
                </tr>
                <tr>
                    <td colspan="4"><input name="document" type="radio" value="5">паспорт иностранного гражданина</td>
                </tr>
                <tr>
                    <td><label for="docNumber"><span class="bold">Номер документа:</span> </label></td><td colspan="3"><input type="text" id="docNumber" name="docNumber"></td>
                </tr>
                <tr>
                    <td><label for="email"><span class="bold">email:</span> </label></td><td colspan="3"><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td><label for="telephone"><span class="bold">телефон:</span> </label></td><td colspan="3"><input type="text" id="telephone" name="telephone"></td>
                </tr>
                <tr>
                    <td colspan="4"><input type="submit" value="Купить"></td>
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
<script src="js/scripts.js" defer>
</script>
