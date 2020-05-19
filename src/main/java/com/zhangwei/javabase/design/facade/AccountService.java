package com.zhangwei.javabase.design.facade;

import com.zhangwei.javabase.common.Account;
import com.zhangwei.javabase.common.User;

public class AccountService {
    public Account updateAccount(User user){
        Account account = new Account();
        account.setAcountNum("152");
        account.setUsername(user.getName());
        account.setAmount(100.0);
        return account;
    }
}
