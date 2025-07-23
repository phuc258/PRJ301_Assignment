<%-- 
    Document   : editBook
    Created on : Jun 11, 2025, 10:40:12 AM
    Author     : toila
--%>

<%@page import="dao.BookDAO"%>
<%@page import="java.lang.String"%>
<%@page import="dto.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int Id = Integer.parseInt(request.getParameter("txtid"));
            Book bookEdit = (new BookDAO()).getBookById(Id);
            if (bookEdit == null) {
                request.getRequestDispatcher("AdminDashboard.jsp").forward(request, response);
            } else {%>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="adminDashboard" />
            <button class="btn">🏠 Về Dashboard</button>
        </form>
        <form action="MainController" style=" padding: 5%" method="post" accept-charset="utf-8">
            <input type="hidden" name="action" value="editBookController" />
            <input type="hidden" name="txtid" value="<%= bookEdit.getId()%>" />
            <p>title<input type="text" name="txttitle" value="<%= bookEdit.getTitle()%>" ></p>
            <p>author<input type="text" name="txtauthor" value="<%= bookEdit.getAuthor()%>"></p>
            <p>category<input type="text" name="txtcategory" value="<%= bookEdit.getCategory()%>"></p>
            <p>isbn<input type="text" name="txtisbn" value="<%= bookEdit.getIsbn()%>"></p>
            <input type="hidden" name="txtstatus" value="<%= bookEdit.getStatus()%>" />
            <input type="hidden" name="txtpublished_year" value="<%= bookEdit.getPublished_year()%>" />
            <input type="hidden" name="txttotal_copies" value="<%= bookEdit.getTotal_copies()%>" />

            <p><input type="submit" name="btn" value="submit"></p>
        </form>
        ${requestScope.message}
        <%}%>
    </body>
</html>
