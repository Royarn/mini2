package com.royarn.mini.java8;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LamdaDemo2 {

    public static void process(Runnable runnable) {
        runnable.run();
    }

    public static void main(String[] args) {
        Runnable runnable1 = () -> System.out.println("Thread-1 print");
        Runnable runnable2 = () -> System.out.println("Thread-2 print");
//        process(runnable1);
//        process(runnable2);
        List<String> strs = Arrays.asList("a", "x", "d", "r");
        //strs.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        strs.sort(String::compareToIgnoreCase);

        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight).reversed();
        //函数式编程思维 --即给出筛选逻辑
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("yellow", 150));
        apples.add(new Apple("red", 120));
        apples.add(new Apple("grey", 180));
        apples.add(new Apple("black", 180));
        apples.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));

        //谓词复合 --构造如陈述式语法
        Function<Integer, Integer> fx1 = (x) -> x + 1;
        Function<Integer, Integer> fx2 = (x) -> x * 2;
        Function<Integer, Integer> fx3 = fx1.andThen(fx2);
        int result = fx3.apply(2);
        System.out.println(result);
    }

}

@FunctionalInterface
interface Supplier<T> {
    T get();
}