package com.royarn.mini.java8;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-22
 */
public class Ambiguity implements Pom, Mirana{

    public void out() {
        Pom.super.out();
    }

    public static void main(String[] args) {
        new Ambiguity().out();
    }
}

interface Pom {
    default void out() {
        System.out.println("pom");
    }
}

interface Mirana {
    default void out() {
        System.out.println("mirana");
    }
}
