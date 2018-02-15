<%@ page import="ru.dmitriykotyshov.trainticketobjects.Document" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 23:06
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
    <h1>Документы</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
<div id="bodyAdmin">
    <p><a href="/admin">На главную администратора</a></p>
    <form action="/addDocument" method="get">
        <h3>Добавление:</h3>
        <table align="center">
            <tr>
                <td><label for="document"><span class="bold">Документ:</span> </label></td><td><input type="text" id="document" name="document"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Добавить"></td>
            </tr>
        </table>
    </form>
    <form action="/delDocument" method="get">
        <h3>Удаление:</h3>
        <table align="center">
            <tr>
                <td><label for="delDocument"><span class="bold">ID:</span> </label></td><td><input type="text" id="delDocument" name="document"></td>
                <td colspan="2"><input type="submit" value="Удалить"></td>
            </tr>
        </table>
    </form>
    <h3>Таблица DOCUMENT</h3>
    <table class="adminTable">
        <tr>
            <td>DOCUMENT_ID</td>
            <td>DOCUMENT</td>
        </tr>
        <%
            List<Document> documents = (List<Document>) request.getAttribute("documents");
            StringBuilder writeDocuments = new StringBuilder();
            for(Document d: documents){
                writeDocuments.append("<tr><td>");
                writeDocuments.append(d.getId()+"</td>");
                writeDocuments.append("<td>"+d.getDocument()+"</td></tr>");
            }
        %>
        <%=writeDocuments%>
    </table>
</div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
