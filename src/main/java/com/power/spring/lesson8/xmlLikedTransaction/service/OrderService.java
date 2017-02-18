package com.power.spring.lesson8.xmlLikedTransaction.service;

import com.power.spring.lesson8.annotationTransaction.dao.OrderInfoMapper;
import com.power.spring.lesson8.annotationTransaction.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

/**
 * Created by shenli on 2017/2/11.
 */
@Component
public class OrderService {

    @Autowired
    private OrderInfoMapper orderDao;

    @Autowired
    private UserService userService;

    @Autowired
    private OperationLogService logService;

    public OrderInfo findById(Integer id) {
        return orderDao.selectByPrimaryKey(id);
    }

    public void updateOrderPrice(Integer orderId, Integer added, Integer opId) throws SQLException {
        OrderInfo orderInfo = orderDao.selectByPrimaryKey(orderId);
        orderInfo.setPrice(orderInfo.getPrice() + added);
        orderDao.updateByPrimaryKey(orderInfo);
        try {
            logService.logOperation(opId, "updateOrderPrice");
        } catch (RuntimeException e) {
            System.out.println("日志记录出错了");
        }
    }

}
