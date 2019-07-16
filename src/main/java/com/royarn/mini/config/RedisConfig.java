package com.royarn.mini.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-16
 */
@Configuration
public class RedisConfig {

    @Value("192.168.12.160")
    private String host;

    @Value("6379")
    private String port;

    @Bean
    public JedisPool jedisPool() {
        JedisPool jedisPool = new JedisPool(new GenericObjectPoolConfig(), host,
                Protocol.DEFAULT_PORT,
                Protocol.DEFAULT_TIMEOUT, "123456",
                Protocol.DEFAULT_DATABASE, null);
        return jedisPool;
    }

    @Bean
    public Jedis jedis(JedisPool jedisPool) {
        return jedisPool.getResource();
    }
}
