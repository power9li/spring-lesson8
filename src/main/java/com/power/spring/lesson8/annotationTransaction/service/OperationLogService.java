package com.power.spring.lesson8.annotationTransaction.service;

import com.power.spring.lesson8.annotationTransaction.dao.OperationLogMapper;
import com.power.spring.lesson8.annotationTransaction.dao.UserInfoMapper;
import com.power.spring.lesson8.annotationTransaction.model.OperationLog;
import com.power.spring.lesson8.annotationTransaction.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by shenli on 2017/2/18.
 */
@Component
public class OperationLogService {

    @Autowired
    private OperationLogMapper logDao;

    @Autowired
    private UserInfoMapper userDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logOperation(Integer userId,String operation){
        UserInfo userInfo = userDao.selectByPrimaryKey(userId);
        OperationLog opLog = new OperationLog();
        opLog.setOperation(operation);
        opLog.setOperator(userInfo.getUsername());
        opLog.setOpId(userId);
        opLog.setOpTime(new Date());
        logDao.insert(opLog);
        if (true) {
//            throw new RuntimeException("logOperation.RuntimeException");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void operationLogic1(){
        System.out.println("OperationLogService.operationLogic1");
        OperationLog opLog = new OperationLog();
        opLog.setOperation("operation1");
        opLog.setOperator("张三");
        opLog.setOpId(1);
        opLog.setOpTime(new Date());
        logDao.insert(opLog);
        if (true) {
            throw new RuntimeException("operationLogic1.RuntimeException");
        }
    }

    @Transactional(propagation = Propagation.NESTED)
    public void operationLogic2(){
        System.out.println("OperationLogService.operationLogic2");
        OperationLog opLog = new OperationLog();
        opLog.setOperation("operation2");
        opLog.setOperator("李四");
        opLog.setOpId(1);
        opLog.setOpTime(new Date());
        logDao.insert(opLog);

    }
}
