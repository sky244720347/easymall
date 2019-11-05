<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tarena
  Date: 2019/1/15
  Time: 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${app}/css/head.css"/>
<meta http-equiv="Content-type" content="text/html" ; charset="UTF-8"/>

<script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
<script type="text/javascript">
    $(function () {
        $("#search_btn").click(function () {
            var search = $(this).prev("input").val();
            window.location.href = "${app}/servlet/ProdListBySearchServlet?search=" + search;
        });
    });
</script>

<div id="common_head">
    <div id="line1">
        <div id="content">
            <%--如果用户没有登录,就提示用户登录或注册--%>
            <c:if test="${ empty sessionScope.user}">
                <a href="${app}/login.jsp">您好，请登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                <a href="${app}/regist.jsp">注册</a>
            </c:if>
            <%--如果用户已经登录了，就提示欢迎xx回来--%>
            <c:if test="${ !(empty sessionScope.user) }">
                欢迎${ user.username }回来,&nbsp;
                <a href="${app}/servlet/LogoutServlet">退出</a>
            </c:if>

            <!-- 后台管理系统入口 -->
            <c:if test="${user.role eq 'admin'}">
                <a href="${app}/backend/manage.jsp">后台</a>
            </c:if>
        </div>
    </div>

    <div id="line2">
        <img id="logo" src="${app}/img/head/logo.jpg"/>
        <input type="text" name="search"/>
        <input id="search_btn" type="button" value="搜 索"/>
        <span id="goto">
            <%--我的订单的超链接--%>
            <a id="goto_order" href="${app}/servlet/OrderListServlet">我的订单</a>
            <%--我的购物车的超链接--%>
            <a id="goto_cart" href="${app}/cart.jsp">我的购物车</a>
        </span>
        <%--二维码图片--%>
        <img id="erwm" src="${app}/img/head/qr.jpg"/>
    </div>

    <div id="line3">
        <div id="content">
            <ul>
                <%--首页超链接--%>
                <li><a href="${app}/index.jsp">首页</a></li>
                <%--全部商品超链接--%>
                <li><a href="${app}/servlet/ProdListByConditionServlet">全部商品</a></li>
                <%--手机数码超链接--%>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=Phone">手机数码</a></li>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=PC">电脑平板</a></li>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=Home">家用电器</a></li>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=Car">汽车用品</a></li>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=Food">食品饮料</a></li>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=Book">图书杂志</a></li>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=Cloth">服装服饰</a></li>
                <li><a href="${app}/servlet/ProdListByConditionServlet?category=Money">手机充值</a></li>
            </ul>
        </div>
    </div>
</div>