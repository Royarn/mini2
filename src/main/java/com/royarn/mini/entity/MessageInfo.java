package com.royarn.mini.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lizq
 * @Description: ${todo}
 * @date 2018/8/2 11:30
 */
public class MessageInfo {

    private Integer id;
    private String message;

    @JsonCreator
    public MessageInfo(@JsonProperty("id") Integer id,
                       @JsonProperty("message") String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "{id : " + id + ", " + "message: " + message + "}";
    }
}
