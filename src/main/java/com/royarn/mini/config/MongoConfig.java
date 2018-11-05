package com.royarn.mini.config;

import com.mongodb.MongoClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 12:50
 */
@Configuration
@ConfigurationProperties(prefix = "mongodb")
public class MongoConfig {

    private String host;
    private int port;
    private String dataSource;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(host + ":" + port);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), dataSource);
    }
}