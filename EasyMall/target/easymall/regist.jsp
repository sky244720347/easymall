<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<head>
    <title>欢迎注册EasyMall</title>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="${app}/css/regist.css"/>
    <script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
    <script>
        $(function () {
            $("#img").click(function () {
                $(this).attr("src", "${app}/servlet/ValiImageServlet?time=" + new Date().getTime());
            });
            $("input[name='username']").blur(function () {
                if (!formObj.checkNull("username", "用户名不能为空！")) {
                    return;
                }
                var username = $(this).val();
                $.post("${app}/servlet/AjaxCheckUsernameServlet", {"username": username}, function (result) {
                    $("#username_msg").html(result);
                });
            });
            $("input[name='password']").blur(function () {
                formObj.checkNull("password", "密码不能为空！");
                formObj.checkPassword("password", "两次密码不一致！");
            });
            $("input[name='password2']").blur(function () {
                formObj.checkNull("password2", "确认密码不能为空！");
                formObj.checkPassword("password", "两次密码不一致！");
            });
            $("input[name='nickname']").blur(function () {
                formObj.checkNull("nickname", "昵称不能为空！");
            });
            $("input[name='email']").blur(function () {
                formObj.checkNull("email", "邮箱不能为空！");
                formObj.checkEmail("email", "请输入正确的邮箱格式！");
            });
            $("input[name='valistr']").blur(function () {
                formObj.checkNull("valistr", "验证码不能为空！");
            });
        })
        var formObj = {
            "checkForm": function () {
                //    非空校验
                var res1 = this.checkNull("username", "用户名不能为空！");
                var res2 = this.checkNull("password", "密码不能为空！");
                var res3 = this.checkNull("password2", "确认密码不能为空！");
                var res4 = this.checkNull("nickname", "昵称不能为空！");
                var res5 = this.checkNull("email", "邮箱不能为空！");
                var res6 = this.checkNull("valistr", "验证码不能为空！");

                var res7 = this.checkPassword("password", "两次密码不一致！");
                var res8 = this.checkEmail("email", "请输入正确的邮箱格式！");

            },
            "checkNull": function (name, msg) {
                var value = $("input[name='" + name + "']").val();
                this.setMsg(name, "");
                if (value.trim() == "") {
                    this.setMsg(name, msg);
                    return false;
                }
                return true;
            },
            "setMsg": function (name, msg) {
                var $span = $("input[name='" + name + "']").nextAll("span");
                $span.html(msg);
                $span.css("color", "red");
            },
            "checkPassword": function (name, msg) {
                var psd1 = $("input[name='" + name + "']").val();
                var psd2 = $("input[name='" + name + "2']").val();
                if (psd1 != "" && psd2 != "") {
                    this.setMsg(name, "");
                    if (psd1 != psd2) {
                        this.setMsg(name, msg);
                        return false;
                    }
                }
                return true;
            },
            "checkEmail": function (name, msg) {
                var email = $("input[name='" + name + "']").val();
                var regExp = /^\w+@\w+(\.\w+)+$/;
                if (email != "") {
                    this.setMsg(name, "");
                    if (!regExp.test(email)) {
                        this.setMsg(name, msg);
                        return false;
                    }
                }
                return true;
            }

        }

    </script>
</head>
<html>
<body>
<form action="${pageContext.request.contextPath}/servlet/RegistServlet" method="POST"
      onsubmit="return formObj.checkForm()">
    <h1>欢迎注册EasyMall</h1>
    <table>
        <tr colspan="2" style="text-align: center;color: red">
            <td>${msg}</td>
        </tr>
        <tr>
            <td class="tds">用户名：</td>
            <td>
                <input type="text" name="username" value="${param.username}"/>
                <span id="username_msg"></span>
            </td>
        </tr>
        <tr>
            <td class="tds">密码：</td>
            <td>
                <input type="password" name="password" value="${param.password}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">确认密码：</td>
            <td>
                <input type="password" name="password2" value="${param.password2}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">昵称：</td>
            <td>
                <input type="text" name="nickname" value="${param.nickname}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">邮箱：</td>
            <td>
                <input type="text" name="email" value="${param.email}"/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="tds">验证码：</td>
            <td>
                <input type="text" name="valistr" value="${param.valistr}"/>
                <img id="img" src="${pageContext.request.contextPath}/servlet/ValiImageServlet" alt=""/>
                <span></span>
            </td>
        </tr>
        <tr>
            <td class="sub_td" colspan="2" class="tds">
                <input type="submit" value="注册用户"/>
            </td>
        </tr>
    </table>
</form>
</body>
</body>
</html>