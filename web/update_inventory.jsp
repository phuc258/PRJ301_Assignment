<%-- 
    Document   : update_inventory
    Created on : Jul 10, 2025, 11:24:57 AM
    Author     : toila
--%>

<%@page import="dto.InventoryLog"%>
<%@page import="dao.BookDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dto.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <title>Quản lý tồn kho</title>
    <style>
        body { font-family: Arial; margin: 20px; }
        select, input, button { margin: 6px; padding: 6px; }
        table, th, td { border: 1px solid #ccc; border-collapse: collapse; padding: 8px; }
        th { background-color: #3498db; color: white; }
        .form-section { margin: 20px 0; }
        .btn { background-color: #2ecc71; color: white; border: none; padding: 6px 12px; cursor: pointer; }
        .btn:hover { background-color: #27ae60; }
    </style>
</head>
<body>

<h2>📦 Cập nhật tồn kho sách</h2>

<!-- Select Book -->
<form method="get" action="InventoryController">
    <label>Chọn sách:</label>
    <select name="bookId" onchange="this.form.submit()">
        <option value="">-- Chọn sách --</option>
        <%
            ArrayList<Book> books = (ArrayList<Book>) request.getAttribute("books");
            Book selectedBook = (Book) request.getAttribute("selectedBook");
            for (Book b : books) {
                boolean selected = (selectedBook != null && b.getId() == selectedBook.getId());
        %>
            <option value="<%= b.getId() %>" <%= selected ? "selected" : "" %>><%= b.getTitle() %></option>
        <% } %>
    </select>
</form>

<% if (selectedBook != null) { %>
<!-- Form update -->
<div class="form-section">
    <form method="post" action="InventoryController">
        <input type="hidden" name="bookId" value="<%= selectedBook.getId() %>" />
        <p><strong>Tiêu đề:</strong> <%= selectedBook.getTitle() %></p>
        <p><strong>Tiêu đề:</strong> <%= selectedBook.getTotal_copies()%></p>

        <label>So Luong Sach:</label>
        <input type="number" name="quantity" value="" required />

        <label>Ghi chú:</label>
        <input type="text" name="note" style="width: 300px;" />
        <br/>

        <button type="submit" class="btn" name="updateInventory" value="cong">💾 thêm hàng (+)</button>
        <button type="submit" class="btn" name="updateInventory" value="tru">💾 Xóa hàng (-)</button>
    </form>
</div>

<!-- Lịch sử cập nhật -->
<h3>📋 Lịch sử cập nhật tồn kho</h3>
<table>
    <tr>
        <th>Thời gian</th><th>Hành động</th><th>Quantity</th><th>Ghi chú</th>
    </tr>
    <%
        ArrayList<InventoryLog> logs = (ArrayList<InventoryLog>) request.getAttribute("logs");
        if (logs != null && !logs.isEmpty()) {
            for (InventoryLog log : logs) {
    %>
    <tr>
        <td><%= log.getTimestamp() %></td>
        <td><%= log.getAction() %></td>
        <td><%= log.getQuantity() %></td>
        <td><%= log.getNote() %></td>
    </tr>
    <% } } else { %>
    <tr><td colspan="6"><i>Không có lịch sử cập nhật tồn kho.</i></td></tr>
    <% } %>
</table>
<% } %>

</body>
</html>
