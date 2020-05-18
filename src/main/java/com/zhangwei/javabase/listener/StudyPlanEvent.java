package com.zhangwei.javabase.listener;

public class StudyPlanEvent implements PlanEvent {
    @Override
    public String getName() {
        return "go to study today";
    }
}
