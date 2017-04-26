package com.cy.aop;

import org.springframework.stereotype.Service;

/**
 * Created by zxj on 2017/4/26.
 */
@Service("doSomethingService")
public class DoSomethingServiceImpl implements DoSomethingService {

    public void doSomething(String command) {
        System.out.println("doSomething...");
    }

}
