package com.easymall.service.Impl;

import com.easymall.dao.UserMapper;
import com.easymall.pojo.User;
import com.easymall.service.TranTestService;
import com.easymall.utils.TranManager;
import org.apache.ibatis.session.SqlSession;

public class TranTestServiceImpl implements TranTestService {
    @Override
    public boolean modifyUser() {
        User user = new User();
        user.setId(100);
        user.setUsername("100name");
        user.setPassword("100pwd");
        user.setEmail("100email");
        user.setRole("admin");
        System.out.println(user);

        SqlSession session = TranManager.getTranConn();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        userMapper.addUser(user);
        User user1 = userMapper.findUserById(user.getId());
        System.out.println(user1);

        int rst = userMapper.updatePwd("100", "newpwd");
        if (rst < 1) {
            throw new RuntimeException("密码更新失败！");
        }
        User user2 = userMapper.findUserById(user.getId());
        System.out.println(user2);
        return true;
    }
}