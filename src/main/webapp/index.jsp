<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/12/2020
  Time: 8:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Product management</title>
  </head>
  <body>
  <center>
    <h1>Product Management</h1>
    <table>
      <tr>
        <td><a href="/products?action=create">Thêm mới sản phẩm</a></td>
        <td><a href="/products?action=categoriList">Danh sách loại sản phẩm</a></td>
      </tr>
    </table>
  </center>
  <div align="center">
    <table border="1" cellpadding="3">
      <caption><h2>Danh sách các sản phẩm</h2></caption>
      <tr>
        <th>ID</th>
        <th>Tên sản phẩm</th>
        <th>Giá</th>
        <th>Lượng</th>
        <th>Màu</th>
        <th>Loại</th>
      </tr>
<c:forEach var="product" items="${listProducts}">
      <tr>
        <td><c:out value="${product.getId()}"/></td>
        <td><c:out value="${product.getName()}"/></td>
        <td><c:out value="${product.getPrice()}"/></td>
        <td><c:out value="${product.getAmount()}"/></td>
        <td><c:out value="${product.getColor()}"/></td>
        <td><c:out value="${product.getCategory().getNameCategory()}"/></td>
        <td>
          <a href="/products?action=edit&id=${product.id}">Edit</a>
          <a href="/products?action=delete&id=${product.id}">Delete</a>
        </td>
      </tr>
  </c:forEach>
    </table>
  </div>
  </body>
</html>
