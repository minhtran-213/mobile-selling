<%-- 
    Document   : homePage
    Created on : Mar 16, 2021, 2:28:05 PM
    Author     : takah
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <h1>MNG's phone shop</h1>
        <h2>Welcome ${sessionScope.USER.fullname}</h2>
        <ul>
            <li><a href="logout">Logout</a></li>
            <li><a href="adminList">Show list of phones</a></li>
        </ul>
    </body>
</html>
