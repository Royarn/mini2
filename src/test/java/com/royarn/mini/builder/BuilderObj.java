package com.royarn.mini.builder;

import com.alibaba.fastjson.JSON;

public class BuilderObj {

    private String key;
    private String value;

    public static void main(String[] args) {
        BuilderObj obj = new HelperClass()
                .setAttr1("name")
                .setAttr2("royarn")
                .bulidObj();
        System.out.println(JSON.toJSON(obj));
    }

    public BuilderObj(String key, String value) {
        this.key = key;
        this.value = value;
    }

        public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public static class HelperClass {
        private String attr1;
        private String attr2;

        public String getAttr1() {
            return attr1;
        }

        public HelperClass setAttr1(String attr1) {
            this.attr1 = attr1;
            return this;
        }

        public String getAttr2() {
            return attr2;
        }

        public HelperClass setAttr2(String attr2) {
            this.attr2 = attr2;
            return this;
        }

        public BuilderObj bulidObj() {
            return new BuilderObj(attr1, attr2);
        }
    }
}