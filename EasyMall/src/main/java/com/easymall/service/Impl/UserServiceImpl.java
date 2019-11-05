package com.easymall.service.Impl;

import com.easymall.dao.OrderMapper;
import com.easymall.dao.UserMapper;
import com.easymall.exception.MsgException;
import com.easymall.pojo.Order;
import com.easymall.pojo.OrderItem;
import com.easymall.pojo.User;
import com.easymall.service.UserService;
import com.easymall.utils.MD5Utils;
import com.easymall.utils.TranManager;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public void registUser(User user) throws MsgException {
        SqlSession session = TranManager.getConn();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        if (userMapper.findUserByUsername(user.getUsername()) != null) {
            throw new MsgException("用户已存在！");
        } else {
            userMapper.addUser(user);
        }
    }

    @Override
    public boolean hasUser(String username) {
        SqlSession session = TranManager.getConn();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User result = userMapper.findUserByUsername(username);
        return result != null;
    }

    @Override
    public User loginUser(String username, String password) {
        SqlSession session = TranManager.getConn();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User result = userMapper.findUserByNameAndPwd(username, password);
        return result;
    }

    @Override
    public boolean delUser(String id) {
        SqlSession session = TranManager.getTranConn();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserById(Integer.parseInt(id));
        if (user != null) {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
            List<Order> orders = orderMapper.findOrderByUserId(user.getId());
            if (orders != null && orders.size() > 0) {
                for (Order order : orders) {
                    List<OrderItem> items = orderMapper.findOrderItemByOrderId(order.getId());
                    if (items != null && items.size() > 0) {
                        orderMapper.deleteOrderItemsByOid(order.getId());
                    }
                    orderMapper.deleteOrderByOid(order.getId());
                }
            }
            int result = userMapper.delUser(id);
            if (result < 1) {
                throw new RuntimeException("删除失败！");
            }
        } else {
            throw new RuntimeException("用户不存在！");
        }
        return true;
    }

    @Override
    public List<User> findAll() {
        SqlSession session = TranManager.getConn();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        return userMapper.findAll();
    }

    @Override
    public boolean resetPwd(String id) {
        SqlSession session = TranManager.getConn();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        int result = userMapper.updatePwd(id, MD5Utils.md5("123456"));
        return result > 0;
    }
}