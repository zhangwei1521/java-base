package com.zhangwei.javabase.design.listener;

/**
 * 监听器模式
 * 关键要素：事件对象、事件监听器对象，事件广播器对象
 *
 */
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
