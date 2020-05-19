package com.zhangwei.javabase.design.template;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JsCalculator extends AbstractCalculator {
    @Override
    protected void doCalculate(String exp) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        try {
            System.out.println(engine.eval(exp));
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
