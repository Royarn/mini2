package com.royarn.mini.service.impl;

import com.royarn.mini.service.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-16
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private Jedis jedis;

    @Override
    public void setString(String key, String value) {
        jedis.set(key, value);
    }

    @Override
    public void msetString(String... values) {
        jedis.mset(values);
    }

    @Override
    public String getString(String key) {
        return jedis.get(key);
    }

    @Override
    public List<String> mgetString(String key) {
        return jedis.mget(key);
    }

    @Override
    public void hsetObj(String key, Map map) {
        this.hmsetObj(key, map);
    }

    @Override
    public void hmsetObj(String key, Map map) {
        jedis.hmset(key, map);
    }

    @Override
    public String hgetObj(String key, String field) {
        return jedis.hget(key, field);
    }

    @Override
    public List<String> hmgetObj(String key, String... fields) {
        return jedis.hmget(key, fields);
    }

    @Override
    public List<String> lrange(String key, long start, long end) {
        return jedis.lrange(key, start, end);
    }

    @Override
    public void rpush(String key, String... eles) {
        jedis.rpush(key, eles);
    }

    @Override
    public String rpop(String key) {
        return jedis.rpop(key);
    }

    @Override
    public String lpop(String key) {
        return jedis.lpop(key);
    }

    @Override
    public void sadd(String key, String... eles) {
        jedis.sadd(key, eles);
    }

    @Override
    public Set<String> semembers(String key) {
        return jedis.smembers(key);
    }

    @Override
    public boolean sismember(String key, String member) {
        return jedis.sismember(key, member);
    }

    @Override
    public long scard(String key) {
        return jedis.scard(key);
    }

    @Override
    public String spop(String key) {
        return jedis.spop(key);
    }

    @Override
    public void zadd(String key, int score, String member) {
        jedis.zadd(key, score, member);
    }

    @Override
    public Set<String> zrange(String key, long start, int stop) {
        return jedis.zrange(key, start, start);
    }

    @Override
    public long zcard(String key) {
        return jedis.zcard(key);
    }

    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        return jedis.zrangeByScore(key, min, max);
    }

    @Override
    public long zremove(String key, String member) {
        return jedis.zrem(key, member);
    }
}
