package com.easymall.service;

import com.easymall.exception.MsgException;
import com.easymall.pojo.User;

import java.util.List;

public interface UserService extends Service {
    void registUser(User user) throws MsgException;

    boolean hasUser(String username);

    User loginUser(String username, String password);

    boolean delUser(String id);

    List<User> findAll();

    boolean resetPwd(String id);
}