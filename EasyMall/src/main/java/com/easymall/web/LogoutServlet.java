package com.easymall.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理用户的注销请求
 *
 * @author Steven
 * @create 2019-01-15 14:43
 */
@WebServlet("/servlet/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //杀死session
        request.getSession().invalidate();
        //如果用户点击退出按钮，默认是取消自动登录，即删除自动登录Cookie
        Cookie c = new Cookie("autologin", "");
        c.setMaxAge(0);//设置为0立即删除
        c.setPath(request.getContextPath() + "/");
        response.addCookie(c);

        //退出之后跳转主页
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
