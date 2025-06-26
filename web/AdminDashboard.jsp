<%-- 
    Document   : AdminDashboard
    Created on : May 31, 2025, 1:50:26 PM
    Author     : toila
--%>

<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*" %> 
<%@page import="dto.User" %>
<%@page import="dao.UserDAO" %>
<%@page import="dto.Book" %>
<%@page import="dao.BookDAO" %>
<!DOCTYPE html>
<html xmlns:h="http://java.sun.com/jsf/html" xmlns:f="http://java.sun.com/jsf/core">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                display: flex;
            }
            .sidebar {
                width: 220px;
                background-color: #2c3e50;
                color: white;
                height: 100vh;
                padding: 20px;
            }
            .sidebar h2 {
                font-size: 22px;
            }
            .sidebar a {
                color: white;
                text-decoration: none;
                display: block;
                margin: 15px 0;
            }
            .main {
                flex: 1;
                padding: 20px;
                background-color: #f4f4f4;
            }
            .header {
                font-size: 24px;
                margin-bottom: 20px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                background-color: white;
            }
            th, td {
                padding: 10px;
                border: 1px solid #ddd;
                text-align: left;
            }
            th {
                background-color: #3498db;
                color: white;
            }
            .btn {
                padding: 8px 12px;
                margin: 10px 0;
                background-color: #27ae60;
                color: white;
                border: none;
                cursor: pointer;
            }
            .btn:hover {
                background-color: #219150;
            }
        </style>
    </head>
     <body>
        <%
            User us = (User) session.getAttribute("USER");
            if (us == null) {
                response.sendRedirect("index.jsp");
            } else if (!us.getRole().equals("admin")) {
               response.sendRedirect("index.jsp");
            } else { %>


        <div class="sidebar">
            <h2>Admin Thư viện</h2>
            <a href="#">📚 Quản lý sách</a>
            <a href="QuanliUser.jsp">👤 Quản lý người dùng</a>
            <a href="#">📝 Đơn thuê</a>
            <a href="OverdueBooksController">📝 overdue </a>
            <a href="systemConfig.jsp">⚙️ Cài đặt</a>
            <a href="AdminStatisticsServlet">⚙️ statis</a>
            <a href="LogoutController">🔓 Đăng xuất</a>

        </div>

        <div class="main">
            <div class="header">📚 Danh sách Sách</div>
            <form>
                <h4> Name Want to find </h4>
                <input type="text" name="nameFindBook" value="${param.nameFindBook}" />
                <input type="submit" value="find" />
            </form>
            <button class="btn" onclick="location.href = 'AddNewBook.jsp'">➕ Thêm Sách Mới</button>

            <h4> ${requestScope.attributeMessage} </h4>

            <table>
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>ID</th>
                        <th>Tên sách</th>
                        <th>Tác giả</th>
                        <th>Thể loại</th>
                        <th>Số lượng</th>
                        <th>Số lượng tồn kho</th>
                        <th>Năm suất bản</th>
                        <th>Mã số</th>
                        <th>Thao tác</th>

                    </tr>
                </thead>
                <tbody>
                    <% BookDAO bookdao = new BookDAO();
                        String namefind = (String) request.getParameter("nameFindBook");
                        request.setAttribute("namefind", namefind);
                        if (namefind == null) {
                            namefind = "";
                        }
                        ArrayList<Book> list = bookdao.getListBookByName(namefind);
                        int count = 1;
                        for (Book b : list) {%>
                    <tr>

                        <td><%= count++%> </td>
                        <td><%= b.getId()%></td>
                        <td><%= b.getTitle()%></td>
                        <td><%= b.getAuthor()%></td>
                        <td><%= b.getCategory()%></td>
                        <td><%= b.getTotal_copies()%></td>
                        <td><%= b.getAvailable_copies()%></td>
                        <td><%= b.getPublished_year()%></td>
                        <td><%= b.getIsbn()%></td>


                        <td>
                            <form action="editBook.jsp">       
                                <input type="hidden" name="txtid" value="<%= b.getId()%>" />
                                <button class="btn">✏️ Sửa</button>
                            </form>



                            <form method="post" style="display:inline;" action="DeleteBook" onsubmit="return confirm('Bạn chắc chưa?')">
                                <input type="hidden" name="xoa_Name" value="<%= b.getTitle()%>" />
                                <input type="hidden" name="xoa_Year" value="<%= b.getPublished_year()%>" />
                                <%
                                 String buttonLabel = b.getStatus().equals("block") ? "Unlock" : "Lock";
                                 String buttonValue = b.getStatus().equals("block") ? "Unlock" : "Lock";
                                    %>
                                <button name="deleteBookButton" value="<%= buttonValue%>" class="btn" style="background-color: #e74c3c;" > <%= buttonLabel%></button>
                            </form>               
                        </td>
                    </tr>
                    <% }%>


                </tbody>
            </table>
        </div>
        <% }%>
    </body>
</html>
