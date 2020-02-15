package com.wpstan.demo.aop.aop;

/**
 * 切面由切点和额外功能构成
 */
public class Aspect {

    private Advice advice;
    private Pointcut pointcut;

    public Aspect(Advice advice, Pointcut pointcut) {
        this.advice = advice;
        this.pointcut = pointcut;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Pointcut getPointcut() {
        return pointcut;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }
}
