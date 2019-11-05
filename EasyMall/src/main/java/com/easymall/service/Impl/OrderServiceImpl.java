package com.easymall.service.Impl;

import com.easymall.dao.OrderMapper;
import com.easymall.dao.ProductMapper;
import com.easymall.exception.MsgException;
import com.easymall.pojo.*;
import com.easymall.service.OrderService;
import com.easymall.utils.TranManager;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder(Order order, List<OrderItem> list) throws MsgException {
        SqlSession session = TranManager.getTranConn();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        ProductMapper productMapper = session.getMapper(ProductMapper.class);
        orderMapper.addOrder(order);
        for (OrderItem orderItem : list) {
            int buynum = orderItem.getBuynum();
            String pid = orderItem.getProduct_id();
            Product p = new Product();
            p.setId(pid);
            Product prod = productMapper.findProdById(p);
            int pnum = prod.getPnum();
            if (buynum > pnum) {
                throw new MsgException("库存数量不足,id:" + pid + ",name:" + prod.getName() + ",pnum:" + pnum);
            }
            orderMapper.addOrderItem(orderItem);
            prod.setPnum(pnum - buynum);
            productMapper.updatePnum(prod);
        }

    }

    @Override
    public List<OrderInfo> findOrderByUserId(int id) {
        SqlSession session = TranManager.getConn();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        ProductMapper prodMapper = session.getMapper(ProductMapper.class);
        List<Order> orderList = orderMapper.findOrderByUserId(id);
        if (orderList == null) return null;
        List<OrderInfo> list = new ArrayList<>();
        for (Order order : orderList) {
            List<OrderItem> oiList = orderMapper.findOrderItemByOrderId(order.getId());
            HashMap<Product, Integer> map = new HashMap<>();
            if (oiList != null) {
                for (OrderItem item : oiList) {
                    Product p = new Product();
                    p.setId(item.getProduct_id());
                    Product prod = prodMapper.findProdById(p);
                    int buynum = item.getBuynum();
                    map.put(prod, buynum);
                }
            }
            OrderInfo orderinfo = new OrderInfo();
            orderinfo.setOrder(order);
            orderinfo.setMap(map);
            list.add(orderinfo);
        }
        return list;
    }

    @Override
    public void deleteOrderByOid(String id) throws MsgException {
        SqlSession session = TranManager.getTranConn();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        orderMapper.deleteOrderByOid(id);
        orderMapper.deleteOrderItemsByOid(id);
    }

    @Override
    public Order findOrderByOid(String oid) {
        SqlSession session = TranManager.getConn();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        Order order = orderMapper.findOrderByOid(oid);
        return order;
    }

    @Override
    public void updatePaystateByOid(String oid, int paystate) {
        SqlSession session = TranManager.getConn();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        orderMapper.updatePaystateByOid(oid, paystate);
    }

    @Override
    public List<SaleInfo> findSaleInfos() {
        SqlSession session = TranManager.getConn();
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        List<SaleInfo> saleInfos = orderMapper.findSaleInfos();
        return saleInfos;
    }
}