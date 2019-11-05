package com.easymall.web.backend;

import com.easymall.factory.BasicFactory;
import com.easymall.pojo.SaleInfo;
import com.easymall.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/servlet/BackSaleListServlet")
public class BackSaleListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        List<SaleInfo> list = service.findSaleInfos();
        request.setAttribute("saleList", list);
        request.getRequestDispatcher("/backend/sale_list.jsp").forward(request, response);
    }
}
