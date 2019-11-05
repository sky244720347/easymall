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
import java.util.List;

/**
 * @author Steven
 * @create 2019-01-15 14:54
 */
@WebServlet("/servlet/ProdListByConditionServlet")
public class ProdListByConditionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        获取查询条件（商品的名称name，商品的分类category，商品的最低价格minprice，商品的最高价格maxprice）
         */
        String name = req.getParameter("name");
        String category = req.getParameter("category");
        String minprice = req.getParameter("minprice");
        String maxprice = req.getParameter("maxprice");
        /*
        对查询条件进行处理
         */
        String _name = null;
        String _category = null;
        double _minprice = 0;
        double _maxprice = Double.MAX_VALUE;
        if (name != null && !"".equals(name)) {
            _name = name;
        }
        if (category != null && !"".equals(category)) {
            _category = category;
        }
        String regex = "^(0(\\.[0-9]+)?)|([1-9][0-9]*(\\.[0-9]+)?)$";//小数的正则表达式
        if (minprice != null && !"".equals(minprice)) {
            if (minprice.matches(regex)) {
                _minprice = Double.parseDouble(minprice);
            }
        }
        if (maxprice != null && !"".equals(maxprice)) {
            if (maxprice.matches(regex) && Double.parseDouble(maxprice) >= _minprice) {
                _maxprice = Double.parseDouble(maxprice);
            }
        }
        /*
        调用service层方法根据条件查询所有符合条件的商品
         */
        ProdService service = BasicFactory.getFactory().getInstance(ProdService.class);
        List<Product> list = service.findAllByCondition(_name, _category, _minprice, _maxprice);
        /*
        将保存符合搜索条件所有的商品的list集合存入request域中
         */
        req.setAttribute("list", list);
        /*
        通过转发将符合搜索条件的所有商品转到商品搜索列表界面
         */
        req.getRequestDispatcher("/prod_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
