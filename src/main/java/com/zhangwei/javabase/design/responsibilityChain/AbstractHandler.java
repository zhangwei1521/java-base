package com.zhangwei.javabase.design.responsibilityChain;

public abstract class AbstractHandler {
    private String name;
    private AbstractHandler next;

    protected AbstractHandler(String name){
        this.name = name;
    }

    public void setNext(AbstractHandler next){
        this.next = next;
    }

    public final void handle(Request request){
        if(support(request)){
            System.out.println(this.name+" begin handle "+request.getType());
            doHandle(request);
        }
        else if(this.next != null){
            this.next.handle(request);
        }
        else {
            throw new RuntimeException("no handler for this request"+request.getType());
        }
    }

    protected abstract void doHandle(Request request);

    protected abstract boolean support(Request request);
}
