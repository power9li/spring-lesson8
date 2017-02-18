package com.power.spring.lesson8.xmlLikedTransaction;

import com.power.spring.lesson8.xmlLikedTransaction.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

/**
 * Created by shenli on 2017/2/18.
 */
public class TestXmlLikedTransaction {

    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.power.spring.lesson8.xmlLikedTransaction");
        OrderService orderService = context.getBean(OrderService.class);
        orderService.updateOrderPrice(1, 5, 1);
    }
}
