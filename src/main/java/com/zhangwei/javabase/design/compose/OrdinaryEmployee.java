package com.zhangwei.javabase.design.compose;

public class OrdinaryEmployee implements Employee {
    private String name;

    private String title;

    public OrdinaryEmployee(String name,String title){
        this.name = name;
        this.title = title;
    }

    @Override
    public String getFullInfo() {
        StringBuilder infoBuilder = new StringBuilder("{");
        infoBuilder.append("name: ").append(name).append(",");
        infoBuilder.append("title: ").append(title);
        infoBuilder.append("}");
        return infoBuilder.toString();
    }
}
