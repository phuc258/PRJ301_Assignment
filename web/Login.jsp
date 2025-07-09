<%-- 
    Document   : Login
    Created on : May 31, 2025, 1:41:40 PM
    Author     : toila
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="loginController"/>
            <p><input type="text" name="txtemail"/>*</p>
            <p><input type="password" name="txtpassword"/>*</p>
            <p><input type="submit" value="Login"/></p>
        </form>   
        <%
            if (request.getAttribute("error") != null){
                out.print(request.getAttribute("error"));
            }
        %>
    </body>
</html>
