package com.wpstan.demo.aop.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    private Aspect aspect;
    private Object bean;

    public MyInvocationHandler(Aspect aspect, Object bean) {
        this.aspect = aspect;
        this.bean = bean;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().matches(aspect.getPointcut().getMethodPattern())) {
            return aspect.getAdvice().invoke(bean, method, args);
        }
        return method.invoke(bean, args);
    }
}
