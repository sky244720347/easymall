package com.easymall.web;

import com.easymall.factory.BasicFactory;
import com.easymall.pojo.Product;
import com.easymall.service.ProdService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/servlet/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到索要加入购物车的商品id以及购买的数量
        String pid = request.getParameter("pid");
        int buyNum = Integer.parseInt(request.getParameter("buyNum"));
        // 调用service层的方法根据id查询商品信息
        ProdService service = BasicFactory.getFactory().getInstance(
                ProdService.class);
        Product prod = service.findProdById(pid);
        //获取session中保存商品的购物车map
        Map<Product, Integer> map = (Map<Product, Integer>) request
                .getSession().getAttribute("cartmap");
        if (buyNum < 0) {
            map.remove(prod);
        } else {
            map.put(prod, map.containsKey(prod) ?
                    map.get(prod) + buyNum : buyNum);
        }
        //重定向到购物车列表页面
        response.sendRedirect(request.getContextPath() + "/cart.jsp");
    }
}