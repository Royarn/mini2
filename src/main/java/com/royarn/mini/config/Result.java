package com.royarn.mini.config;

import java.util.HashMap;
import java.util.Map;
import static com.royarn.mini.config.ResultCode.*;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/6/28 14:38
 */
public class Result {

    /**
     * 状态码
     */
    private int code;
    /**
     * 描述信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Map<String, Object> data = new HashMap<>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * 成功
     * @return
     */
    public static Result ok() {
        Result result = new Result();
        result.setCode(suc.getCode());
        result.setMessage("成功");
        return result;
    }

    /**
     * 失败
     * @param message
     * @return
     */
    public static Result error(String message) {
        Result result = new Result();
        result.setCode(fail.getCode());
        result.setMessage(message);
        return result;
    }

    /**
     * 返回信息追加
     * @param key
     * @param data
     * @return
     */
    public Result property(String key, Object data) {
        this.data.put(key, data);
        return this;
    }
}