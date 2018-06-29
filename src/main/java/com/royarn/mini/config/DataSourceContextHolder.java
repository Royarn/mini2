package com.royarn.mini.config;

/**
 * @author lizq
 * @Description: 数据源动态切换
 * @date 2018/6/28 10:00
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal() {
        return local;
    }

    public static void read() {
        local.set(DBEnums.read.getType());
    }

    public static void write() {
        local.set(DBEnums.write.getType());
    }

    public static String getJDBCType() {
        return local.get();
    }
}
