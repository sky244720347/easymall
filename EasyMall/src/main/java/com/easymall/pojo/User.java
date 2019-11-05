package com.easymall.pojo;

import com.easymall.exception.MsgException;
import com.easymall.utils.WebUtils;

public class User {
    private int id;
    private String username;
    private String password;
    private String password2;
    private String nickname;
    private String email;
    private String role;

    public User() {
    }

    public User(int id, String username, String password, String nickname,
                String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void checkData() throws MsgException {
        if (WebUtils.isNull(username)) {
            throw new MsgException("用户名不能为空！");
        }
        if (WebUtils.isNull(password)) {
            throw new MsgException("密码不能为空!");
        }
        if (WebUtils.isNull(password2)) {
            throw new MsgException("确认密码不能为空!");
        }
        if (!password.equals(password2)) {
            throw new MsgException("两次密码不一致!");
        }
        if (WebUtils.isNull(nickname)) {
            throw new MsgException("昵称不能为空!");
        }
        if (WebUtils.isNull(email)) {
            throw new MsgException("邮箱不能为空!");
        }
        if (!email.matches("^\\w+@\\w+(\\.\\w+)+$")) {
            throw new MsgException("邮箱格式不正确!");
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}