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
        <form action="LoginController" method="post">
            <p><input type="text" name="txtemail"/>*</p>
            <p><input type="password" name="txtpassword"/>*</p>
            <p><input type="submit" value="login"/></p>
        </form>   
        <%
            if (request.getAttribute("error") != null){
                out.print(request.getAttribute("error"));
            }
        %>
    </body>
</html>
