package com.zhangwei.javabase.design.facade;

import com.zhangwei.javabase.common.Account;
import com.zhangwei.javabase.common.User;

public class AccountServiceFacade {
    public Account dealUserAccount(String name){
        UserService userService = new UserService();
        User user = userService.getUserInfo(name);
        AccountService accountService = new AccountService();
        Account account = accountService.updateAccount(user);
        return account;
    }
}
