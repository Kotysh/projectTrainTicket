<%@ page import="ru.dmitriykotyshov.trainticketobjects.Document" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 13.01.2018
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body>
<div id="header">
    <h1>Документы</h1>
</div>
<div id="wrap">
<div id="body">
    <p><a href="/admin">На главную администратора</a></p>
    <form action="/addDocument" method="get">
        <h3>Добавление:</h3>
        <table align="center">
            <tr>
                <td><label for="addDocument"><span class="bold">Документ:</span> </label></td><td><input type="text" id="addDocument" name="document"></td>
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
            <td>Document_id</td><td>Document</td>
        </tr>
        <%
            List<Document> documents = (List<Document>) request.getAttribute("documents");
            StringBuilder stringBuilder = new StringBuilder();
            for(Document d: documents){
                stringBuilder.append("<tr><td>");
                stringBuilder.append(d.getId()+"</td>");
                stringBuilder.append("<td>"+d.getDocument()+"</td></tr>");
            }
        %>
        <%=stringBuilder%>
    </table>
</div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
