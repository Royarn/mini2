package com.royarn.mini.config;

/**
 * @author lizq
 * @Description: 操作类型
 * @date 2018/6/28 9:43
 */
public enum  DBEnums {
    read("read", "从库"), write("write", "主库");
    private String type;
    private String name;

    DBEnums(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}