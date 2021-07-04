<%-- 
    Document   : cartView
    Created on : Mar 16, 2021, 8:32:43 PM
    Author     : takah
--%>

<%@page import="java.util.Map"%>
<%@page import="dtos.Cart"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your cart</title>
    </head>
    <body>
        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty sessionScope.CART}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Title</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:set var="total" value="0"></c:set>
                    <c:forEach var="entry" items="${sessionScope.CART}" varStatus="counter">
                        <c:set var="total" value="${total + entry.phone.price * entry.quantity}"></c:set>
                            <tr>
                                <td>${counter.count}</td>
                            <td>${entry.getPhone().getName()}</td>
                            <td>${entry.getQuantity()}</td>
                            <td>${entry.getPhone().getPrice()*entry.getQuantity()}</td>
                            <td>
                                <c:url value="deleteCart" var="deleteCartLink">
                                    <c:param name="id" value ="${entry.getPhone().getId()}"/>
                                </c:url>
                                <a href=${deleteCartLink}>remove</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <c:url value="list" var="listLink"></c:url>
                <a href=${listLink}>Return to shopping</a> 
                <td></td>
                <td></td>
                <td>
                    Sub total: 
                </td>
                <td> ${total} </td>
            </tr>
        </tbody>
    </table>
    <c:url value="checkOut" var="checkOutLink"></c:url>
    <a href=${checkOutLink}>Check out</a> 
</c:if>
<c:if test="${empty cart}">
    <h2>No Phones has been bought</h2>
    <c:url value="list" var="listLink"></c:url>
    <a href=${listLink}>Return to shopping</a> 
</c:if>
</body>
</html>
