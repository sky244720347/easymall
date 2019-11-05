<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>商品管理</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="${app}/css/backend/prod_list.css"/>

    <script src="${app}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".pnum").blur(function () {
                var oldPnum = $(this).attr("oldPnum");
                var newPnum = $(this).val();
                var pid = $(this).attr("id");
                if (oldPnum == newPnum) {
                    return;
                }

                var reg = /^0$|^[1-9][0-9]*$/;
                if (!reg.test(newPnum)) {
                    alert("您输入的数值不合法！");
                    $(this).val(oldPnum);
                    return;
                }

                $.post("${app}/servlet/BackProdPnumUpdateServlet", {"pid": pid, "pnum": newPnum}, function (result) {
                    if ("true" == result) {
                        alert("修改成功！");
                        $(this).attr("oldPnum", newPnum);
                    } else {
                        alert("修改失败！")
                    }
                });
            });
            $(".del").click(function () {
                if (!window.confirm("您确定要删除该商品吗？")) {
                    return;
                }
                var pid = $(this).parents("tr").find(".pnum").attr("id");
                var _this = this;
                $.post("${app}/servlet/BackProdDelServlet", {"pid": pid}, function (result) {
                    if ("true" == result) {
                        alert("删除成功！");
                        $(_this).parents("tr").remove();
                    } else {
                        alert("删除失败！")
                    }
                });
            })
        })
    </script>
</head>
<body>
<h2>商品管理</h2>
<table border="1">
    <tr>
        <th>商品图片</th>
        <th width="100px">商品ID</th>
        <th class="ths">商品名称</th>
        <th class="ths">商品种类</th>
        <th class="ths">商品单价</th>
        <th class="ths">库存数量</th>
        <th>描述信息</th>
        <th width="50px">操 作</th>
    </tr>
    <c:forEach items="${productList}" var="prod">
        <tr>
            <td><img width="120px" height="120px" src="${app}/servlet/ProdImgServlet?imgurl=${prod.imgurl}" alt=""></td>
            <td>${prod.id}</td>
            <td>${prod.name}</td>
            <td>${ prod.category }</td>
            <td>${ prod.price }</td>
            <td><input type="text" id="${prod.id}" class="pnum" oldPnum="${prod.pnum}" value="${ prod.pnum }"/></td>
            <td>${ prod.description }</td>
            <td><a class="del" href="javascript:void(0)">删 除</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>