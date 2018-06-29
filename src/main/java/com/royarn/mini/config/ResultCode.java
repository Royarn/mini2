package com.royarn.mini.config;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 14:32
 */
public enum ResultCode {

    suc(200, "SUCCESS"), fail(500, "FAIL");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
