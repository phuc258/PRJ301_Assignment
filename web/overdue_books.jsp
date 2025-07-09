<%-- 
    Document   : overdue_books
    Created on : Jul 9, 2025, 12:40:49 PM
    Author     : toila
--%>

<%@page import="dto.OverdueRecord"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
  <h2>Overdue Books</h2>
<table border="1">
 <tr>
    <th>User</th><th>Book</th><th>Borrow Date</th><th>Due Date</th><th>Days Overdue</th><th>Fine ($)</th>
</tr>
<%
    ArrayList<OverdueRecord> list = (ArrayList<OverdueRecord>) request.getAttribute("overdues");
    if (list != null && !list.isEmpty()) {
        for (OverdueRecord r : list) {
%>
<tr>
    <td><%= r.getUserName() %></td>
    <td><%= r.getBookTitle() %></td>
    <td><%= r.getBorrowDate() %></td>
    <td><%= r.getDueDate() %></td>
    <td><%= r.getDaysOverdue() %></td>
    <td><%= String.format("%.2f", r.getFineAmount()) %></td>
</tr>
<%
        }
    } else {
%>
<tr><td colspan="6"><i>No overdue records found.</i></td></tr>
<%
    }
%>

</table>
    </body>
</html>
