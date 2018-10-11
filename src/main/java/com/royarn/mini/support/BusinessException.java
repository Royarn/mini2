package com.royarn.mini.support;

public class BusinessException extends RuntimeException {

    /**
     * 异常码
     */
    private int code;

    /**
     * 异常信息
     */
    private String message;

    public BusinessException(String message) {
        super(message);
    }
}