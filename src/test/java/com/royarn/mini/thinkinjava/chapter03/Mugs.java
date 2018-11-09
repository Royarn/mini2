package com.royarn.mini.thinkinjava.chapter03;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-09
 */
public class Mugs {

    public static void main(String[] args) {
        System.out.println("Inside main()");
        Mugs mugs = new Mugs();

        //it will be output  || mug1 mug2 marker(1) marker(2) Mugs()
    }

    Mug mug1;
    Mug mug2;
    {
        System.out.println("Mug1 " + mug1);
        System.out.println("Mug2 " + mug2);
        mug1 = new Mug(1);
        mug2 = new Mug(2);
    }

    Mugs() {
        System.out.println("Mugs()");
    }
}

class Mug {

    Mug(int marker) {
        System.out.println("Marker (" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}
