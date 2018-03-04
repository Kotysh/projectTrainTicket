<%@ page import="ru.dmitriykotyshov.trainticketobjects.Wagon" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.dmitriykotyshov.trainticketobjects.Document" %>
<%@ page import="static ru.dmitriykotyshov.other.PrintWagon.getWayToPNGWagon" %>
<%@ page import="static ru.dmitriykotyshov.other.PrintWagon.printWagon" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 22.01.2018
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%

    Wagon wagon = (Wagon) request.getSession().getAttribute("wagon");
    Set<Integer> setPlace = (Set<Integer>) request.getSession().getAttribute("places");

    StringBuilder placeToHtml = new StringBuilder();

    placeToHtml.append("<div class = \"train\">\n"
                        +"<h3>");
    if (wagon.getTrain().getRoute().getFirstStation().getCity().getNameCity() != null && !wagon.getTrain().getRoute().getFirstStation().getCity().getNameCity().equals("null"))
        placeToHtml.append("("+wagon.getTrain().getRoute().getFirstStation().getCity().getNameCity()+") ");
    placeToHtml.append(wagon.getTrain().getRoute().getFirstStation().getNameStation()+ " - ");
    if (wagon.getTrain().getRoute().getSecondStation().getCity().getNameCity() != null && !wagon.getTrain().getRoute().getSecondStation().getCity().getNameCity().equals("null"))
        placeToHtml.append("("+wagon.getTrain().getRoute().getSecondStation().getCity().getNameCity()+") ");
    placeToHtml.append(wagon.getTrain().getRoute().getSecondStation().getNameStation()+"</h3>\n"
                        +"<hr>\n"
                        +"<table>\n"
                        +"<tr><td>Маршрут:</td><td><span id=\"route\">"+wagon.getTrain().getRoute().getNameRoute()+"</span></td></tr>\n"
                        +"<tr><td>Поезд №:</td><td><span id=\"numberTrain\">"+wagon.getTrain().getNumberTrain()+"</span></td></tr>\n"
                        +"<tr><td>Дата и время отправки:</td><td><span id=\"firstRouteStation\">"+wagon.getTrain().getRoute().getTimeDateFirstStation()+"</span></td></tr>\n"
                        +"<tr><td>Дата и время прибытия:</td><td><span id=\"secondRouteStation\">"+wagon.getTrain().getRoute().getTimeDateSecondStation()+"</span></td></tr>\n"
                        +"<tr><td>Номер вагона:</td><td><span id=\"orderWagon\">"+wagon.getOrder()+"</span></td></tr>\n"
                        +"<tr><td>Тип вагона:</td><td>"+wagon.getTypeWagon()+"</td></tr>\n"
                        +"<tr><td>Туалет:</td><td>"+(wagon.isBioTiolet()?"да":"нет")+"</td></tr>\n"
                        +"<tr><td>Кондиционер:</td><td>"+(wagon.isAirCondition()?"да":"нет")+"</td></tr>\n"
                        +"<tr><td>Общее количество мест:</td><td>"+wagon.getCountPlace()+"</td></tr>\n"
                        +"<tr><td>Свободное количество мест:</td><td>"+(wagon.getCountPlace()-setPlace.size())+"</td></tr>\n"
                        +"<tr><td>Цена:</td><td><span id=\"price\">"+wagon.getPrice()+"</span>р.</td></tr>\n"
                        +"</table>\n"
                        +"<hr>\n"
                        +"<p>Заполните анкету, выберите место и нажмите купить</p>\n"
                        +"<br>");

    List<Document> documents = (List<Document>) request.getSession().getAttribute("documents");

    StringBuilder documentsBuilder = new StringBuilder();




    for (int i = 0 ; i<documents.size(); i++){

        documentsBuilder.append("<tr>\n"+
                                "<td colspan=\"3\"><input name = \"document\" type=\"radio\""+
                                "value=\""+documents.get(i).getId()+"\">"+documents.get(i).getDocument()+"</td>\n"+
                                "</tr>\n");

    }


    StringBuilder placeRadioButtons = printWagon(wagon, setPlace, wagon.isBioTiolet());

    StringBuilder png = new StringBuilder(getWayToPNGWagon(wagon.getTypeWagon(), wagon.isBioTiolet()));


%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>TrainTicket</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/styles_places.css">
    <link rel="stylesheet" href="css/new_style.css">
    <link rel="stylesheet" href="css/linkMenu.css">
    <script src="js/valid.places.js"></script>
    <link href="css/datepicker.min.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="js/datepicker.js"></script>
    <style>
        #wagonPlaces{
            border: 1px solid black;
            border-radius: 10px;
            margin: 0 auto 0;
            background-image: url(<%=png%>);
            width: 836px;
            height: 268px;
        }
    </style>
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
        <div class="backClick"><a onclick="javascript:history.back(); return false;">Назад к выбору вагона</a></div>
        <%=placeToHtml%>
        <form action="/buy" onsubmit="return validPlace()" method="post">
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
                    <td colspan="2"><span id="noDocument">&nbsp;</span></td>
                </tr>
                <%=documentsBuilder%>
            </table>
            <table>
                <tr>
                    <td><label for="docNumber"><span class="bold">*Номер документа:</span> </label></td><td colspan="2"><input type="text" id="docNumber" name="docNumber"></td>
                </tr>
                <tr>
                    <td><label for="email"><span class="bold">*email:</span> </label></td><td colspan="2"><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td colspan="2"><span id="erTelephone"></span></td>
                </tr>
                <tr>
                    <td><label for="telephone"><span class="bold">телефон:</span> </label></td><td colspan="2">+7<input type="text" id="telephone" name="telephone"></td>
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