package com.easymall.web.backend;

import com.easymall.factory.BasicFactory;
import com.easymall.pojo.User;
import com.easymall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/BackUserListServlet")
public class BackUserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService service = BasicFactory.getFactory().getInstance(UserService.class);
        List<User> list = service.findAll();
        request.setAttribute("userList", list);
        request.getRequestDispatcher("/backend/user_list.jsp").forward(request, response);
    }
}