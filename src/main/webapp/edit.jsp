<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/4/2020
  Time: 2:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Management Application</title>
</head>
<body>
<center>
    <h1>Product Management</h1>
    <h2>
        <a href="products?action=users">List All Product</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    Edit Product
                </h2>
            </caption>
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="<c:out value='${product.getId()}' />"/>
            </c:if>
            <tr>
                <th>Name product:</th>
                <td>
                    <input type="text" name="name" size="45"
                           value="<c:out value='${product.getName()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Price Product:</th>
                <td>
                    <input type="text" name="price" size="45"
                           value="<c:out value='${product.getPrice()}' />"
                    />
                </td>
            </tr>
           <tr>
               <th>Amount Product:</th>
               <td>
               <input type="text" name="amount" size="45"\
               value="<c:out value='${product.getAmount()}' />">
               </td>
           </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
