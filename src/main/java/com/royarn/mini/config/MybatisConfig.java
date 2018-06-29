package com.royarn.mini.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 15:08
 */
@Configuration
@EnableTransactionManagement(order = 10)
public class MybatisConfig {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Value("${mybatis.mapperLocations}")
    private String mapperLocation;
    @Value("${mybatis.typeAliasesPackage}")
    private String pojoPackage;
    @Resource(name = "writeDB")
    private DruidDataSource writeDBSource;
    @Resource(name = "readDB")
    private DruidDataSource readDBSource;

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(routingDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocation));
        sqlSessionFactoryBean.setTypeAliasesPackage(pojoPackage);
        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "routingDataSource")
    public AbstractRoutingDataSource routingDataSource() {
        RouteDB proxy = new RouteDB();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBEnums.write.getType(), writeDBSource);
        targetDataSources.put(DBEnums.read.getType(), readDBSource);
        proxy.setDefaultTargetDataSource(writeDBSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}
