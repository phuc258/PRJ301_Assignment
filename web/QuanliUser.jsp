<%-- 
    Document   : QuanliUser
    Created on : Jun 2, 2025, 4:49:22 PM
    Author     : toila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %> 
<%@page import="dto.User" %>
<%@page import="dao.UserDAO" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quan Li User</title>
    </head>
    <body>
        <c:set var="USER" value="${sessionScope.USER}"/>
        <c:if test="${empty sessionScope.USER}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="adminDashboard" />
            <button class="btn">🏠 Về Dashboard</button>
        </form>         <form method="post" action="MainController">
            <input type="text" name="findEmail" value="${sessionScope.findEmail}" />
            <input type="submit" value="Find User" name="action" />
        </form>

        <c:set var="listUser" value="${requestScope.listUser}"/>
        <c:if test="${not empty listUser}" >
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>name</th>
                        <th>email</th>
                        <th>password</th>
                        <th>role</th>
                        <th>status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${listUser}">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                            <td>${u.email}</td>
                            <td>${u.password}</td>
                            <td>${u.role}</td>
                            <td>${u.status}</td>
                            <td>  
                                <form action="MainController" method="post">
                                    <input type="hidden" name="emailUser" value="${u.email}"/>
                                    <input type="hidden" name="action" value="ManagerUsers"/>
                                    <c:set var="buttonLabel" 
                                           value="${fn:toLowerCase(u.status) eq 'blocked' ? 'UnBan' : 'Ban'}"/>
                                    <c:set var="buttonValue" 
                                           value="${fn:toLowerCase(u.status) eq 'blocked' ? 'moKhoa' : 'ban'}"/>

                                    <button class="btn" value="${buttonValue}" name="yeucau">${buttonLabel}</button>

                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
