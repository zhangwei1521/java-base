package com.zhangwei.javabase.design.facade;

import com.zhangwei.javabase.common.Account;
import com.zhangwei.javabase.common.User;

public class FacadeDemo {
    public static void main(String[] args) {
        //直接使用UserService和AccountService
        UserService userService = new UserService();
        User user = userService.getUserInfo("root");
        AccountService accountService = new AccountService();
        Account account = accountService.updateAccount(user);

        //使用facade模式
        AccountServiceFacade facade = new AccountServiceFacade();
        account = facade.dealUserAccount("root");
    }
}
