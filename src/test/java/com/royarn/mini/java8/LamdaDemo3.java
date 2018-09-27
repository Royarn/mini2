package com.royarn.mini.java8;

import com.alibaba.fastjson.JSON;
import com.royarn.mini.util.Collectionutil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LamdaDemo3 {

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("green", 150));
        apples.add(new Apple("yellow", 160));
        apples.add(new Apple("grey", 170));

        //lamda构造函数实现
        List<Apple> result = FilterApple.filters(apples, (Apple apple) -> apple.getColor().equals("green"));
        List<Apple> result1 = FilterApple.filters(apples, (Apple apple) -> apple.getWeight() > 150);

        //lamda + stream 构建高效数据处理集合 --提供并行处理数据能力
        List<Apple> result2 = apples.stream().filter((Apple apple) -> apple.getWeight() > 150)
                .collect(Collectors.toList());

        //map native
        List<String> result3 = FilterApple.mapNative(apples, (Apple apple) -> apple.getColor() + " || " + apple.getWeight() + " ");
        System.out.println(JSON.toJSON(result3));
    }
}

class Apple {

    private String color;
    private double weight;

    public Apple(String color, double weight) {
        this.color = color;
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

interface Predict<T> {
    boolean test(T t);
}

class FilterApple {

    public static <T> List<T> filters(List<T> list, Predict<T> predict) {
        List<T> result = new ArrayList<>();
        if (Collectionutil.isEmpty(list))
            return null;
        for (T t : list) {
            if (predict.test(t))
                result.add(t);
        }
        return result;
    }

    /**
     * map native  input the original value, and map the result
     * @param list
     * @param function
     * @param <T>
     * @param <R>
     */
    public static <T,R> List<R> mapNative(List<T> list, Function<T, R> function) {
        if (Collectionutil.isEmpty(list)) return null;
        List<R> result = new ArrayList<>();
        for (T t : list) {
            R r = function.apply(t);
            result.add(r);
        }
        return result;
    }
}