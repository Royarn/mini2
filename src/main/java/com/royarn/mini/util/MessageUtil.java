package com.royarn.mini.util;

/**
 * Description:
 *
 * @author dell
 * @date 2018-12-21
 */
public class MessageUtil {

    private static final String INFO = "成功";
    private String message;
    private String id;

    private MessageUtil(String message, String id) {
        this.message = message;
        this.id = id;
    }

    public static MessageUtil MSG(String id) {
        return new MessageUtil(INFO, id);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
