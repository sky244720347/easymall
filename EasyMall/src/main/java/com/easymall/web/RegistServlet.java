package com.easymall.web;

import com.easymall.exception.MsgException;
import com.easymall.factory.BasicFactory;
import com.easymall.pojo.User;
import com.easymall.service.UserService;
import com.easymall.utils.MD5Utils;
import com.easymall.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(urlPatterns = "/servlet/RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        String valistr = request.getParameter("valistr");
        if (WebUtils.isNull(valistr)) {
            request.setAttribute("msg", "验证码不能为空");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        String code = (String) request.getSession().getAttribute("code");
        if (!valistr.equalsIgnoreCase(code)) {
            request.setAttribute("msg", "验证码不正确！");
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        String username = request.getParameter("username");
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        try {
            user.checkData();
            user.setPassword(MD5Utils.md5(user.getPassword()));
            UserService service = BasicFactory.getFactory().getInstance(UserService.class);
            service.registUser(user);
        } catch (MsgException e) {
            e.printStackTrace();
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", e.getCause().getMessage());
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
            return;
        }
        response.getWriter().write("<h1 style='color:red;text-align:center'>恭喜注册成功，3秒后调整至首页..</h1>");
        response.setHeader("refresh", "3,url=" + request.getContextPath() + "/index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
