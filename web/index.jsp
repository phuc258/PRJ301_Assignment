<%-- 
    Document   : index
    Created on : May 31, 2025, 1:42:14 PM
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
       <div style="background:#999661; height:50px;padding: 20px; " >
    <a href="index.html">home</a>|<a href="Login.jsp">Login</a>
    <form action="SearchBookController" style="float:right" method="get">
        <input type="text" name="txtsearch" value="${param.msg}" />
            <input type="submit" value="find"/>
    </form>
    </div>    
    <div style="float:left; width: 70%;">coming soon</div>    
    <div style="float:left; width: 30%; background: #ffffcc;">    
        <form action="RegisterController" style=" padding: 5%" method="post">
            <p>name:<input type="text" name="txtname" required="">*</p>
            <p>email<input type="text" name="txtemail" >*</p>
            <p>password:<input type="password" name="txtpassword" required="">*</p>
            <p>confirm password:<input type="password" name="txtconfirmpassword" required="">*</p>
            <p><input type="submit" name="btn" value="submit"></p>
        </form>
        <a href="FindBook.jsp">Find Book</a>
      </div>   
    </body>
</html>
