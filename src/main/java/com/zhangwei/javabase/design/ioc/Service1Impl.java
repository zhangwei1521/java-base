package com.zhangwei.javabase.design.ioc;

@Bean
public class Service1Impl implements Service1 {

    private Service2 service2;

    @Inject(value = Service2.class)
    public Service1Impl(Service2 service2){
        this.service2 = service2;
    }

    @Override
    public void doService() {
        System.out.println("start doService!");
        service2.doCreate("bean$person$zhangwei");
        System.out.println("doService finished!");
    }
}
