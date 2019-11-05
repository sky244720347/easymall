package com.easymall.web;

import com.easymall.exception.MsgException;
import com.easymall.factory.BasicFactory;
import com.easymall.pojo.Order;
import com.easymall.pojo.OrderItem;
import com.easymall.pojo.Product;
import com.easymall.pojo.User;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/servlet/OrderAddServlet")
public class OrderAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        Order order = new Order();
        order.setUser_id(user.getId());
        order.setId(UUID.randomUUID().toString());
        order.setReceiverinfo(request.getParameter("receiverinfo"));
        order.setPaystate(0);
        Map<Product, Integer> cartmap = (Map<Product, Integer>) request.getSession().getAttribute("cartmap");
        List<OrderItem> list = new ArrayList<>();
        double totalMoney = 0;
        for (Map.Entry<Product, Integer> entry : cartmap.entrySet()) {
            OrderItem item = new OrderItem();
            item.setProduct_id(entry.getKey().getId());
            item.setOrder_id(order.getId());
            item.setBuynum(entry.getValue());
            totalMoney += item.getBuynum() * entry.getKey().getPrice();
            order.setMoney(totalMoney);
            list.add(item);
        }
        OrderService orderService = BasicFactory.getFactory().getInstance(OrderService.class);
        try {
            orderService.addOrder(order, list);
            cartmap.clear();
            response.sendRedirect(request.getContextPath() + "/servlet/OrderListServlet");
        } catch (MsgException e) {
            request.setAttribute("msg", e.getMessage());
            request.getRequestDispatcher("/cart.jsp").forward(request, response);
        }


    }
}
