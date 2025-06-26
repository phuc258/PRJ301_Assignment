<%-- 
    Document   : QuanliUser
    Created on : Jun 2, 2025, 4:49:22 PM
    Author     : toila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*" %> 
<%@page import="dto.User" %>
<%@page import="dao.UserDAO" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
User us = (User)session.getAttribute("USER");
if(us==null){
response.sendRedirect("index.jsp");
}else{
        %>
        <a href="AdminDashboard.jsp">Home</a>
        <form method="get">
            <input type="text" name="findEmail" value="" />
            <input type="submit" value="submit" />
        </form>
        <%     
            String findEmail = "";
            if (request.getParameter("findEmail") != null)
             findEmail = request.getParameter("findEmail");
            UserDAO dao = new UserDAO();
            ArrayList<User> list = dao.findUsersByEmail(findEmail);
            
        %>  
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>name</th>
                    <th>email</th>
                    <th>password</th>
                    <th>role</th>
                    <th>status</th>
                </tr>
            </thead>
            <tbody>
                <% for (User u : list) { %>
                <tr>
                    <td><%= u.getId()%></td>
                    <td><%= u.getName()%></td>
                    <td><%= u.getEmail()%></td>
                    <td><%= u.getPassword()%></td>
                    <td><%= u.getRole()%></td>
                    <td><%= u.getStatus()%></td>
                    <td>  
                        <form action="ManagerUsers">
                            <input type="hidden" name="emailUser" value="<%= u.getEmail() %>"/>
                            <button class="btn" value="xoa" name="yeucau" style="background-color: #e74c3c;">🗑️ Xóa</button>
                            <%
                                String buttonLabel = u.getStatus().equalsIgnoreCase("blocked") ? "UnBan" : "Ban";
                                String buttonValue = u.getStatus().equalsIgnoreCase("blocked") ? "moKhoa" : "ban";
                            %>
                            <button class="btn" value="<%= buttonValue %>" name="yeucau"><%= buttonLabel %></button>

                        </form>

                    </td>
                </tr>
                <%} %>        
            </tbody>
        </table>
                <%} %>
    </body>
</html>
