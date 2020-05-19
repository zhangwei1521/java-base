package com.zhangwei.javabase.design.listener;


import java.util.LinkedList;
import java.util.List;

public class PlanEventMulticastor {

    private List<PlanEventListener> listeners = new LinkedList<>();

    public void addPlanEventListener(PlanEventListener listener){
        listeners.add(listener);
    }

    public void removePlanEventListener(PlanEventListener listener){
        listeners.remove(listener);
    }

    public void multicastPlanEvent(PlanEvent event){
        for(PlanEventListener listener : listeners){
            if(listener.supportEvent(event)){
                listener.onPlanEvent(event);
            }
        }
    }
}
