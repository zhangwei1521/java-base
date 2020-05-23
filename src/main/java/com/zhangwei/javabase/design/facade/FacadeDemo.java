package com.zhangwei.javabase.design.facade;

import com.zhangwei.javabase.common.Account;
import com.zhangwei.javabase.common.User;

/**
 * 外观模式，也称门面模式
 * Facade对象就是一个总代理对象，把需要客户端分别单独调用的逻辑封装到Facade对象的方法中
 */
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
