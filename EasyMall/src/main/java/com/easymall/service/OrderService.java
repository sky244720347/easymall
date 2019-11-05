package com.easymall.service;

import com.easymall.exception.MsgException;
import com.easymall.pojo.Order;
import com.easymall.pojo.OrderInfo;
import com.easymall.pojo.OrderItem;
import com.easymall.pojo.SaleInfo;

import java.util.List;

public interface OrderService extends Service {
    void addOrder(Order order, List<OrderItem> list) throws MsgException;

    List<OrderInfo> findOrderByUserId(int id);

    void deleteOrderByOid(String id) throws MsgException;

    Order findOrderByOid(String oid);

    void updatePaystateByOid(String oid, int paystate);

    List<SaleInfo> findSaleInfos();
}
