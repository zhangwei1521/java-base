package com.zhangwei.javabase.design.listener;

public interface PlanEventListener<T extends PlanEvent> {
    void onPlanEvent(T event);

    boolean supportEvent(PlanEvent event);
}
