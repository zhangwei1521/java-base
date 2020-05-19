package com.zhangwei.javabase.design.listener;

public class RelaxPlanEventListener implements PlanEventListener<RelaxPlanEvent> {
    @Override
    public void onPlanEvent(RelaxPlanEvent event) {
        String name = event.getName();
        System.out.println(name);
        System.out.println("get up at 8:30");
    }

    @Override
    public boolean supportEvent(PlanEvent event) {
        if(RelaxPlanEvent.class.isAssignableFrom(event.getClass())){
            return true;
        }
        return false;
    }
}
