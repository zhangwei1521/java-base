package com.zhangwei.javabase.design.listener;

public class StudyPlanEventListener implements PlanEventListener<StudyPlanEvent> {
    @Override
    public void onPlanEvent(StudyPlanEvent event) {
        String name = event.getName();
        System.out.println(name);
        System.out.println("get up at 7:30");
    }

    @Override
    public boolean supportEvent(PlanEvent event) {
        if(StudyPlanEvent.class.isAssignableFrom(event.getClass())){
            return true;
        }
        return false;
    }
}
