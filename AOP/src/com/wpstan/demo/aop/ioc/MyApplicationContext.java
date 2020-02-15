package com.wpstan.demo.aop.ioc;

import com.wpstan.demo.aop.aop.Aspect;
import com.wpstan.demo.aop.aop.MyInvocationHandler;

import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyApplicationContext implements ApplicationContext {
    private Map<String, Class<?>> beanDefinition = new ConcurrentHashMap<>();

    //可以设置多个切面，这里只用一个切面模拟
    private Aspect aspect;

    @Override
    public Object getBean(String beanName) {
        //只传入beanName返回对象，必须有一个关联关系，因此需要BeanDefinition类来关联注册。
        Object bean = createBeanInstance(beanName);
        //这里不能直接返回bean，必须返回经过加工过的类，可以使用动态代理
        bean = proxyEnhance(bean);
        return bean;
    }

    private Object proxyEnhance(Object bean) {
        if (this.aspect != null && bean.getClass().getName().matches(aspect.getPointcut().getClassPattern())) {
            return Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                    new MyInvocationHandler(this.aspect, bean));
        }
        return bean;
    }

    private Object createBeanInstance(String beanName) {
        try {
            return beanDefinition.get(beanName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void registerBeanDefinition(String beanName, Class<?> clazz) {
        this.beanDefinition.put(beanName, clazz);
    }

    @Override
    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }
}
