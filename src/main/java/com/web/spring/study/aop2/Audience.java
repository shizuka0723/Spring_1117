package com.web.spring.study.aop2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {
//    @Before(value = "execution (* com.web.spring.study.aop2.Actor.show(..))")
//    public void before(JoinPoint jo) throws Throwable{
//        System.out.println("AOP Before攔截");
//        System.out.println(jo.getTarget().getClass().getName()+" , "+jo.getSignature().getName());
//        System.out.println("找位子");
//        System.out.println("關手機");
//    }
//    
//    @After(value = "execution (* com.web.spring.study.aop2.Actor.show(..))")
//    public void after(JoinPoint jo) throws Throwable{
//        System.out.println("AOP After攔截");
//        System.out.println(jo.getTarget().getClass().getName()+" , "+jo.getSignature().getName());
//        System.out.println("拍手");
//    }
    @Pointcut(value = "execution (* com.web.spring.study.aop2.Actor.show(..))")
    public void show(){}
    
    @Around("show()")
    public void watchShow(ProceedingJoinPoint jp){
        try {
            System.out.println("找到座位");
            System.out.println("手機靜音");
            jp.proceed();
            System.out.println("Yes~拍手鼓掌");
        } catch (Throwable e) {
            System.out.println("\u001B[31m太爛了 "+e.getMessage()+" !退票");
        }
    }
}
