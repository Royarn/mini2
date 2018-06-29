package com.royarn.mini.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author lizq
 * @Description:  数据源实例化
 * @date 2018/6/28 9:42
 */
@Configuration
public class DBCfg {

    private final Logger logger = LoggerFactory.getLogger(DBCfg.class);

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name = "writeDB", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource writeDB() {
        logger.info("主库初始化 ... ");
        return new DruidDataSource();
    }

    @Bean(name = "readDB")
    @ConfigurationProperties(prefix = "spring.readdb")
    public DruidDataSource readDB() {
        logger.info("从库初始化 ... ");
        return new DruidDataSource();
    }
}