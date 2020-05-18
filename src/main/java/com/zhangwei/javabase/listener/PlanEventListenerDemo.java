package com.zhangwei.javabase.listener;

public class PlanEventListenerDemo {
    public static void main(String[] args) {
        StudyPlanEventListener studyListener = new StudyPlanEventListener();
        RelaxPlanEventListener relaxListener = new RelaxPlanEventListener();

        PlanEventMulticastor planEventMulticastor = new PlanEventMulticastor();
        planEventMulticastor.addPlanEventListener(studyListener);
        planEventMulticastor.addPlanEventListener(relaxListener);

        StudyPlanEvent e1 = new StudyPlanEvent();
        RelaxPlanEvent e2 = new RelaxPlanEvent();

        planEventMulticastor.multicastPlanEvent(e1);
        planEventMulticastor.multicastPlanEvent(e2);

        planEventMulticastor.removePlanEventListener(relaxListener);

        planEventMulticastor.multicastPlanEvent(e1);
        planEventMulticastor.multicastPlanEvent(e2);
    }
}
