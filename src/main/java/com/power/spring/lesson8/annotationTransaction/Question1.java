package com.power.spring.lesson8.annotationTransaction;

import com.power.spring.lesson8.annotationTransaction.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

/**
 * Created by shenli on 2017/2/9.
 */
public class Question1 {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.power.spring.lesson8");
        UserService us = context.getBean(UserService.class);
//        us.addUserAgeById(1);
        us.selectUserJdbc(1);
    }
}
