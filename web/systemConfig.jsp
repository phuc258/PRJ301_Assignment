<%@page import="dto.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.SystemConfigDAO" %>
<%@ page import="dto.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    SystemConfigDAO dao = new SystemConfigDAO();
    ArrayList<Config> configList = dao.getAllConfig();
%>
<html>
    <head>
        <title>Cấu hình hệ thống</title>
    </head>
    <body>
        <%
            User us = (User) session.getAttribute("USER");
            if (us == null) {
                response.sendRedirect("index.jsp");
            }
        %>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="adminDashboard" />
            <button class="btn">🏠 Về Dashboard</button>
        </form>        <h2>Cấu hình hệ thống</h2>
        <form action="UpdateConfigController" method="post">
            <table border="1">
                <tr>
                    <th>Tham số</th>
                    <th>Giá trị</th>
                    <th>Mô tả</th>
                </tr>
                <% for (Config c : configList) {%>
                <tr>
                    <td><%= c.getConfig_key()%></td>
                    <td>
                        <input type="text" name="<%= c.getConfig_key()%>" value="<%= c.getConfig_value()%>"/>
                    </td>
                    <td><%= c.getDescription()%></td>
                </tr>
                <% }%>
            </table>
            <br/>
            <input type="submit" value="Cập nhật cấu hình"/>
        </form>
    </body>
</html>
