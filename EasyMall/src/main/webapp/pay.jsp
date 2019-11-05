<%@ page language="java" isELIgnored="false" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="${app}/css/pay.css" rel="stylesheet" type="text/css">
    <script src="${app}/js/jquery-1.4.2.js"></script>
    <script>
        function check() {
            var $checkbox = $("input[name='pd_FrpId']");
            var flag = false;
            $checkbox.each(function () {
                if ($(this).is(':checked')) {
                    flag = true;
                }
            })
            if (!flag) {
                alert("请选择支付银行！");
            } else {
                if (${empty param.id} ||
                    ${empty param.money }) {
                    alert("无订单信息，请重新选择订单！");
                    $("form").attr("action", "${app}/order_list.jsp");
                }
            }
            return flag;
        }
    </script>
    <title>支付页面-EasyMall</title>
</head>
<body>
<%@include file="/_head.jsp" %>
<form action="${app}/servlet/PayServlet" method="post" onsubmit="return check()">
    <dl class="payment_page">
        <dt>
            <strong>订单号：</strong>
            <input class="idinp" name="orderid" readonly="readonly" type="text" value="${param.id}">
            <strong>支付金额：</strong>
            <input class="moneyinp" name="" readonly="readonly" type="text" value="${param.money}">元
        </dt>
        <dd class="payment_page_name">
            <strong>请您选择在线支付银行：</strong>
        </dd>
        <dd class="banks">
            <ul>
                <li>
                    <input name="pd_FrpId" type="radio" value="ICBC-NET-B2C">
                    <img src="${app}/img/pay/01gs.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="CMBCHINA-NET-B2C">
                    <img src="${app}/img/pay/02zs.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="CCB-NET-B2C">
                    <img src="${app}/img/pay/03js.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/04ny.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/05zg.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="BOCO-NET-B2C">
                    <img src="${app}/img/pay/06jt.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/07hx.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/08xy.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/09gd.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/10sz.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/11ms.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/12sh.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/13zx.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/14gd.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/15cq.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/16bh.jpg" width="130" height="52">
                </li>
                <li>
                    <input name="pd_FrpId" type="radio" value="">
                    <img src="${app}/img/pay/17yb.jpg" width="130" height="52">
                </li>
            </ul>
        </dd>
        <div style="clear:both;"></div>
        <dd class="ok_dd">
            <input type="submit" class="ok_pay" value="确认支付">
        </dd>
    </dl>
</form>
<%@include file="/_foot.jsp" %>
</body>
</html>