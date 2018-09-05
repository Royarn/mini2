package com.royarn.mini.java8;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LamdaDemo {

    public static void main(String[] args) {
        //返回可执行的文件列表
        File[] files = new File(".").listFiles(File::canWrite);
        //new HelpClass().methedParam(ParamClass::process);
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("yellow", 130));
        apples.add(new Apple("green", 160));
        apples.add(new Apple("grey", 170));

        //需要定义函数实现
        List<Apple> result = FilterApple.filters(apples, Apple::isHeavyApple);
        List<Apple> result1 = FilterApple.filters(apples, Apple::isGreenApple);
        //lamda表达式实现 --即无需定义
        List<Apple> result2 = FilterApple.filters(apples, (Apple apple) -> apple.getWeight() > 150);
        List<Apple> result3 = FilterApple.filters(apples, (Apple apple) -> "green".equals(apple.getColor()));

        //lamda实现通用类库 --无需定义实现
        Collection<Apple> collection1 = FilterApple.generalFilters(apples,
                (Apple apple) -> "green".equals(apple.getColor()));
        System.out.println(JSON.toJSON(collection1));
//        Collection<Apple> collection2 = FilterApple.generalFilters(apples,
////                (Apple apple) -> apple.getWeight() > 150);

        //lamda + stream构建集合并行处理能力 + 数据输出
        List<Apple> result4 = apples.stream().filter((Apple apple) -> apple.getColor().equals("green"))
                .collect(Collectors.toList());
    }
}

interface Predict<T> {
    boolean test(T t);
}

class FilterApple {

    public static List<Apple> filterByColor(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        if (apples == null || apples.size() == 0) return null;
        for (Apple apple : apples) {
            if (apple.getColor().equals("Green")) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterByWeight(List<Apple> apples) {
        List<Apple> result = new ArrayList<>();
        if (apples == null || apples.size() == 0) return null;
        for (Apple apple : apples) {
            if (apple.getWeight() > 150) {
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filters(List<Apple> apples, Predict<Apple> predict) {
        List<Apple> result = new ArrayList<>();
        if (apples == null || apples.size() == 0) return null;
        for (Apple apple : apples) {
            if (predict.test(apple))
                result.add(apple);
        }
        return result;
    }

    public static <T> Collection<T> generalFilters(Collection<T> collection, Predict<T> predict) {
        Collection<T> result = Collections.EMPTY_LIST;
        if (collection == null || collection.size() == 0)
            return collection;
        for (T t : collection) {
            if (predict.test(t))
                result.add(t);
        }
        return result;
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

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    public static boolean isGreenApple(Apple apple) {
        return "green".equals(apple.getColor());
    }
}

interface AppleFormater<T> {
    String format(T t);
}