package com.royarn.mini.thinkinjava.chapter03;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-08
 */
public class InitialTest {

    private int j;
    private int i = initValue();
    private int f = initValues();
    private Obj obj;


    //for compiler , it will be transfer like this;

    public InitialTest() {
        System.out.println(j + " and " + i);
        j = initValuess(i);
    }

    public static void main(String[] args) {
        System.out.println(new InitialTest().i + " and " + new InitialTest().j + " and " + new InitialTest().obj);
    }

    public int initValue() {
        return 10;
    }

    public static int initValues() {
        return 12;
    }

    public int initValuess(int i) {
        i += 1;
        return i;
    }
}

class Obj {}