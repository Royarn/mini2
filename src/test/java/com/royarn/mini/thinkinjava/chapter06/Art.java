package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-09
 */
public class Art {

    protected Art() {
        System.out.println("Art constructor");
    }
}

class Drawing extends Art {

    protected Drawing() {
        System.out.println("Drawing constructor");
    }
}

class Cartoon extends Drawing {

    public Cartoon() {
        System.out.println("Cartoon constructor");
    }

    public static void main(String[] args) {
        new Cartoon();
    }
}