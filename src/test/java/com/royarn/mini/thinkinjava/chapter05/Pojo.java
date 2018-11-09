package com.royarn.mini.thinkinjava.chapter05;

import com.royarn.mini.thinkinjava.chapter06.Art;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-09
 */
public class Pojo {

    public int id;
    public String name;

    @Override
    public String toString() {
        return "{id: " + id + ", name: " + name + "}";
    }

    public void method() {
        System.out.println("");
    }

    public Pojo() {}

    public Pojo(String s) {
        System.out.println(s);
    }

    protected void testProtcted() {
        System.out.println("protected pojo");
    }
}


class Test extends Art{

    public Test() {
        super("sdf");
        System.out.println("Test constructor");
    }

    public static void main(String[] args) {
        new Test();
    }
}