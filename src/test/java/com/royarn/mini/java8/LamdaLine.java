package com.royarn.mini.java8;

import com.alibaba.fastjson.JSON;

import java.util.function.Function;

public class LamdaLine {

    public static void main(String[] args) {
        Function<String, String> header = Letter::addHeader;
        header.apply("My dear");
        Function<String, String> transform
                = header.andThen(Letter::checkSpell)
                .andThen(Letter::addFooter);
        System.out.println(JSON.toJSONString(transform));
    }
}

/**
 * 组装流水线
 *  包含信开头 检查 尾部
 */
class Letter {

    public static String addHeader(String text) {
       return "From royarn" + text;
    }

    public static String checkSpell(String text) {
        return text.replaceAll("laba", "lambda");
    }

    public static String addFooter(String text) {
        return text + " To YCH";
    }
}