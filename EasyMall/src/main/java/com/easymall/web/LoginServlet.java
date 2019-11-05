package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.pojo.User;
import com.easymall.service.UserService;
import com.easymall.utils.MD5Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author Steven
 * @create 2019-01-15 14:43
 * 处理用户登录请求
 */
@WebServlet("/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        request.setCharacterEncoding("utf-8");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remname = request.getParameter("remname");
        UserService UserService = BasicFactory.getFactory().getInstance(
                UserService.class);
        User user = null;
        try {
            user = UserService.loginUser(username, MD5Utils.md5(password));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {//用户名密码正确
            if ("true".equals(remname)) {
                Cookie cookie = new Cookie("remname", URLEncoder.encode(
                        username, "utf-8"));
                cookie.setPath(request.getContextPath() + "/");
                cookie.setMaxAge(3600 * 24 * 30);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("remname", "");
                cookie.setPath(request.getContextPath() + "/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            String autologin = request.getParameter("autologin");
            if ("true".equals(autologin)) {// 实现30天自动登陆
                // 将用户名和密码保存进Cookie中
                Cookie c = new Cookie("autologin", URLEncoder.encode(username, "utf-8") + ":" + MD5Utils.md5(password));
                c.setMaxAge(60 * 60 * 24 * 30);// 保存Cookie30天
                c.setPath(request.getContextPath() + "/");
                // 将Cookie发送给浏览器
                response.addCookie(c);
            } else {// 取消30天自动登陆
                Cookie c = new Cookie("autologin", "");
                c.setMaxAge(0);// 设置为0立即删除
                c.setPath(request.getContextPath() + "/");
                response.addCookie(c);
            }
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {// 用户名密码不正确
            request.setAttribute("msg", "用户名或密码不正确!");
            request.getRequestDispatcher("/login.jsp").forward(request,
                    response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
