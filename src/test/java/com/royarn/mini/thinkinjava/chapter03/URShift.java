package com.royarn.mini.thinkinjava.chapter03;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-08
 */
public class URShift {

    public static void main(String[] args) {
        /*int i = -1;
        //zero expand
        i >>>= 10;
        System.out.println(i);
        System.out.println(true ? "ture": "false");*/

        //connect operator
        /*int x = 0, y = 1, z = 2;
        System.out.println(("test" + x + y + z) instanceof String);
        System.out.println(x + y + z + "sdf");*/

        //compiler
        URShift shift = new URShift();
        shift.toString();
        //for compiler , it will be transfer like this
        //URShift.toString(shift);

        //when you use this, it most likely used in method, and return current object
    }

    //never use return value to identify overloading method
    /*private void func(String s ) { }
    private int func(String s) {return  1;}*/

    @Override
    public String toString() {
        return super.toString();
    }

    public URShift() {
        this("constructor 1");
    }

    public URShift(String s) {
        this("constructor 2", "2");
    }

    public URShift(String s, String s1) {
        this("constructor 3", "3", "3");
    }

    public URShift(String s, String s1, String s2) {
        System.out.println(s + " : " + s1 + " : " + s2);
    }
}