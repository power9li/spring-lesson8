package com.power.spring.lesson8.xmlLikedTransaction.service;

import com.power.spring.lesson8.annotationTransaction.dao.UserInfoMapper;
import com.power.spring.lesson8.annotationTransaction.model.UserInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by shenli on 2017/2/10.
 */
@Component
public class UserService {

    @Autowired
    private UserInfoMapper userDao;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void addUserAgeById(Integer id) throws SQLException {
        System.out.println("UserService.displayUserById");
        UserInfo userInfo = userDao.selectByPrimaryKey(id);
        addUserAge(userInfo, 1);
        System.out.println("userInfo = " + userInfo);
    }

    public void addUserAge(UserInfo user,Integer add) {
        user.setAge(user.getAge() + add);
        userDao.updateByPrimaryKey(user);
    }

    public void insertUser(UserInfoMapper userDao){
        System.out.println("UserService.insertUser");
        UserInfo ui = new UserInfo();
        ui.setAge(30);
        ui.setUsername("Pop");
        ui.setPassword("PopPass");
        userDao.insert(ui);
    }

    public void selectUserJdbc(Integer id) throws SQLException {
        System.out.println("UserService.selectUserJdbc");
        SqlSession sqlSession = SqlSessionUtils.getSqlSession(sqlSessionFactory);
        Connection connection = sqlSession.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from t_users where id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            UserInfo ui = new UserInfo();
            ui.setId(resultSet.getInt("id"));
            ui.setAge(resultSet.getInt("age"));
            ui.setUsername(resultSet.getString("username"));
            ui.setPassword(resultSet.getString("password"));
            System.out.println("selectUserJdbc ui = " + ui);
            return;
        }
        System.out.println("null");
    }

}
