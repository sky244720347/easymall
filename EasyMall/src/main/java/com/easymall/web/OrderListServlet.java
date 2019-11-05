package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.pojo.OrderInfo;
import com.easymall.pojo.User;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/OrderListServlet")
public class OrderListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
        List<OrderInfo> list = orderService.findOrderByUserId(user.getId());
        request.setAttribute("list", list);
        request.getRequestDispatcher("/order_list.jsp").forward(request, response);
    }
}
