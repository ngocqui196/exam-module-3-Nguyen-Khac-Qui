<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11/12/2020
  Time: 9:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/products?action=">List All Products</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Hãy nhập thông tin bạn muốn thêm vào đây</h2>
            </caption>
            <tr>
                <th>Tên sản phẩm:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Giá sản phẩm:</th>
                <td>
                    <input type="text" name="price" id="price" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Số lượng sản phẩm:</th>
                <td>
                    <input type="text" name="amount" id="amount" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Màu sản phẩm:</th>
                <td>
                    <input type="text" name="color" id="color" size="15"/>
                </td>
            </tr>
            <tr>
                <th>Mô tả sản phẩm:</th>
                <td>
                    <input type="text" name="detail" id="detail" size="15"/>
                </td>
            </tr>
            <tr>
                <th>Loại sản phẩm:</th>
                <td>
                    <input type="text" name="id_category" id="id_category" size="15"/>
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
