package com.zhangwei.javabase.design.template;

/**
 * 模板方法模式
 * 这里使用基于继承的模板方法
 * 另一种模板方法是基于回调函数的，在java中是通过定义一个回调接口实现(准确来说
 * 这种基于回调接口的实现不属于模板方法模式，而是策略模式)
 * @see com.zhangwei.javabase.jdbc.JdbcTemplateDemo
 */
public class TemplateDemo {
    public static void main(String[] args) {
        JsCalculator calculator1 = new JsCalculator();
        calculator1.calculate("3 + ( 5 * 2 )");

        JavaCalculator calculator2 = new JavaCalculator();
        calculator2.calculate("15 - ( 9 + 2 )");
    }
}
