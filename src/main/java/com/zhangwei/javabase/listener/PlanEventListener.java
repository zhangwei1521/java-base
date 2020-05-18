package com.zhangwei.javabase.listener;

public interface PlanEventListener<T extends PlanEvent> {
    void onPlanEvent(T event);

    boolean supportEvent(PlanEvent event);
}
