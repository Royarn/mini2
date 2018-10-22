package com.royarn.mini.java8;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-22
 */

/**
 * lambda表达式调试技巧： 尽量在本类中debug， 可以查看具体的栈信息
 * stream调试技巧： 在每一步中加入peek操作，相当于普通程序中的sout
 *
 */
public class LambdaDebuging {

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 34));
        points.stream()
                .map(Point::getX)
                .forEach(System.out::println);
        List<Integer> numbers = Arrays.asList(2, 3, 4, 5);
        numbers.stream()
                .peek(x -> System.out.println("original data: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 ==0)
                .peek(x -> System.out.println("after filter: " + x))
                .forEach(System.out::println);
    }
}
