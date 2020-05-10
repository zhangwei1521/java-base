package com.zhangwei.javabase.event;

public class RelaxPlanEvent implements PlanEvent {
    @Override
    public String getName() {
        return "go to relax today";
    }
}
