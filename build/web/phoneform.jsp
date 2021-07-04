<%-- 
    Document   : phoneform
    Created on : Mar 16, 2021, 4:32:15 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Add Phone Form</h1>
        <form action="add" method="POST" enctype="multipart/form_data">
            <table>
                <tr>
                    <td>ID:</td>
                    <td><input type="text" name="txtID" value="${param.txtID}"/>
                        <font color="red">${requestScope.INVALID.idError}</font></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="txtName" value="${param.txtName}"/>
                        <font color="red">${requestScope.INVALID.nameError}</font></td>
                    
                </tr>
                <tr>
                    <td>Description:</td>
                    <td><input type="text" name="txtDesc" value="${param.txtDesc}"/>
                        <font color="red">${requestScope.INVALID.descriptError}</font></td>
                </tr>
                <tr>
                    <td>Brand:</td>
                    <td> 
                        <select name="txtBrand">
                            <c:forEach items="${requestScope.Brands}" var="dto">
                                <option value="${dto.id}">${dto.id}-${dto.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Image:</td>
                    <td><input type="file" name="txtPhoto" value=""/>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><input type="number" name="txtPrice" value="${param.txtPrice}"/>
                        <font color="red">${requestScope.INVALID.priceError}</font></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="add"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
