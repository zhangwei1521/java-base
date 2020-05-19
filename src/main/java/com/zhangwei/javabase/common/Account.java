package com.zhangwei.javabase.common;

public class Account {
    private String acountNum;
    private String username;
    private Double amount;

    @Override
    public String toString() {
        return "Acount{" +
                "acountNum='" + acountNum + '\'' +
                ", username='" + username + '\'' +
                ", amount=" + amount +
                '}';
    }

    public void setAcountNum(String acountNum) {
        this.acountNum = acountNum;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getAcountNum() {
        return acountNum;
    }

    public String getUsername() {
        return username;
    }

    public Double getAmount() {
        return amount;
    }
}
