package com.zgg.web.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;

@Configuration
public class MybatisConfig {

    @Autowired
    private Environment env;
	
    //platformTransactionManager 为springboot默认初始化好的对象，，无需定义
    @Bean(name = "transactionInterceptorCommon")
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager platformTransactionManager) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        // 事物管理器
        transactionInterceptor.setTransactionManager(platformTransactionManager);
        Properties transactionAttributes = new Properties();

        transactionAttributes.setProperty("insert*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("delete*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("remove*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("save*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("add*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("create*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("put*", "PROPAGATION_REQUIRED,-Throwable");
        transactionAttributes.setProperty("merge*", "PROPAGATION_REQUIRED,-Throwable");
        
        transactionAttributes.setProperty("get*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("select*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("find*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("count*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");
        transactionAttributes.setProperty("list*", "PROPAGATION_SUPPORTS,-Throwable,readOnly");

        transactionInterceptor.setTransactionAttributes(transactionAttributes);
        return transactionInterceptor;
    }
    //代理到Service的Bean
    @Bean
    public BeanNameAutoProxyCreator transactionAutoProxy() {
        BeanNameAutoProxyCreator transactionAutoProxy = new BeanNameAutoProxyCreator();
        transactionAutoProxy.setProxyTargetClass(true);
        transactionAutoProxy.setBeanNames("*Service");
        transactionAutoProxy.setInterceptorNames("transactionInterceptorCommon");
        return transactionAutoProxy;
    }
    
}
