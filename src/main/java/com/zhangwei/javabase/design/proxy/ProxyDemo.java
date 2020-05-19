package com.zhangwei.javabase.design.proxy;

import com.zhangwei.javabase.common.Account;
import com.zhangwei.javabase.common.User;

/**
 * 代理模式，代理模式要求代理类实现被代理类的所有接口
 * 代理模式和装饰器模式的区别在于，代理对象的问题域不同于被代理类的问题域，
 * 而装饰器模式的装饰器对象则用于增强被装饰对象，问题域相同
 */
public class ProxyDemo {


    public static void main(String[] args) {
        User user = User.getInstance();
        user.setName("root");
        UserAccountServiceProxy.tl.set(user);

        UserAccountService userAccountService = new UserAccountServiceProxy(new UserAccountServiceImpl());
        Account account = userAccountService.getUserAccount(user.getName());
        System.out.println(account);
    }
}
