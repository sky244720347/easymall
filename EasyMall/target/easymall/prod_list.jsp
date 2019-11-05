<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="${app}/css/prodList.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@include file="_head.jsp"%><!-- 包含头部 -->
<div id="content">
    <div id="search_div"><!-- 查询功能 -->
        <form method="post" action="${app}/servlet/ProdListByConditionServlet">
            <span class="input_span">商品名：<input type="text" name="name" value="${param.name}"/></span>
            <span class="input_span">商品种类：
            <select name="category" class="input_span">
                <option name="category" value="" ${param.category==""?'selected':''}>所有商品</option>
                <option name="category" value="Phone"${param.category=="Phone"?'selected':''}>手机数码</option>
                <option name="category" value="PC"${param.category=="PC"?'selected':''}>电脑平板</option>
                <option name="category" value="Home"${param.category=="Home"?'selected':''}>家用电器</option>
                <option name="category" value="Car"${param.category=="Car"?'selected':''}>汽车用品</option>
                <option name="category" value="Food"${param.category=="Food"?'selected':''}>食品饮料</option>
                <option name="category" value="Book"${param.category=="Book"?'selected':''}>图书杂志</option>
                <option name="category" value="Cloth"${param.category=="Cloth"?'selected':''}>服装服饰</option>
                <option name="category" value="Money"${param.category=="Money"?'selected':''}>手机充值</option>
            </select>

            </span>
            <span class="input_span">价格区间：
                    <input type="text" name="minprice" value="${param.minprice}"/>
                    <input type="text" name="maxprice" value="${param.maxprice}"/>
                    <input type="submit" value="查 询"/>
                <a href="${app}/servlet/ProdListByConditionServlet" class="replacement">
                    <input type="button" value="重 置" id="reset"/>
                </a>

                </span>
        </form>
    </div>

    <div id="prod_content">

        <c:forEach items="${list}" var="prod">
            <div class="prod_div">
                <!--跳转商品详情-->
                <a href="${app}/servlet/ProdInfoServlet?pid=${prod.id}">
                    <img src="${app}/servlet/ProdImgServlet?imgurl=${prod.imgurl}">
                </a>
                <!--跳转商品详情-->
                <div id="prod_name_div">
                    <a href="${app}/servlet/ProdInfoServlet?pid=${prod.id}"><!-- 点击跳转商品 -->
                            ${prod.name}
                    </a>
                </div>

                <div id="prod_price_div">
                    <a>
                            ${prod.price}
                    </a>
                </div>

                <div>
                    <!--跳转购物车-->
                    <div id="gotocart_div">
                        <a href="${app}/servlet/CartUpdateServlet?pid=${prod.id}&buyNum=1">
                            加入购物车
                        </a>
                    </div>

                    <div id="say_div">
                        <a>
                            XX人评价
                        </a>
                    </div>

                </div>
            </div>
        </c:forEach>
        <div style="clear: both"></div>
    </div>
</div>
<%@include file="_foot.jsp"%><!-- 包含尾部 -->
</body>
</html>
