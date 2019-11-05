package com.easymall.web;

import com.easymall.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet/ValiImageServlet")
public class ValiImageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setDateHeader("Expires", -1);
//        response.setHeader("Cache-Control", "no-cache");

        VerifyCode vc = new VerifyCode();
        //将图片保存到response缓冲区中, 再响应给浏览器
        vc.drawImage(response.getOutputStream());

        //获取图片上的验证码
        String code = vc.getCode();
        //将验证码文本保存到session中, 用于后期的校验
        request.getSession().setAttribute("code", code);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
