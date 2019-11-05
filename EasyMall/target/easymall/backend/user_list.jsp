<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户管理</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="${app}/css/backend/prod_list.css"/>

    <script src="${app}/js/jquery-1.4.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $(".del").click(function () {
                if (!window.confirm("您确定要删除该用户吗？")) {
                    return;
                }
                var id = $(this).parents("tr").find(".uid").attr("id");
                var _this = this;
                $.post("${app}/servlet/BackUserDelServlet", {"id": id}, function (result) {
                    if ("true" == result) {
                        alert("删除成功！");
                        $(_this).parents("tr").remove();
                    } else {
                        alert("删除失败！")
                    }
                });
            });

            $(".reset").click(function () {
                if (!window.confirm("您确定要重置该用户密码为123456吗？")) {
                    return;
                }
                var id = $(this).parents("tr").find(".uid").attr("id");
                var _this = this;
                $.post("${app}/servlet/BackUserResetPwdServlet", {"id": id}, function (result) {
                    if ("true" == result) {
                        alert("重置成功！");
                    } else {
                        alert("重置失败！")
                    }
                });
            })
        })
    </script>
</head>
<body>
<h2>用户管理</h2>
<table border="1">
    <tr>
        <th width="100px">用户ID</th>
        <th class="ths">用户名</th>
        <th class="ths">昵称</th>
        <th class="ths">邮件地址</th>
        <th class="ths">用户角色</th>
        <th width="150px" colspan="2">操 作</th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td class="uid" id="${user.id}">${user.id}</td>
            <td>${user.username}</td>
            <td>${ user.nickname }</td>
            <td>${ user.email }</td>
            <td>${ user.role }</td>
            <td><a class="reset" href="javascript:void(0)">重置密码</a></td>
            <td><a class="del" href="javascript:void(0)">删 除</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>