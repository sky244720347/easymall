<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>EasyMall后台管理页面</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="${app}/css/backend/_left.css"/>
</head>
<frameset rows="13%, 87%" frameborder="0" framespacing="0">
    <frame src="${app}/backend/_top.jsp" noresize="noresize"/>
    <frameset cols="14%, 86%" frameborder="0" framespacing="0">
        <frame src="${app}/backend/_left.jsp" noresize="noresize"/>
        <frame src="${app}/backend/_right.jsp" name="rightFrame" noresize="noresize"/>
    </frameset>
</frameset>
<body>
</body>
</html>