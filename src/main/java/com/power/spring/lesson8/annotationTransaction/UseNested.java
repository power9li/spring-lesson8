package com.power.spring.lesson8.annotationTransaction;

import com.power.spring.lesson8.annotationTransaction.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class UseNested {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.power.spring.lesson8.annotationTransaction");
        OrderService orderService = context.getBean(OrderService.class);
        orderService.bigLogic();
    }
}
