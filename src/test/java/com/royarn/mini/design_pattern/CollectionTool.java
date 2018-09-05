package com.royarn.mini.design_pattern;

import java.util.*;

public class CollectionTool {

    /**
     * 获取Map集合
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K,V> map() {
        return new HashMap<>();
    }

    /**
     * 获取list
     * @param <T>
     * @return
     */
    public static <T> List<T> list() {
        return new ArrayList<>();
    }

    /**
     * 获取set
     * @param <T>
     * @return
     */
    public static <T> Set<T> set() {
        return new HashSet<>();
    }

    /**
     * 获取queue
     * @param <T>
     * @return
     */
    public static <T> Queue<T> queue() {
        return new LinkedList<>();
    }

    public static void main(String[] args) {
        Map<String, String> stringMap = CollectionTool.map();
        stringMap.put("royarn", "lizhiqiang");
        System.out.println(stringMap);
    }
}
