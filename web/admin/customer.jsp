<%@ page import="java.util.List" %>
<%@ page import="ru.dmitriykotyshov.trainticketobjects.Customer" %>
<%@ page import="ru.dmitriykotyshov.trainticketobjects.Document" %>
<%@ page import="static ru.dmitriykotyshov.other.ValidAdmin.validationSuperAdmin" %>
<%@ page import="static ru.dmitriykotyshov.other.Message.insufficientRights" %>
<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 25.01.2018
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%

    String login = (String) request.getSession().getAttribute("login");
    String password = (String) request.getSession().getAttribute("password");

    if (login == null || password == null){
        request.getRequestDispatcher("inputAdmin.jsp").forward(request, response);
    }
    Integer typeAdmin = Integer.valueOf((String) request.getSession().getAttribute("typeAdmin"));
    if (!validationSuperAdmin(typeAdmin))
        insufficientRights(request, response);


    List<Document> documents = (List<Document>) request.getAttribute("documents");

    StringBuilder documentsBuilder = new StringBuilder();

    for (int i = 0 ; i<documents.size(); i++){

        documentsBuilder.append("<tr>\n<td></td>"+
                "<td><input name = \"document\" type=\"radio\""+
                "value=\""+documents.get(i).getId()+"\">"+documents.get(i).getDocument()+"</td>\n"+
                "</tr>\n");

    }


%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/new_style.css">
    <script src="../js/admin/valid.customer.js"></script>
    <script src="../js/admin/valid.delete.js"></script>
</head>
<body>
<div id="header">
    <h1>Пользователи</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addCustomer" onsubmit="return validCustomer()" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td><label for="firstName"><span class="bold">Имя:</span> </label></td><td colspan="3"><input type="text" id="firstName" name="firstName"><span id="erFirstName">&nbsp;</span></td>
                </tr>
                <tr>
                    <td><label for="middleName"><span class="bold">Отчество:</span> </label></td><td colspan="3"><input type="text" id="middleName" name="middleName"><span id="erMiddleName">&nbsp;</span></td>
                </tr>
                <tr>
                    <td><label for="lastName"><span class="bold">Фамилия:</span> </label></td><td colspan="3"><input type="text" id="lastName" name="lastName"><span id="erLastName">&nbsp;</span></td>
                </tr>
                <tr>
                    <td><label><span class="bold">Дата рождения (YYYY-MM-DD):</span><span id="noDate">&nbsp;</span> </label></td>
                    <td><input type="text" id="year" name="year"></td>
                    <td><input type="text" id="month" name="month"></td>
                    <td><input type="text" id="day" name="day"></td>
                </tr>
                <tr>
                    <td><span class="bold">Пол: </span><span id="noGender">&nbsp;</span></td>
                    <td><input name="gender" type="radio" value="1">Мужской</td>
                    <td><input name="gender" type="radio" value="">Женский</td>
                </tr>
                <tr><td>
                <table align="center">
                    <tr>
                        <td colspan="2">Тип документа:<span id="noDocument">&nbsp;</span></td>
                    </tr>
                    <%=documentsBuilder%>
                    <tr>
                        <td><label for="documentNumber"><span class="bold">Номер документа:</span> </label></td><td colspan="3"><input type="text" id="documentNumber" name="documentNumber"></td>
                    </tr>
                    <tr>
                        <td><label for="email"><span class="bold">Email:</span> </label></td><td colspan="3"><input type="text" id="email" name="email"></td>
                    </tr>
                    <tr>
                        <td colspan="3"><span id="erTelephone"></span></td>
                    </tr>
                    <tr>
                        <td><label for="telephone"><span class="bold">Телефон:</span></label></td><td>+7<input type="text" id="telephone" name="telephone"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2"><input type="submit" value="Добавить"></td>
                        <td></td>
                    </tr>
                </td></tr>
                </table>
                <tr>
                    <td><span id="message">&nbsp;</span></td>
            </tr>
            </table>
        </form>
        <form action="/delCustomer" onsubmit="return validDelete('delCustomer')" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td colspan="3" id="mesDel"></td>
                </tr>
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

