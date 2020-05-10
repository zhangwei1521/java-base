package com.zhangwei.javabase.event;

public interface PlanEventListener<T extends PlanEvent> {
    void onPlanEvent(T event);

    boolean supportEvent(PlanEvent event);
}
