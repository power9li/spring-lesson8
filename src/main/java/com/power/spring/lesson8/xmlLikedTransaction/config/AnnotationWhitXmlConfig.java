package com.power.spring.lesson8.xmlLikedTransaction.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.*;

import java.util.HashMap;

/**
 * Created by shenli on 2017/2/18.
 */
@Configuration
public class AnnotationWhitXmlConfig {

    @Bean
    public AspectJExpressionPointcutAdvisor aspectJExpressionPointcutAdvisor(){
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.power.spring.lesson8.xmlLikedTransaction.service.*.*(..)) ");
        advisor.setAdvice(transactionInterceptor());
        return advisor;
    }

    @Bean
    public NameMatchTransactionAttributeSource nameMatchTransactionAttributeSource() {
        NameMatchTransactionAttributeSource attributeSource = new NameMatchTransactionAttributeSource();

        //Read Transaction Rule
        RuleBasedTransactionAttribute readTa = new RuleBasedTransactionAttribute();
        readTa.setTimeout(5);
        readTa.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        readTa.setReadOnly(Boolean.TRUE);
        readTa.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        //Write Transaction Rule
        RuleBasedTransactionAttribute writeTa = new RuleBasedTransactionAttribute();
        writeTa.setTimeout(1);
        writeTa.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        writeTa.setReadOnly(Boolean.FALSE);
        writeTa.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);

        //Required_New Transaction Rule
        RuleBasedTransactionAttribute requiresNewTa = new RuleBasedTransactionAttribute();
        requiresNewTa.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        requiresNewTa.setTimeout(2);

        attributeSource.setNameMap(new HashMap<String,TransactionAttribute>(){{
            put("read*",  readTa);
            put("find*",  readTa);
            put("query*", readTa);
            put("load*",  readTa);

            put("log*",   requiresNewTa);

            put("update*", writeTa);
            put("save*",   writeTa);
            put("delete*", writeTa);
            put("insert*", writeTa);
            put("add*",    writeTa);
        }});
        return attributeSource;
    }

    @Bean
    public TransactionInterceptor transactionInterceptor() {
        TransactionInterceptor interceptor = new TransactionInterceptor();
        interceptor.setTransactionAttributeSource(nameMatchTransactionAttributeSource());
        return interceptor;
    }


}
