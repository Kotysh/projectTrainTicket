<%@ page import="ru.dmitriykotyshov.entity.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.dmitriykotyshov.entity.TypeAdmin" %>
<%@ page import="static ru.dmitriykotyshov.other.Message.insufficientRights" %>
<%@ page import="static ru.dmitriykotyshov.other.ValidAdmin.validationSuperAdmin" %><%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 12.02.2018
  Time: 19:21
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


    List<TypeAdmin> types = (List<TypeAdmin>) request.getAttribute("types");
    StringBuilder adminBuilder = new StringBuilder();

    for (int i=0; i<types.size(); i++){

        adminBuilder.append("<option value=\""+types.get(i).getId()+"\">"+
                            types.get(i).getType()+
                            "</option>\n");

    }

    List<String> recAdmins = (List<String>) request.getAttribute("recAdmins");

    StringBuilder adminsBuilder = new StringBuilder();
    for (int i=0; i<recAdmins.size(); i++){

        adminsBuilder.append("<p align=\"cennter\"><pre>\n"+
                            recAdmins.get(i)+
                            "</pre></p>\n");

    }

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Administrator</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../css/new_style.css">
    <script src="../js/admin/valid.admin.js"></script>
    <script src="../js/admin/valid.delete.js"></script>
</head>
<body>
<div id="header">
    <h1>Администраторы</h1>
    <div id="myAdmin">Вы зашли как, <%=login%> <a href="/exitAdmin">Выйти</a></div>
</div>
<div id="wrap">
    <div id="bodyAdmin">
        <p><a href="/admin">На главную администратора</a></p>
        <form action="/addAdmin" onsubmit="return validAdmin()" method="get">
            <h3>Добавление:</h3>
            <table align="center">
                <tr>
                    <td colspan="2"><span id="erName"></span></td>
                </tr>
                <tr>
                    <td><label for="name"><span class="bold">Имя:</span> </label></td><td colspan="3"><input type="text" id="name" name="name"></td>
                </tr>
                <tr>
                    <td colspan="2"><span id="erPass"></span></td>
                </tr>
                <tr>
                    <td><label for="password"><span class="bold">Пароль:</span> </label></td><td colspan="3"><input type="text" id="password" name="password"></td>
                </tr>
                <tr>
                    <td><label for="typeAdmin"><span class="bold">Тип администратора:</span></label></td>
                    <td>
                        <select size="1" name="typeAdmin" id="typeAdmin">
                            <option disabled>выберите тип администратора</option>
                            <%=adminBuilder%>
                        </select>

                    </td>
                </tr>
                <tr>
                    <td colspan="2"><span id="erBoss"></span></td>
                </tr>
                <tr>
                    <td><label for="boss"><span class="bold">Начальник:</span> </label></td><td colspan="3"><input type="text" id="boss" name="boss"></td>
                </tr>
                <tr>
                    <td></td>
                    <td colspan="2"><input type="submit" value="Добавить"></td>
                    <td></td>
                </tr>
            </table>
        </form>
        <form action="/delAdmin" onsubmit="return validDelete('delAdmin')" method="get">
            <h3>Удаление:</h3>
            <table align="center">
                <tr>
                    <td colspan="2" id="mesDel"></td>
                </tr>
                <tr>
                    <td><label for="delAdmin"><span class="bold">ID:</span> </label></td><td><input type="text" id="delAdmin" name="delAdmin"></td>
                    <td colspan="2"><input type="submit" value="Удалить"></td>
                </tr>
            </table>
        </form>
        <h3>Таблица ADMIN</h3>
        <table class="adminTable">
            <tr>
                <td>ADMIN_ID</td>
                <td>NAME</td>
                <td>PASS</td>
                <td>TYPE_ADMIN_ID</td>
                <td>BOSS</td>
            </tr>
            <%
                List<Admin> admins = (List<Admin>) request.getAttribute("admins");
                StringBuilder writeAdmins = new StringBuilder();
                for(Admin a: admins){
                    writeAdmins.append("<tr>");
                    writeAdmins.append("<td>"+a.getId()+"</td>");
                    writeAdmins.append("<td>"+a.getName()+"</td>");
                    writeAdmins.append("<td>"+a.getPass()+"</td>");
                    writeAdmins.append("<td>"+a.getTypeAdmin().getId()+" ("+a.getTypeAdmin().getType()+")</td>");
                    writeAdmins.append("<td>"+a.getBoss()+"</td></tr>");
                }
            %>
            <%=writeAdmins%>
        </table>
        <table align="center">
            <tr>
                <td align="center">Иерархия администраторов</td>
            </tr>
            <tr>
                <td><%=adminsBuilder%></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
        </table>
    </div>
</div>
<div id="footer">
    <p>Дмитрий Котяшов 2к18<br>
        kotyshok@yandex.ru</p>
</div>
</body>
</html>
