package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-21
 */
public class Callbacks {

    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        Caller caller = new Caller(c1);
        Caller caller1 = new Caller(c2.incrementable());
        caller.go();
        caller.go();
        caller1.go();
        caller1.go();
        /**
         * this will be output
         * Other operation
         * 1
         * 1
         * 2
         * Other operation
         * 2
         * Other operation
         * 3
         */
    }
}

interface Incrementable {
    void increment();
}

class Callee1 implements Incrementable {
    private int i=0;
    @Override
    public void increment() {
        i++;
        System.out.println(i);
    }
}

class MyIncrement {
    public void increment() {
        System.out.println("Other operation");
    }
    static void f(MyIncrement increment) {
        increment.increment();
    }
}

class Callee2 extends MyIncrement {
    private int i=0;
    public void increment() {
        super.increment();
        i++;
        System.out.println(i);
    }
    private class Closure implements Incrementable {
        @Override
        public void increment() {
            Callee2.this.increment();
        }
    }

    Incrementable incrementable() {
        return new Closure();
    }
}

class Caller {
    private Incrementable incrementable;
    Caller(Incrementable incrementable) {
        this.incrementable = incrementable;
    }
    void go() {
        incrementable.increment();
    }
}