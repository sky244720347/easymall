package com.easymall.service;

import com.easymall.exception.MsgException;
import com.easymall.factory.BasicFactory;
import com.easymall.pojo.Order;
import com.easymall.pojo.OrderItem;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderTest {
    OrderService orderService;

    @Before
    public void init() {
        orderService = BasicFactory.getFactory().getInstance(OrderService.class);
    }

    @Test
    public void addOrder() throws MsgException {
        Order order = new Order();
        order.setId("te111");
        order.setMoney(10000);
        order.setOrdertime(new Date());
        order.setPaystate(0);
        order.setReceiverinfo("上海市123");
        order.setUser_id(12);
        List<OrderItem> list = new ArrayList<>();
        OrderItem item = new OrderItem();
        item.setBuynum(1);
        item.setOrder_id(order.getId());
        item.setProduct_id("1");
        list.add(item);
        orderService.addOrder(order, list);
    }

    @Test
    public void findOrderByUserId() {
        System.out.println(orderService.findOrderByUserId(9));
    }

    @Test
    public void deleteOrderByOid() throws MsgException {
        orderService.deleteOrderByOid("te");
    }

    @Test
    public void findOrderByOid() {
        Order order = orderService.findOrderByOid("test");
        System.out.println(order.getOrdertime());
    }

    @Test
    public void updatePaystateByOid() {
        orderService.updatePaystateByOid("test", 1);
    }


    @Test
    public void findSaleInfos() {
        System.out.println(orderService.findSaleInfos());
    }

}
