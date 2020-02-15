package com.wpstan.demo.aop.aop;

import java.lang.reflect.Method;

/**
 * 用户需要增强的功能
 */
public interface Advice {

    //需要执行原有方法，因此参数需要对象、方法、方法参数
    Object invoke(Object target, Method method, Object[] args);
}
