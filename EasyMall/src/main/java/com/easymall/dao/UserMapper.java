package com.easymall.dao;

import com.easymall.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    void addUser(User user);

    User findUserByUsername(String username);

    User findUserByNameAndPwd(@Param("username") String username, @Param("password") String password);

    User findUserById(int user_id);

    int delUser(String id);

    List<User> findAll();

    int updatePwd(@Param("id") String id, @Param("password") String password);
}