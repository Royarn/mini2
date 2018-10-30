package com.royarn.mini.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 11:06
 */
@Configuration
@EnableTransactionManagement
public class DBTXCfg extends DataSourceTransactionManagerAutoConfiguration {
    private Logger logger = LoggerFactory.getLogger(DBTXCfg.class);

    @Resource(name = "routingDataSource")
    private DataSource routingDataSource;

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager() {
        logger.info("数据库事务初始化 ... ");
        return new DataSourceTransactionManager(routingDataSource);
    }
}