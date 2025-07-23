<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Library Statistics</title>
</head>
<body>
    <h1>Library Statistics</h1>

    <h3>Total Books: ${totalBooks}</h3>
    <h3>Total Users: ${totalUsers}</h3>
    <h3>Currently Borrowed Books: ${borrowedBooks}</h3>
    <h3>Average Borrow Duration: ${avgDuration} days</h3>

    <hr/>

    <h2>Top 5 Most Borrowed Books</h2>
    <table border="1">
        <tr>
            <th>Title</th>
            <th>Times Borrowed</th>
        </tr>
        <c:forEach var="book" items="${mostBorrowedBooks}">
            <tr>
                <td>${book.title}</td>
                <td>${book.count}</td>
            </tr>
        </c:forEach>
    </table>

    <hr/>

    <h2>Monthly Borrowing Statistics</h2>
    <table border="1">
        <tr>
            <th>Month</th>
            <th>Borrow Count</th>
        </tr>
        <c:forEach var="m" items="${monthlyStats}">
            <tr>
                <td>${m.month}</td>
                <td>${m.count}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
