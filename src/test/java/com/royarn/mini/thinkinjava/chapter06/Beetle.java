package com.royarn.mini.thinkinjava.chapter06;

import org.springframework.context.annotation.Bean;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class Beetle extends Insect {
    private int k = pinit("Beetle.k initialized");

    public Beetle() {
        System.out.println("k = " + k);
        System.out.println("j = " + j);
    }

    private static int x2 = init("Beetle.x2 initialized");

    private static int init(String s) {
        System.out.println("Beetle.x2 initialized");
        return 300;
    }

    private int pinit(String s) {
        System.out.println("Beetle.k initialize");
        return 200;
    }

    public static void main(String[] args) {
        System.out.println("Beetle constructor");
        new Beetle();
    }
}

/**
 *
 * static Insect x1 initialized
 * Beetle.x2 initialized
 * Beetle constructor
 * i = 9 j = 0
 * Beetle.k initialized
 * k = 200
 * j = 39
 *  static_insect_x1_initialized
 *  i = 9 j = 0
 */
class Insect {

    private int i = 9;
    protected int j;
    Insect() {
        System.out.println("i = " + i + " j = " + j);
        j = 39;
    }

    private static int xl = initClass("static Insect x1 initialized");

    private static int initClass(String static_insect_x1_initialized) {
        System.out.println("static_insect_x1_initialized");
        return 100;
    }
}
