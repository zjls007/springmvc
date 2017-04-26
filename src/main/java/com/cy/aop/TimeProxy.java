package com.cy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by zxj on 2017/4/26.
 */
@Component
@Aspect
@Order(2) // order 的值越小，说明越先被执行
public class TimeProxy {

    @Pointcut("execution(* com.cy.aop.*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println(String.format("begin: %s", now()));
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] params = point.getArgs();
        System.out.println(Arrays.toString(params));
        point.proceed();
        System.out.println(String.format("after: %s", now()));
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("time before");
    }

    @After("pointcut()")
    public void after() {
        System.out.println("time after");
    }

    private String now() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

}
