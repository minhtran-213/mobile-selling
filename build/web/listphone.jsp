<%-- 
    Document   : listphone
    Created on : Mar 16, 2021, 12:58:39 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dtos.Phone"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phone List</title>
        <style type="text/css">
            img {
                text-align: center;
                border: solid 1px black;
                padding: 5px;
                margin: 5px;
                float: left;
                display: inline-block;
            }
        </style>
    </head>
    <body>
        <h1>Phone List </h1>
        <c:url value="loadBrand" var="loadBrandLink"></c:url>
        <a href=${loadBrandLink}>Add new items</a> 
        <c:if test ="${requestScope.Phones!=null}">
            <c:if test = "${not empty requestScope.Phones}" var = "testEmpty">
                <table border="1">
                    <thead>
                        <tr>
                            <td>ID</td>
                            <td>Name</td>
                            <td>Description</td>
                            <td>Brand</td>
                            <td>Image</td>
                            <td>Price</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.Phones}" var="dto">
                        <form action="loadByID" method="POST">
                            <tr>
                                <td>${dto.id}</td>
                                <td>${dto.name}</td> 
                                <td>${dto.descript}</td>
                                <td>${dto.brand.name}</td> 
                                <td><img 
                                        src="images/${dto.imgURL}" width="120"/>${dto.imgURL}</td>
                                <td>${dto.price}</td> 
                                <td>
                                    <c:url value="delete" var="deleteLink">
                                        <c:param name="id" value ="${dto.id}"/>
                                    </c:url> 
                                    <a href="${deleteLink}" onclick="return confirmDelete()">Delete</a>
                                </td>
                                <td>
                                    <c:url value="loadPhoneID" var="loadPhoneIDLink">
                                        <c:param name="id" value ="${dto.id}"/>
                                    </c:url>
                                    <a href="${loadPhoneIDLink}">Update</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${!testEmpty}">
            <h2>No Computer here</h2>
        </c:if>
    </c:if>
    <c:url value="loadBrand" var="loadBrandLink"></c:url>
    <a href=${loadBrandLink}>Add new items</a> 
    <c:url value="logout" var="logoutLink"></c:url>
    <a href=${logoutLink}>Log Out</a> 
    <script>
        function confirmDelete() {
            var r = confirm("Are you sure you want to delete this?")
            return r;
        }
    </script>
</body>
</html>
