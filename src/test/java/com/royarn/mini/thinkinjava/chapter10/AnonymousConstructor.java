package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
public class AnonymousConstructor {

    public Base base(int num) {
        return new Base(num) {
            {
                System.out.println("Inside initial constructor");
            }
            @Override
            public void f() {
                System.out.println("I'm anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = new AnonymousConstructor().base(28);
        base.f();
    }
}

abstract class Base {
    public Base(int num) {
        System.out.println("Base Constructor " + "num = " + num);
    }

    public abstract void f();
}
