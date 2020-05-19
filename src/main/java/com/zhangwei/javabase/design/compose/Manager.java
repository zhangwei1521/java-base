package com.zhangwei.javabase.design.compose;

import java.util.ArrayList;
import java.util.List;

public class Manager implements Employee {

    private String name;

    private String title;

    private List<Employee> underlings = new ArrayList<>();

    public Manager(String name,String title){
        this.name = name;
        this.title = title;
    }

    @Override
    public String getFullInfo() {
        StringBuilder infoBuilder = new StringBuilder("{");
        infoBuilder.append("name: ").append(name).append(",");
        infoBuilder.append("title: ").append(title).append(",");
        infoBuilder.append("underlings: [");
        String[] underlingsInfo = new String[underlings.size()];
        for(int i=0;i<underlings.size();i++){
            underlingsInfo[i] = underlings.get(i).getFullInfo();
        }
        infoBuilder.append(String.join(",",underlingsInfo));
        infoBuilder.append("]}");
        return infoBuilder.toString();
    }

    public void add(Employee underling){
        underlings.add(underling);
    }

    public void remove(Employee underling){
        underlings.remove(underling);
    }


    public List<Employee> getUnderlings(){
        return underlings;
    }
}
