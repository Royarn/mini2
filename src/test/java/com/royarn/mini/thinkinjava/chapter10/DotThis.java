package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class DotThis {
    void f() {
        System.out.println("DotThis.f()");
    }
    public class Inner {
        public DotThis outer() {
            return DotThis.this;
        }
    }
    public Inner inner() {
        return new Inner();
    }

    public static void main(String[] args) {
        DotThis dotThis = new DotThis();
        Inner inner = dotThis.inner();
        inner.outer().f();
        DotThis dotThis1 = new DotThis();
        DotThis.Inner inner1 = dotThis1.new Inner();
    }
}
