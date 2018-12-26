package com.royarn.mini.config;


import com.royarn.mini.entity.Group;

import static com.royarn.mini.config.ResultCode.fail;
import static com.royarn.mini.config.ResultCode.suc;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-25
 */
public class SpecialResult {

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
    private Group data;

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

    public Group getData() {
        return data;
    }

    public void setData(Group data) {
        this.data = data;
    }

    /**
     * 成功
     * @return
     */
    public static SpecialResult ok() {
        SpecialResult result = new SpecialResult();
        result.setCode(suc.getCode());
        result.setMessage("成功");
        return result;
    }

    /**
     * 失败
     * @param message
     * @return
     */
    public static SpecialResult error(String message) {
        SpecialResult result = new SpecialResult();
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
    public SpecialResult property(Object data) {
        this.data = (Group) data;
        return this;
    }
}
