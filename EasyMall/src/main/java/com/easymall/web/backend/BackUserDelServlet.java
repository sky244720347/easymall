package com.easymall.web.backend;

import com.easymall.factory.BasicFactory;
import com.easymall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/BackUserDelServlet")
public class BackUserDelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        boolean success = false;
        try {
            success = service.delUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除用户失败：" + id + " - " + e.getCause().getMessage());
        }
        response.getWriter().write(success + "");
    }
}