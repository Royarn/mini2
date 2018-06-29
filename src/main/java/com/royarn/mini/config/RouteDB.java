package com.royarn.mini.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 10:41
 */
public class RouteDB extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String key = DataSourceContextHolder.getJDBCType();
        if (key.equals(DBEnums.write.getType())) {
            return DBEnums.write.getType();
        } else {
            /**
             * 如果我们不是用MySQL Router，而是配置了多个从库的话，这里应该做一些处理，写个轮询、随机访问之类的规则，
             * 不过负载的事由MySQL Router做了，这里就不用制定规则了。
             */
            return DBEnums.read.getType();
        }
    }
}
