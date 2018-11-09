package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-09
 */
public class Art {

    protected Art(String name) {
        System.out.println("Art constructor" + name);
    }
}

class Drawing extends Art {

    protected Drawing(String name) {
        super("Art");
        System.out.println("Drawing constructor");
    }
}

class Cartoon extends Drawing {

    public Cartoon(int id , String name) {
        super("blink");
        System.out.println("Cartoon constructor");
    }

    public static void main(String[] args) {
        Cartoon cartoon = new Cartoon(1, "1");
        System.out.println(cartoon);
    }
}