package com.zhangwei.javabase.design.facade;

import com.zhangwei.javabase.common.User;

public class UserService {
    public User getUserInfo(String name){
        User user = User.getInstance();
        user.setName(name);
        user.setAge(25);
        user.setPass("root");
        return user;
    }
}
