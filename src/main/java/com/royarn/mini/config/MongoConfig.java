package com.royarn.mini.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/7/12 12:50
 */
@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient("192.168.12.78:9090");
    }

    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), "np_config");
    }
}
