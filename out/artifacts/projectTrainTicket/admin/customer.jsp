<%@ page import="java.util.List" %>
<%@ page import="ru.dmitriykotyshov.trainticketobjects.Customer" %>
<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 25.01.2018
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<div id="header">
    <h1>Пользователи</h1>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addCustomer" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td><label for="firstName"><span class="bold">Имя:</span> </label></td><td colspan="3"><input type="text" id="firstName" name="firstName"></td>
                </tr>
                <tr>
                    <td><label for="middleName"><span class="bold">Отчество:</span> </label></td><td colspan="3"><input type="text" id="middleName" name="middleName"></td>
                </tr>
                <tr>
                    <td><label for="lastName"><span class="bold">Фамилия:</span> </label></td><td colspan="3"><input type="text" id="lastName" name="lastName"></td>
                </tr>
                <tr>
                    <td><label><span class="bold">Дата рождения (YYYY-MM-DD):</span> </label></td>
                    <td><input type="text" id="year" name="year"></td>
                    <td><input type="text" id="month" name="month"></td>
                    <td><input type="text" id="day" name="day"></td>
                </tr>
                <tr>
                    <td><label for="gender"><span class="bold">Пол:</span> </label></td><td colspan="3"><input type="text" id="gender" name="gender"></td>
                </tr>
                <tr>
                    <td><label for="documentID"><span class="bold">ID документа:</span> </label></td><td colspan="3"><input type="text" id="documentID" name="documentID"></td>
                </tr>
                <tr>
                    <td><label for="documentNumber"><span class="bold">Номер документа:</span> </label></td><td colspan="3"><input type="text" id="documentNumber" name="documentNumber"></td>
                </tr>
                <tr>
                    <td><label for="email"><span class="bold">Email:</span> </label></td><td colspan="3"><input type="text" id="email" name="email"></td>
                </tr>
                <tr>
                    <td><label for="telephone"><span class="bold">Телефон:</span> </label></td><td colspan="3"><input type="text" id="telephone" name="telephone"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                    <td></td>
                </tr>
            </table>
        </form>
        <form action="/delCustomer" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td><label for="delCustomer"><span class="bold">ID:</span> </label></td><td><input type="text" id="delCustomer" name="customer"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица CUSTOMER</h3>
        <table class="adminTable">
            <tr>
                <td>CUSTOMER_ID</td>
                <td>FIRST_NAME</td>
                <td>MIDDLE_NAME</td>
                <td>LAST_NAME</td>
                <td>BIRTHDAY</td>
                <td>GENDER</td>
                <td>DOCUMENT_ID</td>
                <td>DOC_NUMBER</td>
                <td>EMAIL</td>
                <td>TELEPHONE</td>
            </tr>
            <%
                List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                StringBuilder writeCustomers = new StringBuilder();
                for(Customer c: customers){
                    writeCustomers.append("<tr>");
                    writeCustomers.append("<td>"+c.getCustomerId()+"</td>");
                    writeCustomers.append("<td>"+c.getFirstName()+"</td>");
                    writeCustomers.append("<td>"+c.getMiddleName()+"</td>");
                    writeCustomers.append("<td>"+c.getLastName()+"</td>");
                    writeCustomers.append("<td>"+c.getBirthday()+"</td>");
                    writeCustomers.append("<td>"+c.getGender()+"</td>");
                    writeCustomers.append("<td>"+c.getDocument().getId()+"("+c.getDocument().getDocument()+")</td>");
                    writeCustomers.append("<td>"+c.getDocNumber()+"</td>");
                    writeCustomers.append("<td>"+c.getEmail()+"</td>");
                    writeCustomers.append("<td>"+c.getTelephone()+"</td></tr>");
                }
            %>
            <%=writeCustomers%>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>

