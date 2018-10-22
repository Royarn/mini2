package com.royarn.mini.java8.factory;

import com.royarn.mini.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-22
 */
public class CarFactory {

    private static Map<String, Supplier<Car>> supplierMap = new HashMap<>();

    static {
        supplierMap.put("mini", Mini::new);
        supplierMap.put("porsche", Porsche::new);
    }

    public static Car carInstance(String name) {
        if (StringUtils.isEmpty(name)) {return null;}
        return supplierMap.get(name).get();
    }

    public static void main(String[] args) {
        CarFactory.carInstance("mini");
    }
}