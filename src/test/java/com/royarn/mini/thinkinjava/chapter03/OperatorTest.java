package com.royarn.mini.thinkinjava.chapter03;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-08
 */
public class OperatorTest {

    public static void main(String[] args) {
       /* //arithmetic operator
        System.out.println(increse(1));

        //boolean operator
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        System.out.println(i1 == i2);*/

        //bit operator
        System.out.println();
    }

    private static int increse(int number) {
        //pre-increase
        System.out.println(++number);
        //post-increase
        System.out.println(number++);
        return number;
    }
}