package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/AjaxCheckUsernameServlet")
public class AjaxCheckUsernameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        boolean result = service.hasUser(username);
        response.getWriter().write(result ? "用户名已存在！" : "恭喜，该用户名可以使用！");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
