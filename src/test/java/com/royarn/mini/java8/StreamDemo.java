package com.royarn.mini.java8;

import com.alibaba.fastjson.JSON;

import java.util.*;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

/**
 * 在stream API中
 *  流式计算分为三步骤
 *      1.数据源--用于转化为流
 *      2.中间操作链 --对应于流水线 处理后的数据仍然是流
 *      3.终端操作 --将流数据生成结果
 *
 *    转换流：
 *      stream()  parallStream()
 *    常用中间操作链包括：
 *      filter() map() reduce() distinct() sorted() limit()
 *    终端操作：
 *      count() collect() forEach()
 *          包含短路匹配：
 *              anyMatch()  allMatch() noneMatch()
 *              短路查找：
 *              findAny()   findAll()
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();
        menus.add(new Menu("pork", false, 800, Menu.Type.MEAT));
        menus.add(new Menu("beef", false, 530, Menu.Type.MEAT));
        menus.add(new Menu("chicken", false, 400, Menu.Type.MEAT));
        menus.add(new Menu("french fries", true, 530, Menu.Type.OTHER));
        menus.add(new Menu("rice", true, 350, Menu.Type.OTHER));
        menus.add(new Menu("reason fruit", true, 120, Menu.Type.OTHER));
        menus.add(new Menu("pizza", true, 1550, Menu.Type.OTHER));
        menus.add(new Menu("prawns", false, 300, Menu.Type.FISH));
        menus.add(new Menu("salmon", false, 450, Menu.Type.FISH));

        //构建流水线处理 --函数式编程
        menus.parallelStream()
                .filter(menu -> menu.getCalories() > 300)
                .map(Menu::getName)
                .limit(3)
                .collect(toList());

        //流式处理 --distinct
       Arrays.asList(1, 2, 2, 5, 5, 8, 8, 6)
                .stream()
                .filter(integer -> integer % 2 == 0)
                .distinct()
                .collect(toList());

       //筛选数据
        menus.stream()
                .filter(menu -> menu.getType() == Menu.Type.MEAT)
                .limit(2)
                .map(menu -> menu.getName())
                .map(String::length)
                .collect(toList());

        //map模型
        List<String> words = Arrays.asList("blink", "flume", "ych", "chaos knight");
        words.stream()
                .map(String::length)
                .collect(toList());

        //map模型 --flatMap
        Arrays.asList("Hello", "World")
                .stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        //map模型 --求数的平方
        Arrays.asList(1, 3, 5, 7)
                .stream()
                .map(integer -> integer * integer)
                .distinct()
                .collect(toList());

        //map模型 --构建笛卡尔积
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        number1.stream()
                .flatMap(integer ->
                        number2.stream()
                                .filter(integer1 -> (integer + integer1) % 3 == 0)
                .map(integer1 -> new int[] {integer, integer1}))
                .collect(toList());

        //map模型 --anyMatch匹配
        menus.stream()
                .anyMatch(menu -> menu.getType().equals(Menu.Type.MEAT));

        //map模型  --allMatch匹配
        menus.stream()
                .allMatch(menu -> menu.getCalories() < 600);

        //map模型  --noneMatch匹配
        menus.stream()
                .noneMatch(menu -> menu.getCalories() > 300);

        //map模型  --findAny
        menus.stream()
                .filter(menu -> menu.getType().equals(Menu.Type.OTHER))
                .findAny();

        //map模型  --findFirst
        menus.stream()
                .filter(menu -> menu.getCalories() > 1000)
                .findFirst();

        //reduce模型  --数据合并
        Arrays.asList(1, 4, 8, 2, 5).stream()
                .reduce(1, (a, b) -> a * b);

        //reduce模型 --数值极值
        Arrays.asList(2833, 23, 234, 2135, 5675634).stream()
                .reduce(24, (a, b) -> a > b ? a : b);

        //map & reduce模型
        menus.stream()
                .map(Menu::getCalories)
                .reduce(0, (a, b) -> a + b);

        //max
        menus.stream()
                .max(Comparator.comparing(Menu::getCalories));
        menus.stream()
                .max(Comparator.comparing(Menu::getCalories));
        menus.stream()
                .map(menu -> menu.getCalories())
                .reduce(0, (v1, v2) -> v1 > v2 ? v1 : v2);

        //sum
        menus.stream()
                .collect(summingLong(Menu::getCalories));

        //join
        menus.stream()
                .map(menu -> menu.getName())
                .collect(joining(" - "));

        //group by
        menus.stream()
                .collect(groupingBy(Menu::getCalories));
        menus.stream()
                .collect(groupingBy(Menu::getType));

        //customize group by
        menus.stream()
                .collect(groupingBy(menu -> {
                    if (menu.getCalories() < 400)
                        return Menu.CalorieType.LOW;
                    else if (menu.getCalories() > 700)
                        return Menu.CalorieType.HIGH;
                    return Menu.CalorieType.NORMAL;
                }));

        //multi group by
        menus.stream()
                .collect(groupingBy(Menu::getType,
                        groupingBy(menu -> {
                            if (menu.getCalories() >700)
                                return Menu.CalorieType.HIGH;
                            if (menu.getCalories() < 400)
                                return Menu.CalorieType.LOW;
                            return Menu.CalorieType.NORMAL;
                        })));

        //group by and then count
        menus.stream()
                .collect(groupingBy(Menu::getType, counting()));

        //group bu and the find max
        menus.stream()
                .collect(groupingBy(Menu::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Menu::getCalories)),
                                Optional::get)));

        //group bu and count calories
        menus.stream()
                .collect(groupingBy(Menu::getType,
                        summingLong(Menu::getCalories)));

        //group by and then transfer
        menus.stream()
                .collect(groupingBy(Menu::getType,
                        mapping(menu -> {
                            if (menu.getCalories() > 700)
                                return Menu.CalorieType.HIGH;
                            if (menu.getCalories() < 400)
                                return Menu.CalorieType.LOW;
                            return Menu.CalorieType.NORMAL;
                        }, toSet())
                ));

        //group bu and then transfer --a better and safer way
        menus.stream()
                .collect(groupingBy(Menu::getType,
                        mapping(menu -> {
                            if (menu.getCalories() > 700)
                                return Menu.CalorieType.HIGH;
                            if (menu.getCalories() < 400)
                                return Menu.CalorieType.LOW;
                            return Menu.CalorieType.NORMAL;
                        }, toCollection(HashSet::new))
                ));

        //count
        menus.stream()
                .collect(counting());

        //average
        menus.stream()
                .collect(averagingInt(Menu::getCalories));

        //statistics
        menus.stream()
                .collect(summarizingInt(Menu::getCalories));

        //reduce transfer
        menus.stream()
                .collect(reducing(0, Menu::getCalories, Integer::sum));

        //mapToInt
        menus.stream()
                .map(Menu::getCalories)
                .reduce(Integer::sum);

        //partition
        menus.stream()
                .collect(partitioningBy(Menu::isFlag,
                        groupingBy(Menu::getType)));

        //partition and then find max
        menus.stream()
                .collect(partitioningBy(Menu::isFlag,
                        collectingAndThen(maxBy(comparingInt(Menu::getCalories)), Optional::get)
                ));

        //get CPU num
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

class Menu {

    private final String name;
    private final boolean flag;
    private final int calories;
    private final Type type;

    public Menu(String name, boolean flag, int calories, Type type) {
        this.name = name;
        this.flag = flag;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isFlag() {
        return flag;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    enum Type {
        FISH,MEAT,OTHER
    }

    enum CalorieType {
        LOW, NORMAL, HIGH
    }
}