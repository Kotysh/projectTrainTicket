<%@ page import="ru.dmitriykotyshov.trainticketobjects.Ticket" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 26.01.2018
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%

    String login = (String) request.getSession().getAttribute("login");
    String password = (String) request.getSession().getAttribute("password");

    if (login == null || password == null){
        request.getRequestDispatcher("inputAdmin.jsp").forward(request, response);
    }



%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/new_style.css">
</head>
<body>
<div id="header">
    <h1>Билеты</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addTicket" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td><label for="customerId"><span class="bold">ID пользователя:</span> </label></td><td colspan="3"><input type="text" id="customerId" name="customerId"></td>
                </tr>
                <tr>
                    <td><label for="wagonId"><span class="bold">ID вагона:</span> </label></td><td colspan="3"><input type="text" id="wagonId" name="wagonId"></td>
                </tr>
                <tr>
                    <td><label for="place"><span class="bold">Место:</span> </label></td><td colspan="3"><input type="text" id="place" name="place"></td>
                </tr>
                <tr>
                    <td><label><span class="bold">Дата покупки (YYYY-MM-DD):</span> </label></td>
                    <td><input type="text" id="year" name="year"></td>
                    <td><input type="text" id="month" name="month"></td>
                    <td><input type="text" id="day" name="day"></td>
                </tr>
                <tr>
                    <td><label for="firstRouteStationId"><span class="bold">ID первой станции на маршруте:</span> </label></td><td colspan="3"><input type="text" id="firstRouteStationId" name="firstRouteStationId"></td>
                </tr>
                <tr>
                    <td><label for="secondRouteStationId"><span class="bold">ID первой станции на маршруте станции:</span> </label></td><td colspan="3"><input type="text" id="secondROuteStationId" name="secondRouteStationId"></td>
                </tr>
                <tr>
                    <td><label for="price"><span class="bold">Цена:</span> </label></td><td colspan="3"><input type="text" id="price" name="price"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                    <td></td>
                </tr>
            </table>
        </form>
        <form action="/delTicket" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td><label for="delTicket"><span class="bold">ID:</span> </label></td><td><input type="text" id="delTicket" name="ticket"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица TICKET</h3>
        <table class="adminTable">
            <tr>
                <td>TICKET_ID</td>
                <td>CUSTOMER_ID</td>
                <td>WAGON_ID</td>
                <td>PLACE</td>
                <td>DATE_BUY</td>
                <td>FIRST_ROUTE_STATION_ID</td>
                <td>SECOND_ROUTE_STATION_ID</td>
                <td>PRICE</td>
            </tr>
            <%
                List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
                StringBuilder writeTickets = new StringBuilder();
                for(Ticket t: tickets){
                    writeTickets.append("<tr>");
                    writeTickets.append("<td>"+t.getTicketId()+"</td>");
                    writeTickets.append("<td>"+t.getCustomer().getCustomerId()+"("+
                            t.getCustomer().getFirstName()+"<br>"+
                            t.getCustomer().getMiddleName()+"<br>"+
                            t.getCustomer().getLastName()+")</td>");
                    writeTickets.append("<td>"+t.getWagon().getWagonId()+"</td>");
                    writeTickets.append("<td>"+t.getPlace()+"</td>");
                    writeTickets.append("<td>"+t.getDateBuy()+"</td>");
                    writeTickets.append("<td>"+t.getFirstStation().getRouteStationId()+"</td>");
                    writeTickets.append("<td>"+t.getSecondStation().getRouteStationId()+"</td>");
                    writeTickets.append("<td>"+t.getPrice()+"</td><tr>");

                }
            %>
            <%=writeTickets%>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
