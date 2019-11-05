package com.easymall.dao;

import com.easymall.pojo.Order;
import com.easymall.pojo.OrderItem;
import com.easymall.pojo.SaleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void addOrder(Order order);

    void addOrderItem(OrderItem orderItem);

    List<Order> findOrderByUserId(int userId);

    List<OrderItem> findOrderItemByOrderId(String id);

    Order findOrderByOid(String id);

    void deleteOrderItemsByOid(String id);

    void deleteOrderByOid(String id);

    void updatePaystateByOid(@Param("id") String id, @Param("paystate") int paystate);

    List<SaleInfo> findSaleInfos();
}
