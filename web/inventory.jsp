<%-- 
    Document   : inventory
    Created on : Jul 10, 2025, 6:28:47 PM
    Author     : toila
--%>

<%@page import="dto.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.InventoryLog"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="UTF-8">
    <title>Inventory Management</title>
    <style>
        table, th, td { border: 1px solid black; border-collapse: collapse; padding: 6px; }
        th { background-color: #f2f2f2; }
        .success { color: green; font-weight: bold; }
    </style>
</head>
<body>

<%
    Book book = (Book) request.getAttribute("book");
    ArrayList<InventoryLog> logs = (ArrayList<InventoryLog>) request.getAttribute("logs");
    String success = request.getParameter("success");
%>

<h2>Inventory Management: <%= book.getTitle() %></h2>

<% if ("1".equals(success)) { %>
    <p class="success">✅ Cập nhật thành công!</p>
<% } %>

<form action="InventoryController" method="post">
    <input type="hidden" name="bookId" value="<%= book.getId() %>" />
    <p>
        Total Copies:
        <input type="number" name="newTotal" value="<%= book.getTotalCopies() %>" required />
    </p>
    <p>
        Available Copies:
        <input type="number" name="newAvailable" value="<%= book.getAvailableCopies() %>" required />
    </p>
    <p>
        Note (optional):
        <input type="text" name="note" style="width: 300px;" />
    </p>
    <input type="submit" value="Update Inventory" />
</form>

<hr/>

<h3>📋 Inventory Update History</h3>
<% if (logs != null && !logs.isEmpty()) { %>
    <table>
        <tr>
            <th>Time</th>
            <th>Admin</th>
            <th>Action</th>
            <th>Old Qty</th>
            <th>New Qty</th>
            <th>Note</th>
        </tr>
        <% for (InventoryLog log : logs) { %>
            <tr>
                <td><%= log.getTimestamp() %></td>
                <td><%= log.getAdminName() %></td>
                <td><%= log.getAction() %></td>
                <td><%= log.getOldQuantity() %></td>
                <td><%= log.getNewQuantity() %></td>
                <td><%= log.getNote() %></td>
            </tr>
        <% } %>
    </table>
<% } else { %>
    <p><i>Chưa có lịch sử chỉnh sửa tồn kho.</i></p>
<% } %>

</body>
</html>
