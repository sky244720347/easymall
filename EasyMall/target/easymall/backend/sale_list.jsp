<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>销售榜单</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="${app}/css/backend/sale_list.css"/>
</head>
<body>
<h1>销售榜单</h1>
<a href="${app}/servlet/SalesDownloadServlet">销售榜单下载</a>
<hr/>
<table bordercolor="black" border="1" width="95%" cellspacing="0px" cellpadding="5px">
    <tr>
        <th>商品ID</th>
        <th>商品名称</th>
        <th>商品总量</th>
    </tr>
    <c:forEach items="${requestScope.saleList}" var="prod">
        <tr>
            <td>${prod.prod_id}</td>
            <td>${prod.prod_name}</td>
            <td>${prod.sale_num}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>