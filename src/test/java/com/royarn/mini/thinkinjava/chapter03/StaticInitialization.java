package com.royarn.mini.thinkinjava.chapter03;


/**
 * Description:
 *
 * @author dell
 * @date 2018-11-08
 */
public class StaticInitialization {

    static int i;

    public static void main(String[] args) {
        System.out.println("Creating new Cupboard() in main ");
        //new Cupboard();

//        System.out.println("Inside main()");
//        Cups.c1.f(99);
//
//        System.out.println("Creating new Cupboard() in main");

        System.out.println(StaticInitialization.i);
    }
    static Cup c1 = new Cup(3);
    static Cup c2 = new Cup(4);

    static {
        System.out.println(i);
        i = 10;
    }
}

class Bowl {

    Bowl(int marker) {
        System.out.println("Bowl (" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Table {

    static Bowl b1 = new Bowl(1);

    Table() {
        System.out.println("Table()");
        b2.f(1);
    }

    Bowl b2 = new Bowl(2);
}

class Cupboard {

    Bowl b3 = new Bowl(3);

    static Bowl b4 = new Bowl(4);

    Cupboard() {
        System.out.println("Cupboard()");
        b4.f(2);
    }

    void f3(int marker) {
        System.out.println("f3 (" + marker + ")");
    }

    static Bowl b5 = new Bowl(5);
}

class Cup {

    Cup(int marker) {
        System.out.println("Cup (" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f (" + marker + ")");
    }
}

class Cups {

    static Cup c1;
    static Cup c2;
    static {
        c1 = new Cup(1);
        c2 = new Cup(2);
    }

    Cups() {
        System.out.println("Cups()");
    }
}