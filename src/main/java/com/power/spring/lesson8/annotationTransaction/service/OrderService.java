package com.power.spring.lesson8.annotationTransaction.service;

import com.power.spring.lesson8.annotationTransaction.dao.OrderInfoMapper;
import com.power.spring.lesson8.annotationTransaction.model.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public OrderInfo findById(Integer id) {
        return orderDao.selectByPrimaryKey(id);
    }

    /**
     * 外层事务是REQUIRED,内层事务是REQUIRES_NEW
     * 抛出受检异常,无论内外层,都不会影响事务提交
     * 内层事务抛出运行异常,外层如果不捕获则全部回滚,外层如果捕获,只回滚内层事务
     * 外层事务抛出运行异常,如果是在内从事务执行后,则外层回滚,内层提交
     *
     * 综上所述RequiresNew的事务传播级别
     *
     * @param orderId 订单id
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateOrderPrice(Integer orderId, Integer added, Integer opId) throws SQLException {
        OrderInfo orderInfo = orderDao.selectByPrimaryKey(orderId);
        orderInfo.setPrice(orderInfo.getPrice() + added);
        orderDao.updateByPrimaryKey(orderInfo);
        try {
            logService.logOperation(opId, "updateOrderPrice");
        } catch (RuntimeException e) {
            System.out.println("日志记录出错了");
        }
        if (true) {
            throw new RuntimeException("updateOrderPrice.RuntimeException");
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void bigLogic(){
        System.out.println("OrderService.bigLogic");
        OrderInfo orderInfo = orderDao.selectByPrimaryKey(1);
        orderInfo.setPrice(orderInfo.getPrice() + 1);
        orderDao.updateByPrimaryKey(orderInfo);
        try {
            logService.operationLogic1();
        } catch (Exception e) {
            System.out.println("e = " + e);
            logService.operationLogic2();
        }
    }

}
