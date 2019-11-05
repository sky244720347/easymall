package com.easymall.service;

import com.easymall.exception.MsgException;
import com.easymall.factory.BasicFactory;
import com.easymall.pojo.User;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    UserService userService;

    @Before
    public void init() {
        userService = BasicFactory.getFactory().getInstance(UserService.class);
    }

    @Test
    public void hasUser() {
        String username = "admin";
        boolean hasUser = userService.hasUser(username);
        System.out.println(username + ": " + hasUser);
        username = "a";
        hasUser = userService.hasUser(username);
        System.out.println(username + ": " + hasUser);
    }

    @Test
    public void registUser() {
        User user = new User();
        user.setUsername("heweinan");
        user.setPassword("1111");
        user.setNickname("test");
        user.setEmail("123@qq.com");
        try {
            userService.registUser(user);
        } catch (MsgException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginUser() {
        System.out.println(userService.loginUser("heweinan", "0000").getEmail());
    }

    @Test
    public void deleteUser() {
        try {
            userService.delUser("1");
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getCause().getMessage());
        }
    }
}