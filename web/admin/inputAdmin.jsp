<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 09.02.2018
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%

    String login = (String) request.getSession().getAttribute("login");
    String password = (String) request.getSession().getAttribute("password");

    if (login != null || password != null){
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }


%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/new_style.css">
</head>
<body>
<div id="header">
    <h1>Раздел администратора</h1>
</div>
<div id="wrap">
    <div id="body">
        <h2>Выполните вход</h2>


        <form action="/inputAdmin" method="get">
            <table align="center">

                <tr align="center">
                    <td colspan="2">Вход</td>
                </tr>
                <tr>
                    <td><label for="login">Логи: </label></td><td><input type="text" id="login" name="login"></td>
                </tr>
                <tr>
                    <td><label for="password">Пароль: </label></td><td><input type="password" id="password" name="password"></td>
                </tr>
                <tr align="center">
                    <td colspan="2"><input type="submit" value="Войти"></td>
                </tr>

            </table>
        </form>

    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>

