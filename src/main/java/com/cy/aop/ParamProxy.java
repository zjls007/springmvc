package com.cy.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by zxj on 2017/4/26.
 * 这里的 {@link MethodInterceptor} 不是cglib的，cglib也有同名的
 */
@Component
@Order(3)
public class ParamProxy implements MethodInterceptor {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println(methodInvocation.getMethod().getName());
        System.out.println(Arrays.toString(methodInvocation.getArguments()));
        Object result = methodInvocation.proceed();
        System.out.println(result);
        return result;
    }

}
