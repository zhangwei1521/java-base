package com.zhangwei.javabase.design.proxy;

import com.zhangwei.javabase.common.Account;

public class UserAccountServiceImpl implements UserAccountService {
    @Override
    public Account getUserAccount(String name) {
        Account account = new Account();
        account.setUsername(name);
        account.setAmount(100.0);
        return account;
    }
}
