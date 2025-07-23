<%@page import="dto.User"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.SystemConfigDAO" %>
<%@ page import="dto.Config" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    SystemConfigDAO dao = new SystemConfigDAO();
    ArrayList<Config> configList = dao.getAllConfig();
%>
<html>
    <head>
        <title>Cấu hình hệ thống</title>
    </head>
    <body> 
        <c:set var="USER" value="${sessionScope.USER}"/>
        <c:if test="${empty sessionScope.USER}">
            <jsp:forward page="index.jsp"/>
        </c:if>
        <c:set var="configList" value="${requestScope.configList}"/>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="adminDashboard" />
            <button class="btn">🏠 Về Dashboard</button>
        </form>        <h2>Cấu hình hệ thống</h2>
        <form action="UpdateConfigController" method="post">
            <table border="1">
                <tr>
                    <th>Tham số</th>
                    <th>Giá trị</th>
                    <th>Mô tả</th>
                </tr>
                <c:if test="${empty configList}">
                    <jsp:forward page="UpdateConfigController"/>
                </c:if>
                <c:forEach var="c" items="${configList}">
                    <tr>
                        <td>${c.config_key}</td>
                        <td>
                            <c:choose>
                                <c:when test="${c.config_key == 'overdue_fine_per_day' 
                                                or c.config_key == 'unit_price_per_book'}">
                                        <input
                                            type="number"
                                            name="${c.config_key}"
                                            value="${c.config_value}"
                                            step="0.01"
                                            min="0"
                                            required
                                            />
                                </c:when>

                                <c:when test="${c.config_key == 'default_borrow_duration_days'}">
                                    <input
                                        type="number"
                                        name="${c.config_key}"
                                        value="${c.config_value}"
                                        step="1"
                                        min="1"
                                        required
                                        />
                                </c:when>

                                <c:otherwise>
                                    <input
                                        type="text"
                                        name="${c.config_key}"
                                        value="${c.config_value}"
                                        />
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${c.description}</td>
                    </tr>
                </c:forEach>

            </table>
            <br/>
            <input type="submit" value="Cập nhật cấu hình"/>
        </form>
    </body>
</html>
