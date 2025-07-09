<%-- 
    Document   : AddNewBook
    Created on : Jun 7, 2025, 10:48:58 AM
    Author     : toila
--%>

<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Book</title>
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
</form>

<form action="MainController" method="post" style="padding:5%" accept-charset="utf-8">
    <input type="hidden" name="action" value="addnewbookcontroller" />     
    <p>Title: <input type="text" name="txttitle" required></p>
    <p>Author: <input type="text" name="txtauthor" required></p>
    <p>Category: <input type="text" name="txtcategory" required></p>
    <p>ISBN: <input type="text" name="txtisbn" required></p>
    <p>Published year: <input type="number" name="txtpublished_year" required></p>
    <p>Total copies: <input type="number" name="txttotal_copies" required></p>
    <p>Status:
        <select name="txtstatus" required>
            <option value="active">Active</option>
            <option value="inactive">Inactive</option>
        </select>
    </p>
    <p><input type="submit" value="Submit"></p>
</form>

<% if (request.getAttribute("mess") != null) { %>
    <%= request.getAttribute("mess") %>
<% } %>

    </body>
</html>
