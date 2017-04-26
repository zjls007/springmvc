package com.cy.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zxj on 2017/4/26.
 */
@Component("logProxy")
public class LogProxy {

    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("begin...");
        point.proceed();
        System.out.println("after...");
    }

}
