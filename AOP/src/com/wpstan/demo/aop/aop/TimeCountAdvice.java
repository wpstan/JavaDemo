package com.wpstan.demo.aop.aop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TimeCountAdvice implements Advice {
    @Override
    public Object invoke(Object target, Method method, Object[] args) {
        long time1 = System.currentTimeMillis();
        try {
            Object obj = method.invoke(target, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        long time2 = System.currentTimeMillis();
        System.out.println(target.getClass().getName() + "对象调用" + method.getName() + "，执行时间:" + (time2 - time1));
        return null;
    }
}
