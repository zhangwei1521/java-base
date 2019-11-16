package com.zhangwei.javabase.jdbc;

public class Account {
    private int id;
    private String name;
    private String num;
    private String pwd;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num='" + num + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }

    public String getPwd() {
        return pwd;
    }
}
