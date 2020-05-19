package com.zhangwei.javabase.design.adapter;

/**
 * 适配器模式
 */
public class AdaterDemo {
    public static void main(String[] args) {
        LocalStyleService localStyleService = new LocalServiceImpl();
        ClientPreferStyleService service = new ServiceAdapter(localStyleService);
        service.selectList();
    }
}
