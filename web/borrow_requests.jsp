<%-- 
    Document   : borrow_requests
    Created on : Jul 8, 2025, 5:46:33 PM
    Author     : toila
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dto.BorrowRequest"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Borrrow Requets</title>
    </head>
    <body>
        <c:set var="USER" value="${sessionScope.USER}"/>
        <c:if test="${empty sessionScope.USER}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="adminDashboard" />
            <button class="btn">🏠 Về Dashboard</button>
        </form>
        <h2>Borrow/Return Requests</h2>
        <table border="1">
            <tr>
                <th>ID</th><th>User</th><th>Book</th><th>Request Date</th><th>Status</th><th>Action</th>
            </tr>
            <%
                ArrayList<BorrowRequest> list = (ArrayList<BorrowRequest>) request.getAttribute("requests");
                for (BorrowRequest r : list) {
            %>
            <tr>
                <td><%= r.getId()%></td>
                <td><%= r.getUserName()%></td>
                <td><%= r.getBookTitle()%></td>
                <td><%= r.getRequestDate()%></td>
                <td><%= r.getStatus()%></td>
                <td>
                    <% if (r.getStatus().equals("pending")) {%>
                    <form method="post" style="display:inline;" action="BorrowRequestController">
                        <input type="hidden" name="requestId" value="<%= r.getId()%>" />
                        <button name="action" value="approve">Approve</button>
                        <button name="action" value="reject">Reject</button>
                    </form>
                    <% } else {%>
                    <i><%= r.getStatus()%></i>
                    <% } %>
                </td>
            </tr>
            <% }%>
        </table>
    </body>
</html>
