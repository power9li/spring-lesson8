package com.power.spring.lesson8.annotationTransaction;

import com.power.spring.lesson8.annotationTransaction.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class UseRequiresNew {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.power.spring.lesson8");

        OrderService orderService = context.getBean(OrderService.class);
        int orderId = 1;
        int userId = 1;
        int added = 10;
        orderService.updateOrderPrice(orderId,added,userId);

    }
}
