<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Overdue Books</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="adminDashboard" />
            <button class="btn">🏠 Về Dashboard</button>
        </form>
        <h2>Danh sách sách đã quá hạn</h2>
        <c:set var="USER" value="${sessionScope.USER}"/>
        <c:if test="${empty sessionScope.USER}">
            <jsp:forward page="index.jsp"/>
        </c:if>

        <table border="1" cellpadding="5" cellspacing="0">
            <thead>
                <tr>
                    <th>#</th><th>Tiêu đề</th><th>Người mượn</th>
                    <th>Ngày mượn</th><th>Ngày hẹn trả</th>
                    <th>Số ngày quá hạn</th>
                    <th>Trạng Thái</th>
                    <th>Tiền Phạt</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${OVERDUE_LIST}" varStatus="st">
                    <tr>
                        <td>${st.count}</td>
                        <td>${item.title}</td>
                        <td>${item.userName}</td>
                        <td>${item.borrowDate}</td>
                        <td>${item.dueDate}</td>
                        <td>${item.daysOverdue}</td>
                        <td>${item.status}</td>
                        <td>${item.tienPhat}</td>
                        <c:if test="${item.status eq 'overdue' or item.status eq 'borrowed'}">
                            <td><a href="MainController?action=ThanhToanTienPhat&id=${item.recordId}">Thanh Toan</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
