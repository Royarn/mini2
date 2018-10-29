package com.royarn.mini.java8;

import java.util.*;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-24
 */
public class Function {

    private static List<List<Integer>> rest = new ArrayList<>();

    public static void main(String[] args) {
        /**String result1 = Function.print(() -> "Hello");
        String result2 = Function.print(() -> "Hello");*/

        System.out.println(subsets(Arrays.asList(1, 4, 9)));
    }

    public static String print(LambdaIntf intf) {
        return intf.out();
    }

    public static List<List<Integer>> subsets(List<Integer> list) {
        if (list.isEmpty()) {
            List<List<Integer>> retList = new ArrayList<>();
            retList.add(Collections.emptyList());
            return retList;
        }
        Integer first = list.get(0);
        List<Integer> rest = list.subList(1, list.size());

        List<List<Integer>> subans = subsets(rest);
        List<List<Integer>> subans2 = insertAll(first, subans);
        return contact(subans, subans2);
    }

    private static List<List<Integer>> contact(List<List<Integer>> subans, List<List<Integer>> subans2) {
        rest.addAll(subans);
        rest.addAll(subans2);
        return rest;
    }

    private static List<List<Integer>> insertAll(Integer first, List<List<Integer>> subans2) {
        List<List<Integer>> integers = new ArrayList<>();
        for (List<Integer> list : subans2) {
            List<Integer> copyList = new ArrayList<>();
            copyList.add(first);
            copyList.addAll(list);
            integers.add(copyList);
        }
        return integers;
    }
}

@FunctionalInterface
interface LambdaIntf {
    String out();
}