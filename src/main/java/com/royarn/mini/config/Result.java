package com.royarn.mini.config;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 14:38
 */
public class Result {

    private int code;
    private String message;
    private Object info;

    public Result(int code, String message, Object info) {
        this.code = code;
        this.message = message;
        this.info = info;
    }
}