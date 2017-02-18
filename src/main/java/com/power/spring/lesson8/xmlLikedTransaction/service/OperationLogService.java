package com.power.spring.lesson8.xmlLikedTransaction.service;

import com.power.spring.lesson8.annotationTransaction.dao.OperationLogMapper;
import com.power.spring.lesson8.annotationTransaction.dao.UserInfoMapper;
import com.power.spring.lesson8.annotationTransaction.model.OperationLog;
import com.power.spring.lesson8.annotationTransaction.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public void logOperation(Integer userId,String operation){
        UserInfo userInfo = userDao.selectByPrimaryKey(userId);
        OperationLog opLog = new OperationLog();
        opLog.setOperation(operation);
        opLog.setOperator(userInfo.getUsername());
        opLog.setOpId(userId);
        opLog.setOpTime(new Date());
        logDao.insert(opLog);
    }
}
