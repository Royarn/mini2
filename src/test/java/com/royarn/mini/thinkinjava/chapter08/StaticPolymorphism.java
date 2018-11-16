package com.royarn.mini.thinkinjava.chapter08;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-16
 */
public class StaticPolymorphism {

    public static void main(String[] args) {
        StaticSuper staticSuper = new StaticSub();
        System.out.println(staticSuper.dynamicGet());
        System.out.println(staticSuper.staticGet());
    }
}

class StaticSuper {

    public static String staticGet() {
        return "Base staticGet()";
    }

    public String dynamicGet() {
        return "Base dynamicGet()";
    }
}

class StaticSub extends StaticSuper {

    public static String staticGet() {
        return "StaticSub staticGet()";
    }

    public String dynamicGet() {
        return "StaticSub dynamicGet()";
    }
}
