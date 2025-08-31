<%-- 
    Document   : update_inventory
    Created on : Jul 10, 2025, 11:24:57 AM
    Author     : toila
--%>

<%@page import="dao.InventoryLogDAO"%>
<%@page import="dto.InventoryLog"%>
<%@page import="dao.BookDAO"%>
<%@page import="dto.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="dto.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Quản lý tồn kho</title>        
        <link rel="stylesheet" href="./style/inventory.css"/>
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
                <option value="<%= b.getId()%>" <%= selected ? "selected" : ""%>><%= b.getTitle()%></option>
                <% } %>
            </select>
        </form>

        <% if (selectedBook != null) {%>
        <!-- Form update -->
        <div class="form-section">
            <form method="post" action="MainController">
                <input type="text" name="action" value="InventoryController" />
                <input type="hidden" name="bookId" value="<%= selectedBook.getId()%>" />
                <p><strong>Tiêu đề:</strong> <%= selectedBook.getTitle()%></p>
                <p><strong>tổng sách :</strong> <%= selectedBook.getTotal_copies()%></p>
                <p>${requestScope.MESS}</p>
                <label>Số lượng sách:</label>
                <input type="number" min="1"  name="quantity" value="" required
                       oninvalid="this.setCustomValidity('Vui lòng nhập số nguyên lớn hơn 0')" 
                       oninput="this.setCustomValidity('')" />

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
                Book b_e = (Book) request.getAttribute("selectedBook");
                if (logs == null) {
                    InventoryLogDAO ld = new InventoryLogDAO();
                    logs = ld.getLogsByBookId(b_e.getId());
                }
                if (logs != null && !logs.isEmpty()) {
                    for (InventoryLog log : logs) {
            %>
            <tr>
                <td><%= log.getTimestamp()%></td>
                <td><%= log.getAction()%></td>
                <td><%= log.getQuantity()%></td>
                <td><%= log.getNote()%></td>
            </tr>
            <% }
            } else { %>
            <tr><td colspan="6"><i>Không có lịch sử cập nhật tồn kho.</i></td></tr>
            <% } %>
        </table>
        <% }%>

    </body>
</html>
