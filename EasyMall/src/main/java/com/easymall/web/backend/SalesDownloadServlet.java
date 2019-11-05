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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/servlet/SalesDownloadServlet")
public class SalesDownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        List<SaleInfo> list = service.findSaleInfos();
        StringBuilder sb = new StringBuilder("id,name,sales\n");
        for (SaleInfo info : list) {
            sb.append(info);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMHHmmssSSS");
        String fileName = sdf.format(new Date()) + ".csv";
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.getWriter().write(sb.toString());
    }
}