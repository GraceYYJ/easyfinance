package com.gracyya.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by ximing on 2018/6/3.
 */
public class BeanUtil {
    //private static AnnotationConfigApplicationContext ctx;
    //static {
    //    ctx = new AnnotationConfigApplicationContext();
    //    ctx.scan("com.gracyya.service.impl,com.gracyya.dao,com.gracyya.spider.pipeline");
    //    ctx.refresh();
    //}

    //public static <T> T getBean(Class<T> clazz) {
    //    return ctx.getBean(clazz);
    //}

    private static ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/spring-mvc.xml");

    public static <T> T getBean(Class<T> clazz) {
        return ac.getBean(clazz);
    }
}
