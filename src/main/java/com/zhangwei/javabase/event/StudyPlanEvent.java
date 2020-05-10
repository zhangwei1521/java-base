package com.zhangwei.javabase.event;

public class StudyPlanEvent implements PlanEvent {
    @Override
    public String getName() {
        return "go to study today";
    }
}
