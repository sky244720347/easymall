package com.easymall.web;

import com.easymall.exception.MsgException;
import com.easymall.factory.BasicFactory;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/servlet/OrderDeleteServlet")
public class OrderDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
        try {
            orderService.deleteOrderByOid(id);
            response.getWriter().write("订单删除成功，2秒后自动跳转...");
        } catch (MsgException e) {
            response.getWriter().write("订单删除失败，2秒后自动跳转...");
        }
        response.setHeader("refresh", "2;url=" + request.getContextPath() + "/servlet/OrderListServlet");
    }
}
