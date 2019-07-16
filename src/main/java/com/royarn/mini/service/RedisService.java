package com.royarn.mini.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author lizhiqiang
 * @date 2019-07-16
 */
public interface RedisService {

    /**
     * String api
     */
    void setString(String key, String value);
    void msetString(String... values);
    String getString(String key);
    List<String> mgetString(String key);

    /**
     * hash api
     */
    void hsetObj(String key, Map map);
    void hmsetObj(String key, Map map);
    String hgetObj(String key, String field);
    List<String> hmgetObj(String key, String... fields);

    /**
     * list api
     */
    void rpush(String key, String... eles);
    String rpop(String key);
    String lpop(String key);
    List<String> lrange(String key, long start, long end);

    /**
     * set api
     */
    void sadd(String key, String... eles);
    Set<String> semembers(String key);
    boolean sismember(String key, String member);
    long scard(String key);
    String spop(String key);

    /**
     * zset api
     */
    void zadd(String key, int score, String member);
    Set<String> zrange(String key, long start, int stop);
    long zcard(String key);
    Set<String> zrangeByScore(String key, double min, double max);
    long zremove(String key, String member);
}
