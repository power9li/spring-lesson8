package com.power.spring.lesson8.xmlLikedTransaction.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * Created by shenli on 2017/2/9.
 */
@Component
@PropertySource("classpath:jdbc.properties")
@MapperScan("com.power.spring.lesson8.annotationTransaction.dao")
//@EnableTransactionManagement
@Import(AnnotationWhitXmlConfig.class)
@EnableAspectJAutoProxy
@Configuration
public class SpringConfig {

    private static final Logger LOG = LoggerFactory.getLogger(SpringConfig.class);

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        ds.setUsername(env.getProperty("jdbc.username"));
        ds.setPassword(env.getProperty("jdbc.password"));
        ds.setUrl(env.getProperty("jdbc.url"));
        ///设置空闲和借用的连接的最大总数量，同时可以激活。
        ds.setMaxTotal(60);
        //设置初始大小
        ds.setInitialSize(1);
        //最小空闲连接
        ds.setMinIdle(1);
        //最大空闲连接
        ds.setMaxIdle(16);
        //超时等待时间毫秒
        ds.setMaxWaitMillis(2*10000);
        //只会发现当前连接失效，再创建一个连接供当前查询使用
        ds.setTestOnBorrow(true);
        //removeAbandonedTimeout ：超过时间限制，回收没有用(废弃)的连接（默认为 300秒，调整为180）
        ds.setRemoveAbandonedTimeout(180);
        //removeAbandoned ：超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收（默认为false，调整为true)
        //ds.setRemoveAbandonedOnMaintenance(removeAbandonedOnMaintenance);
        ds.setRemoveAbandonedOnBorrow(true);
        //testWhileIdle
        ds.setTestOnReturn(true);
        //testOnReturn
        ds.setTestOnReturn(true);
        //setRemoveAbandonedOnMaintenance
        ds.setRemoveAbandonedOnMaintenance(true);
        //记录日志
        ds.setLogAbandoned(true);
        //设置自动提交
        ds.setDefaultAutoCommit(true);
        // ds.setEnableAutoCommitOnReturn(true);
        LOG.debug("===debug===");
        LOG.info("完成设置数据库连接池ds的参数！！");
        return ds;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @Autowired
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource){
        SqlSessionFactory factory = null;
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        try {
            factory = sqlSessionFactoryBean.getObject();
            //如果使用编程方式创建Mapper,则要手动addMapper,如果使用MapperScan则不需要
//            factory.getConfiguration().addMapper(UserInfoMapper.class);
//            factory.getConfiguration().addMapper(OrderInfoMapper.class);
            org.apache.ibatis.session.Configuration configuration = factory.getConfiguration();
            Collection<Class<?>> mappers = configuration.getMapperRegistry().getMappers();
            for (Class<?> clazz : mappers) {
                System.out.println(clazz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

//    @Bean
//    @Autowired
//    public UserInfoMapper userInfoMapper(SqlSessionFactory sqlSessionFactory) throws Exception {
//        SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
//        return sessionTemplate.getMapper(UserInfoMapper.class);
//    }

    @Bean
    @Autowired
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory factory) {
        SqlSessionTemplate template = new SqlSessionTemplate(factory);
        return template;
    }

}
