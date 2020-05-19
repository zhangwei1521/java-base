package com.zhangwei.javabase.design.proxy;

import com.zhangwei.javabase.common.Account;
import com.zhangwei.javabase.common.User;

public class UserAccountServiceProxy implements UserAccountService {

    public static final ThreadLocal<User> tl = new ThreadLocal<>();

    private UserAccountService userAccountService;

    public UserAccountServiceProxy(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }

    @Override
    public Account getUserAccount(String name) {
        User user = tl.get();
        if(user == null || user.getName()==null || !user.getName().equals(name)){
            throw new RuntimeException("illegal access");
        }
        return userAccountService.getUserAccount(name);
    }
}
