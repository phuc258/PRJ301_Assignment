<%-- 
    Document   : AdminDashboard
    Created on : May 31, 2025, 1:50:26 PM
    Author     : toila
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*" %> 
<%@page import="dto.User" %>
<%@page import="dao.UserDAO" %>
<%@page import="dto.Book" %>
<%@page import="dao.BookDAO" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <c:set var="USER" value="${sessionScope.USER}"/>
        <c:if test="${empty sessionScope.USER}">
            <jsp:forward page="index.jsp"/>
        </c:if>


        <div class="sidebar">
            <h2>Admin Thư viện</h2>
            <a href="#">📚 Quản lý sách</a>
            <a href="InventoryController">📚 Inventory</a> <%-- dien gi vao day de su dung chuc nang update inventory --%>

            <form action="MainController" method="POST">
                <input type="hidden" name="action" value="quanliuser" />
                <input type="submit" value="👤 Quản lý người dùng" />

            </form>
            <a href="BorrowRequestController">📝 Đơn thuê</a>
            <a href="OverdueBooksController">📝 overdue </a>
            <form action="MainController" method="POST">
                <input type="hidden" name="action" value="setupconfig" />
                <input type="submit" value="⚙️ Cài đặt Config" />

            </form>
            <a href="AdminStatisticsServlet">⚙️ statis</a>
            <a href="LogoutController">🔓 Đăng xuất</a>

        </div>

        <div class="main">
            <div class="header">📚 Danh sách Sách</div>
            <form action="MainController" method="post">
                <h4> Name Want to find </h4>
                <input type="text" name="nameFindBook" value="${param.nameFindBook}" />
                <input type="hidden" name="action" value="findBook" />
                <input type="submit" value="find" />
            </form>
            <form action="MainController" method="post" style="display:inline;">
                <input type="hidden" name="action" value="addnewbook" />
                <button type="submit" class="btn">➕ Thêm Sách Mới</button>
            </form>

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
                    <c:set var="list" value="${requestScope.listBookBSearch}"/>
                    <c:if test="${ empty list}">
                        <jsp:forward page="SearchBookController"/>
                    </c:if>
                    <c:forEach var="b" items="${list}" varStatus="status">


                        <tr>

                            <td>${status.count}</td>
                            <td>${b.id}</td>
                            <td>${b.title}</td>
                            <td>${b.author}</td>
                            <td>${b.category}</td>
                            <td>${b.total_copies}</td>
                            <td>${b.available_copies}</td>
                            <td>${b.published_year}</td>
                            <td>${b.isbn}</td>



                            <td>
                                <form action="MainController" method="post">       
                                    <input type="hidden" name="txtid" value="${b.id}" />
                                    <input type="hidden" name="action" value="editBook" />
                                    <button class="btn">✏️ Sửa</button>
                                </form>



                                <form method="post" style="display:inline;" action="MainController" onsubmit="return confirm('Bạn chắc chưa?')">
                                    <input type="hidden" name="action" value="deletebook" />
                                    <input type="hidden" name="xoa_Name" value="${b.title}" />
                                    <input type="hidden" name="xoa_Year" value="${b.published_year}" />

                                    <c:set var="buttonLabel" value="${b.status eq 'block' ? 'Unlock' : 'Lock'}"/>
                                    <c:set var="buttonValue" value="${buttonLabel}"/>                                    
                                    <button name="deleteBookButton" value="${buttonValue}" class="btn" style="background-color: #e74c3c;" > ${buttonLabel}</button>
                                </form>               
                            </td>
                        </tr>
                    </c:forEach>


                </tbody>
            </table>
        </div>
    </body>
</html>
