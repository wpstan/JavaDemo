package com.wpstan.demo.aop.ioc;

import com.wpstan.demo.aop.aop.Aspect;

public interface ApplicationContext {

     Object getBean(String beanName);
     void registerBeanDefinition(String beanName, Class<?> clazz);
     void setAspect(Aspect aspect);
}
