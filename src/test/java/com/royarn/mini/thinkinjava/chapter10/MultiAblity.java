package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
public class MultiAblity {
    private String outer;

    public static void main(String[] args) {
        /**C c = new C();
        D d = new D();
        takeA(c);
        takeB(c);
        takeA(d);
        takeB(d.makeB());*/
        MultiAblity multiAblity = new MultiAblity();
        InnerClass innerClass = multiAblity.innerClass();
        System.out.println(multiAblity.outer);
        innerClass.operate();
        System.out.println(multiAblity.outer);
    }

    static void takeA(A a) {
        a.A();
    }
    static void takeB(B a) {
        a.B();
    }

    class InnerClass {
        private String name;
        public void operate() {
            outer = "deal with outer data";
        }
    }

    public InnerClass innerClass() {
        return new InnerClass();
    }
}

interface A {
    void A();
}
interface B {
    void B();
}

/**
 * let a class have A ,B ablity, you can implements them all
 */
class C implements A, B {
    @Override
    public void A() {
        System.out.println("A ablity");
    }

    @Override
    public void B() {
        System.out.println("B ablity");
    }
}

/**
 * also , you can use it like this
 */
class D implements A {
    @Override
    public void A() {
        System.out.println("A ablity");
    }
    B makeB() {
        return () -> System.out.println("B ablity");
    }
}