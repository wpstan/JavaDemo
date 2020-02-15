package com.wpstan.demo.aop;

import com.wpstan.demo.aop.aop.Advice;
import com.wpstan.demo.aop.aop.Aspect;
import com.wpstan.demo.aop.aop.Pointcut;
import com.wpstan.demo.aop.aop.TimeCountAdvice;
import com.wpstan.demo.aop.service.ConfigService;
import com.wpstan.demo.aop.service.impl.JsonConfigService;
import com.wpstan.demo.aop.service.impl.XmlConfigService;
import com.wpstan.demo.aop.ioc.ApplicationContext;
import com.wpstan.demo.aop.ioc.MyApplicationContext;

public class Main {
    public static void main(String[] args) {
        Advice advice = new TimeCountAdvice();
        Pointcut pointcut = new Pointcut("com\\.wpstan\\.demo\\.aop\\.service\\.impl\\..*", ".*Config");
        //通过增强功能和切点构造切面
        Aspect aspect = new Aspect(advice, pointcut);

        //框架处理该切面，使得切面生效
        //前提需要让框架来管理对象，因此需要IOC
        //构建一个IOC容器
        ApplicationContext context = new MyApplicationContext();
        context.setAspect(aspect);
        //给IOC容器注册，可以xml，注解等，这里直接代码模拟注册功能。
        context.registerBeanDefinition("jsonConfig", JsonConfigService.class);
        context.registerBeanDefinition("xmlConfig", XmlConfigService.class);

        ConfigService configService = (ConfigService) context.getBean("jsonConfig");
        configService.loadConfig();


    }
}
