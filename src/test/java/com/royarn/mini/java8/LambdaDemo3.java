package com.royarn.mini.java8;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 在lambda表达式中
 *  jdk提供了三种函数式接口，都是为了处理集合
 *      针对集合中的每条数据做处理
 */
public class LambdaDemo3 {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("lizhiqiang", 176, 134));
        people.add(new Person("yangchunhong", 150, 150));
        people.add(new Person("royarn", 174, 130));
        people.add(new Person("ych", 164, 154));

        //使用Predicate进行筛选数据
        List<Person> byPredicate = filterByPredicate(people, (person -> person.getHeight() > 160));

        //使用Consumer进行元素审查
        filterByConsumer(people, person -> System.out.println(JSON.toJSON(person)));

        //使用Function实现map函数
        List<String> result = map(people, person -> person.getName());

        //java8实现数据过滤-排序-map
        List<String> result1 = people.stream()
                .filter(person -> person.getWeight() > 120)
                .sorted(Comparator.comparing(Person::getHeight))
                .map(person -> person.getName())
                .collect(Collectors.toList());

        //基于多核CPU架构的并行处理数据
        List<String> result2 = people.parallelStream()
                .filter(person -> person.getWeight() > 120)
                .sorted(Comparator.comparing(Person::getHeight))
                .map(person -> person.getName())
                .collect(Collectors.toList());
        System.out.println(JSON.toJSON(result2));
    }

    //使用Predicate进行筛选数据
    public static <T> List<T> filterByPredicate(List<T> list, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t))
                result.add(t);
        }
        return result;
    }

    //使用Consumer进行元素审查
    public static <T> void filterByConsumer(List<T> list, Consumer<T> consumer) {
        List<T> result = new ArrayList<>();
        for (T t : list) {
            consumer.accept(t);
        }
    }

    //使用Function实现map函数
    public static <T, R> List<R> map(List<T> list, Function<T, R> function) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(function.apply(t));
        }
        return result;
    }
}

class Person {

    private String name;
    private Integer height;
    private Integer weight;

    public Person(String name, Integer height, Integer weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}