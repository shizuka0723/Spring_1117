package com.web.spring.study.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/java/com/web/spring/study/beans/beans-config.xml");
        HelloBean helloBean = (HelloBean)ctx.getBean("helloBean");
        System.out.println(helloBean.getName());
        
        Husband husband = ctx.getBean(Husband.class);
        System.out.println(husband);
        System.out.println(husband.getName());
        System.out.println(husband.getWife().getName());
    }
}
