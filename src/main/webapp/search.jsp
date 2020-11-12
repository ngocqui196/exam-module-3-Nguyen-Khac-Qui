
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/4/2020
  Time: 11:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Seach User</title>
</head>
<body>
<center>
    <h1>User Management</h1>

</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption>
            <h2>
                Show List of Users : <c:out value="${product.getName()}"/>
            </h2>
        </caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Color</th>
            <th>Detail</th>
            <th>Category</th>
        </tr>
        <c:forEach var="product" items="${listProduct}">
            <tr>
                <td><c:out value="${product.id}"/></td>
                <td><c:out value="${product.getName()}"/></td>
                <td><c:out value="${product.getPrice()}"/></td>
                <td><c:out value="${product.getAmount()}"/></td>
                <td><c:out value="${product.getColor()}"/></td>
                <td><c:out value="${product.getDetail()}"/></td>
                <td><c:out value="${product.getCategory().getIdCategory()}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
