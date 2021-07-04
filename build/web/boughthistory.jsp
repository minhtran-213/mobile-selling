<%-- 
    Document   : boughthistory
    Created on : Mar 19, 2021, 8:05:08 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User history</title>
    </head>
    <body>
        <h1>Your Orders</h1>
    <c:set var="cart" value="${sessionScope.Order}"/>
    <c:if test="${not empty sessionScope.Order}" var = "testEmpty">
        <table border="1">
            <thead>
                <tr>
                    <th>Phone</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
            <c:set var="total" value="0"></c:set>
            <c:forEach var="entry" items="${sessionScope.Order}" varStatus="counter">
                <c:set var="total" value="${total + entry.phone.price * entry.quantity}"></c:set>
                <tr>
                    <td>${entry.getPhone().getName()}</td>
                    <td>${entry.getQuantity()}</td>
                    <td>${entry.getPhone().getPrice()*entry.getQuantity()}</td>
                </tr>
            </c:forEach>
            <tr>

            <c:url value="list" var="listLink"></c:url>
            <a href=${listLink}>Return to shopping</a> 

            <td></td>
            <td>
                Sub total: 
            </td>
            <td> ${total} </td>
            </tr>
            </tbody>
        </table>
    </c:if>
    <c:if test="${!testEmpty}">
        <h2>You haven't bought anything! Click on the link below to buy some phones</h2>
        <c:url value="list" var="listLink"></c:url>
        <a href=${listLink}>Return to shopping</a> 
    </c:if>
</body>
</html>
