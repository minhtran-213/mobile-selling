<%-- 
    Document   : ListProducts
    Created on : Mar 5, 2021, 11:02:11 AM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MNQ's Phones</title>
        <style type="text/css">
            section{    
            }

            #searching {
                width: 450px;
                padding: 5px;
                margin: 8px;
                border-radius: 25px;
                text-align: center;
            }
            .img {
                text-align: center;
                border: solid 1px black;
                padding: 5px;
                margin: 5px;
                height: 250px;
                width: 200px;
                float: left;
            }
            div .img{
                display: inline-block;
            }

            li .dropdown{
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px rgba(0,0,0,0.2);
                z-index: 1;
            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }

            li {
                float: left;
            }

            li a, .dropbtn {
                display: inline-block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover, .dropdown:hover .dropbtn {
                background-color: red;
            }

            li.dropdown {
                display: inline-block;
            }
            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                text-align: left;
            }

            .dropdown-content a:hover {background-color: #f1f1f1;}

            .dropdown:hover .dropdown-content {
                display: block;
            }
            #searchbtn {
                padding: 5px;
                margin: 8px;
            }
        </style>
    </head>
    <body>
        <div class = "menu-area">
            <ul>
                <li><a href="homePageCust.jsp">Home</a></li>
                <li class="dropdown">
                    <a href="javascript:void(0)" class="dropbtn">Brand</a>
                    <div class="dropdown-content">
                        <c:forEach var="cat" items="${sessionScope.catList}" varStatus="counter">
                            <a href="ListByBrand?action=brand&bid=${cat.id}">${cat.name}</a>
                        </c:forEach>
                    </div>
                </li>
                <form action="Search" method="POST">
                    <li>
                        <input type="text" name="searching" placeholder="what are you looking for" id="searching"/>
                    </li>
                    <li>
                        <input type="submit" value="SEARCH" id="searchbtn"/>
                    </li>
                </form>

            </ul>
        </div>
        <section>
            
            <c:forEach var="phone" items="${sessionScope.phoneList}">
                <form action="loadID" method="POST">
                    <div class="img">

                        ${phone.name}<br/>
                        <input type="hidden" name="PhoneName" value="${phone.name}" />
                        <img src="${pageContext.request.contextPath}/images/${phone.imgURL}" width="150" height="150" /><br/>
                        <input type="hidden" name="PhoneImg" value="${phone.imgURL}" />
                        ${phone.price}<br/>
                        <input type="hidden" name="PhonePrice" value="${phone.price}" />
                        <c:url value="loadID" var="loadIDLink">
                            <c:param name="id" value ="${phone.id}"/>
                        </c:url>
                        <a href="${loadIDLink}">buy</a>
                    </div>
                </form>
            </c:forEach>
            <c:url value="cart" var="cartLink"></c:url>
            <a href=${cartLink}>CART</a> 
            <br>
            <c:url value="history" var="historyLink"></c:url>
            <a href=${historyLink}>BUY HISTORY</a> 
            <h2>Welcome ${sessionScope.USER.fullname}</h2>
        </section>
    </body>
</html>
